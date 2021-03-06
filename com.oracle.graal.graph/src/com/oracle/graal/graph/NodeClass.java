/*
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.graal.graph;

import java.lang.reflect.*;
import java.util.*;
import java.util.Map.Entry;

import com.oracle.graal.graph.Graph.DuplicationReplacement;
import com.oracle.graal.graph.Node.Verbosity;

public class NodeClass extends FieldIntrospection {

    public static final NodeClass get(Class<?> c) {
        NodeClass clazz = (NodeClass) allClasses.get(c);
        if (clazz != null) {
            return clazz;
        }

        /*
         * Using putIfAbsent doesn't work here, because the creation of NodeClass needs to be
         * serialized. (the NodeClass constructor looks at allClasses, and it also uses the static
         * field nextIterableId)
         * 
         * The fact that ConcurrentHashMap.put and .get are used should make the double-checked
         * locking idiom work, since it internally uses volatile.
         */

        synchronized (allClasses) {
            clazz = (NodeClass) allClasses.get(c);
            if (clazz == null) {
                clazz = new NodeClass(c);
                NodeClass oldClass = (NodeClass) allClasses.putIfAbsent(c, clazz);
                assert oldClass == null;
            }
            return clazz;
        }
    }

    static final int NOT_ITERABLE = -1;

    private static final Class<?> NODE_CLASS = Node.class;
    private static final Class<?> INPUT_LIST_CLASS = NodeInputList.class;
    private static final Class<?> SUCCESSOR_LIST_CLASS = NodeSuccessorList.class;

    private static int nextIterableId = 0;

    private final int directInputCount;
    private final long[] inputOffsets;
    private final int directSuccessorCount;
    private final long[] successorOffsets;
    private final Class<?>[] dataTypes;
    private final boolean canGVN;
    private final int startGVNNumber;
    private final String shortName;
    private final String nameTemplate;
    private final int iterableId;
    private int[] iterableIds;

    public NodeClass(Class<?> clazz) {
        super(clazz);
        assert NODE_CLASS.isAssignableFrom(clazz);

        FieldScanner scanner = new FieldScanner(new DefaultCalcOffset());
        scanner.scan(clazz);

        directInputCount = scanner.inputOffsets.size();
        inputOffsets = sortedLongCopy(scanner.inputOffsets, scanner.inputListOffsets);
        directSuccessorCount = scanner.successorOffsets.size();
        successorOffsets = sortedLongCopy(scanner.successorOffsets, scanner.successorListOffsets);

        dataOffsets = sortedLongCopy(scanner.dataOffsets);
        dataTypes = new Class[dataOffsets.length];
        for (int i = 0; i < dataOffsets.length; i++) {
            dataTypes[i] = scanner.fieldTypes.get(dataOffsets[i]);
        }

        fieldNames = scanner.fieldNames;
        fieldTypes = scanner.fieldTypes;

        canGVN = Node.ValueNumberable.class.isAssignableFrom(clazz);
        startGVNNumber = clazz.hashCode();

        String newShortName = clazz.getSimpleName();
        if (newShortName.endsWith("Node") && !newShortName.equals("StartNode") && !newShortName.equals("EndNode")) {
            newShortName = newShortName.substring(0, newShortName.length() - 4);
        }
        String newNameTemplate = null;
        NodeInfo info = clazz.getAnnotation(NodeInfo.class);
        if (info != null) {
            if (!info.shortName().isEmpty()) {
                newShortName = info.shortName();
            }
            if (!info.nameTemplate().isEmpty()) {
                newNameTemplate = info.nameTemplate();
            }
        }
        this.nameTemplate = newNameTemplate == null ? newShortName : newNameTemplate;
        this.shortName = newShortName;
        if (Node.IterableNodeType.class.isAssignableFrom(clazz)) {
            this.iterableId = nextIterableId++;
            List<NodeClass> existingClasses = new LinkedList<>();
            for (FieldIntrospection nodeClass : allClasses.values()) {
                if (clazz.isAssignableFrom(nodeClass.clazz)) {
                    existingClasses.add((NodeClass) nodeClass);
                }
                if (nodeClass.clazz.isAssignableFrom(clazz) && Node.IterableNodeType.class.isAssignableFrom(nodeClass.clazz)) {
                    NodeClass superNodeClass = (NodeClass) nodeClass;
                    superNodeClass.iterableIds = Arrays.copyOf(superNodeClass.iterableIds, superNodeClass.iterableIds.length + 1);
                    superNodeClass.iterableIds[superNodeClass.iterableIds.length - 1] = this.iterableId;
                }
            }
            int[] ids = new int[existingClasses.size() + 1];
            ids[0] = iterableId;
            int i = 1;
            for (NodeClass other : existingClasses) {
                ids[i++] = other.iterableId;
            }
            this.iterableIds = ids;
        } else {
            this.iterableId = NOT_ITERABLE;
            this.iterableIds = null;
        }
    }

    @Override
    protected void rescanFieldOffsets(CalcOffset calc) {
        FieldScanner scanner = new FieldScanner(calc);
        scanner.scan(clazz);
        assert directInputCount == scanner.inputOffsets.size();
        copyInto(inputOffsets, sortedLongCopy(scanner.inputOffsets, scanner.inputListOffsets));
        assert directSuccessorCount == scanner.successorOffsets.size();
        copyInto(successorOffsets, sortedLongCopy(scanner.successorOffsets, scanner.successorListOffsets));
        copyInto(dataOffsets, sortedLongCopy(scanner.dataOffsets));

        for (int i = 0; i < dataOffsets.length; i++) {
            dataTypes[i] = scanner.fieldTypes.get(dataOffsets[i]);
        }

        fieldNames.clear();
        fieldNames.putAll(scanner.fieldNames);
        fieldTypes.clear();
        fieldTypes.putAll(scanner.fieldTypes);
    }

    public String shortName() {
        return shortName;
    }

    public int[] iterableIds() {
        return iterableIds;
    }

    public int iterableId() {
        return iterableId;
    }

    public boolean valueNumberable() {
        return canGVN;
    }

    public static int cacheSize() {
        return nextIterableId;
    }

    protected static class FieldScanner extends BaseFieldScanner {

        public final ArrayList<Long> inputOffsets = new ArrayList<>();
        public final ArrayList<Long> inputListOffsets = new ArrayList<>();
        public final ArrayList<Long> successorOffsets = new ArrayList<>();
        public final ArrayList<Long> successorListOffsets = new ArrayList<>();

        protected FieldScanner(CalcOffset calc) {
            super(calc);
        }

        @Override
        protected void scanField(Field field, Class<?> type, long offset) {
            if (field.isAnnotationPresent(Node.Input.class)) {
                assert !field.isAnnotationPresent(Node.Successor.class) : "field cannot be both input and successor";
                if (INPUT_LIST_CLASS.isAssignableFrom(type)) {
                    GraalInternalError.guarantee(Modifier.isFinal(field.getModifiers()), "NodeInputList input field %s should be final", field);
                    GraalInternalError.guarantee(!Modifier.isPublic(field.getModifiers()), "NodeInputList input field %s should not be public", field);
                    inputListOffsets.add(offset);
                } else {
                    GraalInternalError.guarantee(NODE_CLASS.isAssignableFrom(type) || type.isInterface(), "invalid input type: %s", type);
                    GraalInternalError.guarantee(!Modifier.isFinal(field.getModifiers()), "Node input field %s should not be final", field);
                    GraalInternalError.guarantee(Modifier.isPrivate(field.getModifiers()), "Node input field %s should be private", field);
                    inputOffsets.add(offset);
                }
                if (field.getAnnotation(Node.Input.class).notDataflow()) {
                    fieldNames.put(offset, field.getName() + "#NDF");
                }
            } else if (field.isAnnotationPresent(Node.Successor.class)) {
                if (SUCCESSOR_LIST_CLASS.isAssignableFrom(type)) {
                    GraalInternalError.guarantee(Modifier.isFinal(field.getModifiers()), "NodeSuccessorList successor field % should be final", field);
                    GraalInternalError.guarantee(!Modifier.isPublic(field.getModifiers()), "NodeSuccessorList successor field %s should not be public", field);
                    successorListOffsets.add(offset);
                } else {
                    GraalInternalError.guarantee(NODE_CLASS.isAssignableFrom(type), "invalid successor type: %s", type);
                    GraalInternalError.guarantee(!Modifier.isFinal(field.getModifiers()), "Node successor field %s should not be final", field);
                    GraalInternalError.guarantee(Modifier.isPrivate(field.getModifiers()), "Node successor field %s should be private", field);
                    successorOffsets.add(offset);
                }
            } else {
                GraalInternalError.guarantee(!NODE_CLASS.isAssignableFrom(type) || field.getName().equals("Null"), "suspicious node field: %s", field);
                GraalInternalError.guarantee(!INPUT_LIST_CLASS.isAssignableFrom(type), "suspicious node input list field: %s", field);
                GraalInternalError.guarantee(!SUCCESSOR_LIST_CLASS.isAssignableFrom(type), "suspicious node successor list field: %s", field);
                dataOffsets.add(offset);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("NodeClass ").append(clazz.getSimpleName()).append(" [");
        for (int i = 0; i < inputOffsets.length; i++) {
            str.append(i == 0 ? "" : ", ").append(inputOffsets[i]);
        }
        str.append("] [");
        for (int i = 0; i < successorOffsets.length; i++) {
            str.append(i == 0 ? "" : ", ").append(successorOffsets[i]);
        }
        str.append("] [");
        for (int i = 0; i < dataOffsets.length; i++) {
            str.append(i == 0 ? "" : ", ").append(dataOffsets[i]);
        }
        str.append("]");
        return str.toString();
    }

    /**
     * Describes an edge slot for a {@link NodeClass}.
     * 
     * @see NodeClass#get(Node, Position)
     * @see NodeClass#getName(Position)
     */
    public static final class Position {

        public final boolean input;
        public final int index;
        public final int subIndex;

        public Position(boolean input, int index, int subIndex) {
            this.input = input;
            this.index = index;
            this.subIndex = subIndex;
        }

        @Override
        public String toString() {
            return (input ? "input " : "successor ") + index + "/" + subIndex;
        }

        public Node get(Node node) {
            return node.getNodeClass().get(node, this);
        }

        public void set(Node node, Node value) {
            node.getNodeClass().set(node, this, value);
        }

        public boolean isValidFor(Node node, Node from) {
            return node.getNodeClass().isValid(this, from.getNodeClass());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + index;
            result = prime * result + (input ? 1231 : 1237);
            result = prime * result + subIndex;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Position other = (Position) obj;
            if (index != other.index) {
                return false;
            }
            if (input != other.input) {
                return false;
            }
            if (subIndex != other.subIndex) {
                return false;
            }
            return true;
        }
    }

    private static Node getNode(Node node, long offset) {
        return (Node) unsafe.getObject(node, offset);
    }

    @SuppressWarnings("unchecked")
    private static NodeList<Node> getNodeList(Node node, long offset) {
        return (NodeList<Node>) unsafe.getObject(node, offset);
    }

    private static void putNode(Node node, long offset, Node value) {
        unsafe.putObject(node, offset, value);
    }

    private static void putNodeList(Node node, long offset, NodeList value) {
        unsafe.putObject(node, offset, value);
    }

    /**
     * An iterator that will iterate over the fields given in {@link #offsets}. The first
     * {@link #directCount} offsets are treated as fields of type {@link Node}, while the rest of
     * the fields are treated as {@link NodeList}s. All elements of these NodeLists will be visited
     * by the iterator as well. This iterator can be used to iterate over the inputs or successors
     * of a node.
     * 
     * An iterator of this type will not return null values, unless the field values are modified
     * concurrently. Concurrent modifications are detected by an assertion on a best-effort basis.
     */
    public static final class NodeClassIterator implements Iterator<Node> {

        private final Node node;
        private final int modCount;
        private final int directCount;
        private final long[] offsets;
        private int index;
        private int subIndex;

        /**
         * Creates an iterator that will iterate over fields in the given node.
         * 
         * @param node the node which contains the fields.
         * @param offsets the offsets of the fields.
         * @param directCount the number of fields that should be treated as fields of type
         *            {@link Node}, the rest are treated as {@link NodeList}s.
         */
        private NodeClassIterator(Node node, long[] offsets, int directCount) {
            this.node = node;
            this.modCount = node.modCount();
            this.offsets = offsets;
            this.directCount = directCount;
            index = NOT_ITERABLE;
            subIndex = 0;
            forward();
        }

        private void forward() {
            if (index < directCount) {
                index++;
                while (index < directCount) {
                    Node element = getNode(node, offsets[index]);
                    if (element != null) {
                        return;
                    }
                    index++;
                }
            } else {
                subIndex++;
            }
            while (index < offsets.length) {
                NodeList<Node> list = getNodeList(node, offsets[index]);
                while (subIndex < list.size()) {
                    if (list.get(subIndex) != null) {
                        return;
                    }
                    subIndex++;
                }
                subIndex = 0;
                index++;
            }
        }

        private Node nextElement() {
            if (index < directCount) {
                return getNode(node, offsets[index]);
            } else if (index < offsets.length) {
                NodeList<Node> list = getNodeList(node, offsets[index]);
                return list.get(subIndex);
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            try {
                return index < offsets.length;
            } finally {
                assert modCount == node.modCount() : "must not be modified";
            }
        }

        @Override
        public Node next() {
            try {
                return nextElement();
            } finally {
                forward();
                assert modCount == node.modCount();
            }
        }

        public Position nextPosition() {
            try {
                if (index < directCount) {
                    return new Position(offsets == node.getNodeClass().inputOffsets, index, NOT_ITERABLE);
                } else {
                    return new Position(offsets == node.getNodeClass().inputOffsets, index, subIndex);
                }
            } finally {
                forward();
                assert modCount == node.modCount();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public int valueNumber(Node n) {
        int number = 0;
        if (canGVN) {
            number = startGVNNumber;
            for (int i = 0; i < dataOffsets.length; ++i) {
                Class<?> type = dataTypes[i];
                if (type.isPrimitive()) {
                    if (type == Integer.TYPE) {
                        int intValue = unsafe.getInt(n, dataOffsets[i]);
                        number += intValue;
                    } else if (type == Long.TYPE) {
                        long longValue = unsafe.getLong(n, dataOffsets[i]);
                        number += longValue ^ (longValue >>> 32);
                    } else if (type == Boolean.TYPE) {
                        boolean booleanValue = unsafe.getBoolean(n, dataOffsets[i]);
                        if (booleanValue) {
                            number += 7;
                        }
                    } else {
                        assert false;
                    }
                } else {
                    Object o = unsafe.getObject(n, dataOffsets[i]);
                    if (o != null) {
                        number += o.hashCode();
                    }
                }
                number *= 13;
            }
        }
        return number;
    }

    /**
     * Populates a given map with the names and values of all data fields.
     * 
     * @param node the node from which to take the values.
     * @param properties a map that will be populated.
     */
    public void getDebugProperties(Node node, Map<Object, Object> properties) {
        for (int i = 0; i < dataOffsets.length; ++i) {
            Class<?> type = fieldTypes.get(dataOffsets[i]);
            Object value = null;
            if (type.isPrimitive()) {
                if (type == Integer.TYPE) {
                    value = unsafe.getInt(node, dataOffsets[i]);
                } else if (type == Long.TYPE) {
                    value = unsafe.getLong(node, dataOffsets[i]);
                } else if (type == Boolean.TYPE) {
                    value = unsafe.getBoolean(node, dataOffsets[i]);
                } else if (type == Long.TYPE) {
                    value = unsafe.getLong(node, dataOffsets[i]);
                } else if (type == Double.TYPE) {
                    value = unsafe.getDouble(node, dataOffsets[i]);
                } else {
                    assert false : "unhandled property type: " + type;
                }
            } else {
                value = unsafe.getObject(node, dataOffsets[i]);
            }
            properties.put(fieldNames.get(dataOffsets[i]), value);
        }
    }

    public boolean valueEqual(Node a, Node b) {
        if (!canGVN || a.getNodeClass() != b.getNodeClass()) {
            return a == b;
        }
        for (int i = 0; i < dataOffsets.length; ++i) {
            Class<?> type = dataTypes[i];
            if (type.isPrimitive()) {
                if (type == Integer.TYPE) {
                    int aInt = unsafe.getInt(a, dataOffsets[i]);
                    int bInt = unsafe.getInt(b, dataOffsets[i]);
                    if (aInt != bInt) {
                        return false;
                    }
                } else if (type == Boolean.TYPE) {
                    boolean aBoolean = unsafe.getBoolean(a, dataOffsets[i]);
                    boolean bBoolean = unsafe.getBoolean(b, dataOffsets[i]);
                    if (aBoolean != bBoolean) {
                        return false;
                    }
                } else if (type == Long.TYPE) {
                    long aLong = unsafe.getLong(a, dataOffsets[i]);
                    long bLong = unsafe.getLong(b, dataOffsets[i]);
                    if (aLong != bLong) {
                        return false;
                    }
                } else if (type == Double.TYPE) {
                    double aDouble = unsafe.getDouble(a, dataOffsets[i]);
                    double bDouble = unsafe.getDouble(b, dataOffsets[i]);
                    if (aDouble != bDouble) {
                        return false;
                    }
                } else {
                    assert false : "unhandled type: " + type;
                }
            } else {
                Object objectA = unsafe.getObject(a, dataOffsets[i]);
                Object objectB = unsafe.getObject(b, dataOffsets[i]);
                if (objectA != objectB) {
                    if (objectA != null && objectB != null) {
                        if (!(objectA.equals(objectB))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValid(Position pos, NodeClass from) {
        if (this == from) {
            return true;
        }
        long[] offsets = pos.input ? inputOffsets : successorOffsets;
        if (pos.index >= offsets.length) {
            return false;
        }
        long[] fromOffsets = pos.input ? from.inputOffsets : from.successorOffsets;
        if (pos.index >= fromOffsets.length) {
            return false;
        }
        return offsets[pos.index] == fromOffsets[pos.index];
    }

    public Node get(Node node, Position pos) {
        long offset = pos.input ? inputOffsets[pos.index] : successorOffsets[pos.index];
        if (pos.subIndex == NOT_ITERABLE) {
            return getNode(node, offset);
        } else {
            return getNodeList(node, offset).get(pos.subIndex);
        }
    }

    public String getName(Position pos) {
        return fieldNames.get(pos.input ? inputOffsets[pos.index] : successorOffsets[pos.index]);
    }

    public void set(Node node, Position pos, Node x) {
        long offset = pos.input ? inputOffsets[pos.index] : successorOffsets[pos.index];
        if (pos.subIndex == NOT_ITERABLE) {
            Node old = getNode(node, offset);
            assert x == null || fieldTypes.get((pos.input ? inputOffsets : successorOffsets)[pos.index]).isAssignableFrom(x.getClass()) : this + ".set(node, pos, " + x + ")";
            putNode(node, offset, x);
            if (pos.input) {
                node.updateUsages(old, x);
            } else {
                node.updatePredecessor(old, x);
            }
        } else {
            NodeList<Node> list = getNodeList(node, offset);
            if (pos.subIndex < list.size()) {
                list.set(pos.subIndex, x);
            } else {
                while (pos.subIndex < list.size() - 1) {
                    list.add(null);
                }
                list.add(x);
            }
        }
    }

    public NodeClassIterable getInputIterable(final Node node) {
        assert clazz.isInstance(node);
        return new NodeClassIterable() {

            @Override
            public NodeClassIterator iterator() {
                return new NodeClassIterator(node, inputOffsets, directInputCount);
            }

            @Override
            public boolean contains(Node other) {
                return inputContains(node, other);
            }
        };
    }

    public NodeClassIterable getSuccessorIterable(final Node node) {
        assert clazz.isInstance(node);
        return new NodeClassIterable() {

            @Override
            public NodeClassIterator iterator() {
                return new NodeClassIterator(node, successorOffsets, directSuccessorCount);
            }

            @Override
            public boolean contains(Node other) {
                return successorContains(node, other);
            }
        };
    }

    public boolean replaceFirstInput(Node node, Node old, Node other) {
        int index = 0;
        while (index < directInputCount) {
            Node input = getNode(node, inputOffsets[index]);
            if (input == old) {
                assert other == null || fieldTypes.get(inputOffsets[index]).isAssignableFrom(other.getClass()) : "Can not assign " + other.getClass() + " to " + fieldTypes.get(inputOffsets[index]) +
                                " in " + node;
                putNode(node, inputOffsets[index], other);
                return true;
            }
            index++;
        }
        while (index < inputOffsets.length) {
            NodeList<Node> list = getNodeList(node, inputOffsets[index]);
            assert list != null : clazz;
            if (list.replaceFirst(old, other)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean replaceFirstSuccessor(Node node, Node old, Node other) {
        int index = 0;
        while (index < directSuccessorCount) {
            Node successor = getNode(node, successorOffsets[index]);
            if (successor == old) {
                assert other == null || fieldTypes.get(successorOffsets[index]).isAssignableFrom(other.getClass()) : fieldTypes.get(successorOffsets[index]) + " is not compatible with " +
                                other.getClass();
                putNode(node, successorOffsets[index], other);
                return true;
            }
            index++;
        }
        while (index < successorOffsets.length) {
            NodeList<Node> list = getNodeList(node, successorOffsets[index]);
            assert list != null : clazz + " " + successorOffsets[index] + " " + node;
            if (list.replaceFirst(old, other)) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Clear all inputs in the given node. This is accomplished by setting input fields to null and
     * replacing input lists with new lists. (which is important so that this method can be used to
     * clear the inputs of cloned nodes.)
     * 
     * @param node the node to be cleared
     */
    public void clearInputs(Node node) {
        int index = 0;
        while (index < directInputCount) {
            putNode(node, inputOffsets[index++], null);
        }
        while (index < inputOffsets.length) {
            long curOffset = inputOffsets[index++];
            int size = (getNodeList(node, curOffset)).initialSize;
            // replacing with a new list object is the expected behavior!
            putNodeList(node, curOffset, new NodeInputList<>(node, size));
        }
    }

    /**
     * Clear all successors in the given node. This is accomplished by setting successor fields to
     * null and replacing successor lists with new lists. (which is important so that this method
     * can be used to clear the successors of cloned nodes.)
     * 
     * @param node the node to be cleared
     */
    public void clearSuccessors(Node node) {
        int index = 0;
        while (index < directSuccessorCount) {
            putNode(node, successorOffsets[index++], null);
        }
        while (index < successorOffsets.length) {
            long curOffset = successorOffsets[index++];
            int size = getNodeList(node, curOffset).initialSize;
            // replacing with a new list object is the expected behavior!
            putNodeList(node, curOffset, new NodeSuccessorList<>(node, size));
        }
    }

    /**
     * Copies the inputs from node to newNode. The nodes are expected to be of the exact same
     * NodeClass type.
     * 
     * @param node the node from which the inputs should be copied.
     * @param newNode the node to which the inputs should be copied.
     */
    public void copyInputs(Node node, Node newNode) {
        assert node.getClass() == clazz && newNode.getClass() == clazz;

        int index = 0;
        while (index < directInputCount) {
            putNode(newNode, inputOffsets[index], getNode(node, inputOffsets[index]));
            index++;
        }
        while (index < inputOffsets.length) {
            NodeList<Node> list = getNodeList(newNode, inputOffsets[index]);
            list.copy(getNodeList(node, inputOffsets[index]));
            index++;
        }
    }

    /**
     * Copies the successors from node to newNode. The nodes are expected to be of the exact same
     * NodeClass type.
     * 
     * @param node the node from which the successors should be copied.
     * @param newNode the node to which the successors should be copied.
     */
    public void copySuccessors(Node node, Node newNode) {
        assert node.getClass() == clazz && newNode.getClass() == clazz;

        int index = 0;
        while (index < directSuccessorCount) {
            putNode(newNode, successorOffsets[index], getNode(node, successorOffsets[index]));
            index++;
        }
        while (index < successorOffsets.length) {
            NodeList<Node> list = getNodeList(newNode, successorOffsets[index]);
            list.copy(getNodeList(node, successorOffsets[index]));
            index++;
        }
    }

    public boolean edgesEqual(Node node, Node other) {
        return inputsEqual(node, other) && successorsEqual(node, other);
    }

    public boolean inputsEqual(Node node, Node other) {
        assert node.getClass() == clazz && other.getClass() == clazz;
        int index = 0;
        while (index < directInputCount) {
            if (getNode(other, inputOffsets[index]) != getNode(node, inputOffsets[index])) {
                return false;
            }
            index++;
        }
        while (index < inputOffsets.length) {
            NodeList<Node> list = getNodeList(other, inputOffsets[index]);
            if (!list.equals(getNodeList(node, inputOffsets[index]))) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean successorsEqual(Node node, Node other) {
        assert node.getClass() == clazz && other.getClass() == clazz;
        int index = 0;
        while (index < directSuccessorCount) {
            if (getNode(other, successorOffsets[index]) != getNode(node, successorOffsets[index])) {
                return false;
            }
            index++;
        }
        while (index < successorOffsets.length) {
            NodeList<Node> list = getNodeList(other, successorOffsets[index]);
            if (!list.equals(getNodeList(node, successorOffsets[index]))) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean inputContains(Node node, Node other) {
        assert node.getClass() == clazz;

        int index = 0;
        while (index < directInputCount) {
            if (getNode(node, inputOffsets[index]) == other) {
                return true;
            }
            index++;
        }
        while (index < inputOffsets.length) {
            NodeList<Node> list = getNodeList(node, inputOffsets[index]);
            if (list.contains(other)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean successorContains(Node node, Node other) {
        assert node.getClass() == clazz;

        int index = 0;
        while (index < directSuccessorCount) {
            if (getNode(node, successorOffsets[index]) == other) {
                return true;
            }
            index++;
        }
        while (index < successorOffsets.length) {
            NodeList<Node> list = getNodeList(node, successorOffsets[index]);
            if (list.contains(other)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public List<Position> getFirstLevelInputPositions() {
        List<Position> positions = new ArrayList<>(inputOffsets.length);
        for (int i = 0; i < inputOffsets.length; i++) {
            positions.add(new Position(true, i, NOT_ITERABLE));
        }
        return positions;
    }

    public List<Position> getFirstLevelSuccessorPositions() {
        List<Position> positions = new ArrayList<>(successorOffsets.length);
        for (int i = 0; i < successorOffsets.length; i++) {
            positions.add(new Position(false, i, NOT_ITERABLE));
        }
        return positions;
    }

    public Class<?> getJavaClass() {
        return clazz;
    }

    /**
     * The template used to build the {@link Verbosity#Name} version. Variable part are specified
     * using &#123;i#inputName&#125; or &#123;p#propertyName&#125;.
     */
    public String getNameTemplate() {
        return nameTemplate;
    }

    static Map<Node, Node> addGraphDuplicate(Graph graph, Iterable<Node> nodes, DuplicationReplacement replacements) {
        Map<Node, Node> newNodes = new IdentityHashMap<>();
        Map<Node, Node> replacementsMap = new IdentityHashMap<>();
        // create node duplicates
        for (Node node : nodes) {
            if (node != null) {
                assert !node.isDeleted() : "trying to duplicate deleted node: " + node;
                Node replacement = replacements.replacement(node);
                if (replacement != node) {
                    assert replacement != null;
                    newNodes.put(node, replacement);
                } else {
                    Node newNode = node.clone(graph);
                    assert newNode.getClass() == node.getClass();
                    newNodes.put(node, newNode);
                }
            }
        }
        // re-wire inputs
        for (Entry<Node, Node> entry : newNodes.entrySet()) {
            Node oldNode = entry.getKey();
            Node node = entry.getValue();
            for (NodeClassIterator iter = oldNode.inputs().iterator(); iter.hasNext();) {
                Position pos = iter.nextPosition();
                if (!pos.isValidFor(node, oldNode)) {
                    continue;
                }
                Node input = oldNode.getNodeClass().get(oldNode, pos);
                Node target = newNodes.get(input);
                if (target == null) {
                    target = replacementsMap.get(input);
                    if (target == null) {
                        Node replacement = replacements.replacement(input);
                        if (replacement != input) {
                            replacementsMap.put(input, replacement);
                            assert isAssignable(node.getNodeClass().fieldTypes.get(node.getNodeClass().inputOffsets[pos.index]), replacement);
                            target = replacement;
                        } else if (input.graph() == graph) { // patch to the outer world
                            target = input;
                        }
                    }
                }
                node.getNodeClass().set(node, pos, target);
            }
        }

        // re-wire successors
        for (Entry<Node, Node> entry : newNodes.entrySet()) {
            Node oldNode = entry.getKey();
            Node node = entry.getValue();
            for (NodeClassIterator iter = oldNode.successors().iterator(); iter.hasNext();) {
                Position pos = iter.nextPosition();
                if (!pos.isValidFor(node, oldNode)) {
                    continue;
                }
                Node succ = oldNode.getNodeClass().get(oldNode, pos);
                Node target = newNodes.get(succ);
                if (target == null) {
                    target = replacementsMap.get(succ);
                    if (target == null) {
                        Node replacement = replacements.replacement(succ);
                        if (replacement != succ) {
                            replacementsMap.put(succ, replacement);
                            assert isAssignable(node.getNodeClass().fieldTypes.get(node.getNodeClass().successorOffsets[pos.index]), replacement);
                            target = replacement;
                        }
                    }
                }
                node.getNodeClass().set(node, pos, target);
            }
        }
        return newNodes;
    }

    private static boolean isAssignable(Class<?> fieldType, Node replacement) {
        return replacement == null || !NODE_CLASS.isAssignableFrom(fieldType) || fieldType.isAssignableFrom(replacement.getClass());
    }
}
