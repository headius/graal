/*
 * Copyright (c) 2012, 2012, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.truffle.api.dsl.test;

import static com.oracle.truffle.api.CompilerAsserts.*;
import static com.oracle.truffle.api.CompilerDirectives.*;
import static com.oracle.truffle.api.TruffleOptions.*;
import static com.oracle.truffle.api.dsl.test.SimpleTypesGen.*;
import static java.util.Arrays.*;

import com.oracle.truffle.api.CompilerDirectives.SlowPath;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.dsl.test.PolymorphicTest2.Node1;
import com.oracle.truffle.api.dsl.test.TypeSystemTest.ValueNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(PolymorphicTest2.class)
public final class PolymorphicTest2Factory {

    private PolymorphicTest2Factory() {
    }

    public static List<NodeFactory<Node1>> getFactories() {
        return asList(Node1Factory.getInstance());
    }

    @GeneratedBy(Node1.class)
    static final class Node1Factory implements NodeFactory<Node1> {

        private static Node1Factory node1FactoryInstance;

        private Node1Factory() {
        }

        @Override
        public Node1 createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof ValueNode)) {
                return create((ValueNode) arguments[0], (ValueNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public Node1 createNodeGeneric(Node1 thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<Node1> getNodeClass() {
            return Node1.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class, ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class);
        }

        static Node1 createGeneric(Node1 thisNode) {
            return new Node1GenericNode((Node1BaseNode) thisNode);
        }

        static Node1 create(ValueNode left, ValueNode right) {
            return new Node1UninitializedNode(left, right);
        }

        static NodeFactory<Node1> getInstance() {
            if (node1FactoryInstance == null) {
                node1FactoryInstance = new Node1Factory();
            }
            return node1FactoryInstance;
        }

        @GeneratedBy(Node1.class)
        private abstract static class Node1BaseNode extends Node1 {

            @Child protected ValueNode left;
            @Child protected ValueNode right;
            @Child protected Node1BaseNode next0;

            Node1BaseNode(ValueNode left, ValueNode right) {
                super();
                this.left = adoptChild(left);
                this.right = adoptChild(right);
            }

            Node1BaseNode(Node1BaseNode copy) {
                this.left = adoptChild(copy.left);
                this.right = adoptChild(copy.right);
                this.next0 = adoptChild(copy.next0);
            }

            protected void setNext0(Node1BaseNode next0) {
                this.next0 = adoptChild(next0);
            }

            protected abstract boolean isCompatible0(Class<?> type);

            private Node1BaseNode createSpezialization0(Class<?> clazz) {
                Node1BaseNode node;
                if (clazz == Node1IntNode.class) {
                    node = new Node1IntNode(this);
                } else if (clazz == Node1IntBooleanBooleanNode.class) {
                    node = new Node1IntBooleanBooleanNode(this);
                } else if (clazz == Node1StringBooleanIntNode.class) {
                    node = new Node1StringBooleanIntNode(this);
                } else {
                    throw new AssertionError();
                }
                node.setNext0(new Node1UninitializedNode(this));
                return node;
            }

            protected abstract Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValueEvaluated, Object rightValueEvaluated);

            protected int executeCached0(VirtualFrame frameValue, Object leftValueEvaluated, Object rightValueEvaluated) throws UnexpectedResultException {
                Object leftValue = leftValueEvaluated;
                Object rightValue = rightValueEvaluated;
                return SIMPLETYPES.expectInteger(this.executeCachedGeneric0(frameValue, leftValue, rightValue));
            }

            protected Object executeCached1(VirtualFrame frameValue, boolean leftValueEvaluated, Object rightValueEvaluated) {
                Object leftValue = leftValueEvaluated;
                Object rightValue = rightValueEvaluated;
                return this.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

            protected Object executeCached2(VirtualFrame frameValue, Object leftValueEvaluated, int rightValueEvaluated) {
                Object leftValue = leftValueEvaluated;
                Object rightValue = rightValueEvaluated;
                return this.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftValue, Object rightValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == Node1UninitializedNode.class);
                String message = createInfo0(reason, leftValue, rightValue);
                allowed = allowed || (minimumState == Node1IntNode.class);
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    if (resultClass == null) {
                        result = super.add(leftValueCast, rightValueCast);
                        resultClass = Node1IntNode.class;
                    }
                    if (allowed) {
                        super.replace(new Node1IntNode(this), message);
                        return result;
                    }
                }
                allowed = allowed || (minimumState == Node1IntBooleanBooleanNode.class);
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isBoolean(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    boolean rightValueCast = SIMPLETYPES.asBoolean(rightValue);
                    if (resultClass == null) {
                        result = super.add(leftValueCast, rightValueCast);
                        resultClass = Node1IntBooleanBooleanNode.class;
                    }
                    if (allowed) {
                        super.replace(new Node1IntBooleanBooleanNode(this), message);
                        return result;
                    }
                }
                allowed = allowed || (minimumState == Node1StringBooleanIntNode.class);
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    if (resultClass == null) {
                        result = super.add(leftValueCast, rightValueCast);
                        resultClass = Node1StringBooleanIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new Node1StringBooleanIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", leftValue, rightValue));
                }
                Node1PolymorphicNode polymorphic = new Node1PolymorphicNode(this);
                this.left = null;
                this.right = null;
                super.replace(polymorphic, message);
                polymorphic.setNext0(this);
                setNext0(createSpezialization0(resultClass));
                polymorphic.optimizeTypes();
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftValue, Object rightValue) {
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isBoolean(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    boolean rightValueCast = SIMPLETYPES.asBoolean(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftValue, rightValue));
            }

            protected static String createInfo0(String message, Object leftValue, Object rightValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("leftValue").append(" = ").append(leftValue);
                    if (leftValue != null) {
                        builder.append(" (").append(leftValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("rightValue").append(" = ").append(rightValue);
                    if (rightValue != null) {
                        builder.append(" (").append(rightValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static class Node1PolymorphicNode extends Node1BaseNode {

            Node1PolymorphicNode(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return next0.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

            protected final void optimizeTypes() {
                if (isCompatible0(Node1Polymorphic0Node.class)) {
                    super.replace(new Node1Polymorphic0Node(this), "Optimized polymorphic types for (int, Object, Object)");
                } else if (isCompatible0(Node1Polymorphic1Node.class)) {
                    super.replace(new Node1Polymorphic1Node(this), "Optimized polymorphic types for (Object, boolean, Object)");
                } else if (isCompatible0(Node1Polymorphic2Node.class)) {
                    super.replace(new Node1Polymorphic2Node(this), "Optimized polymorphic types for (Object, Object, int)");
                } else if (isCompatible0(Node1PolymorphicNode.class)) {
                    super.replace(new Node1PolymorphicNode(this), "Optimized polymorphic types for (Object, Object, Object)");
                }
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                throw new AssertionError("Should not be reached.");
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                return type != getClass() && next0.isCompatible0(type);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static final class Node1Polymorphic0Node extends Node1PolymorphicNode {

            Node1Polymorphic0Node(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return next0.executeCached0(frameValue, leftValue, rightValue);
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SIMPLETYPES.expectBoolean(ex.getResult());
                }
                return SIMPLETYPES.expectBoolean(value);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static final class Node1Polymorphic1Node extends Node1PolymorphicNode {

            Node1Polymorphic1Node(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                boolean leftValue;
                try {
                    leftValue = this.left.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightValue = this.right.execute(frameValue);
                    return next0.executeCachedGeneric0(frameValue, ex.getResult(), rightValue);
                }
                Object rightValue = this.right.execute(frameValue);
                return next0.executeCached1(frameValue, leftValue, rightValue);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static final class Node1Polymorphic2Node extends Node1PolymorphicNode {

            Node1Polymorphic2Node(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object leftValue = this.left.execute(frameValue);
                int rightValue;
                try {
                    rightValue = this.right.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return next0.executeCachedGeneric0(frameValue, leftValue, ex.getResult());
                }
                return next0.executeCached2(frameValue, leftValue, rightValue);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class Node1UninitializedNode extends Node1BaseNode {

            Node1UninitializedNode(ValueNode left, ValueNode right) {
                super(left, right);
            }

            Node1UninitializedNode(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                return true;
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return super.executeAndSpecialize0(Node1UninitializedNode.class, frameValue, leftValue, rightValue, "Uninitialized monomorphic");
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                transferToInterpreter();
                Node searchNode = super.getParent();
                int depth = 0;
                while (searchNode != null) {
                    depth++;
                    searchNode = searchNode.getParent();
                    if (searchNode instanceof Node1PolymorphicNode) {
                        break;
                    }
                }
                assert searchNode instanceof Node1PolymorphicNode;
                Node1PolymorphicNode polymorphic = (Node1PolymorphicNode) searchNode;
                if (depth >= 3) {
                    searchNode.replace(new Node1GenericNode(this), "Polymorphic limit reached (3)");
                    return super.executeGeneric0(leftValue, rightValue);
                } else {
                    super.setNext0(new Node1UninitializedNode(this));
                    Object result = executeAndSpecialize0(Node1UninitializedNode.class, frameValue, leftValue, rightValue, "Uninitialized polymorphic (" + depth + "/3)");
                    polymorphic.optimizeTypes();
                    return result;
                }
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class Node1IntNode extends Node1BaseNode {

            Node1IntNode(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == Node1Polymorphic0Node.class || type == Node1Polymorphic2Node.class || type == Node1PolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftValue;
                try {
                    leftValue = this.left.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightValue = this.right.execute(frameValue);
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(Node1IntBooleanBooleanNode.class, frameValue, ex.getResult(), rightValue, "Expected leftValue instanceof int"));
                }
                int rightValue;
                try {
                    rightValue = this.right.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(Node1IntBooleanBooleanNode.class, frameValue, leftValue, ex.getResult(), "Expected rightValue instanceof int"));
                }
                return super.add(leftValue, rightValue);
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SIMPLETYPES.expectBoolean(ex.getResult());
                }
                return SIMPLETYPES.expectBoolean(value);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            protected int executeCached0(VirtualFrame frameValue, Object leftValue, Object rightValue) throws UnexpectedResultException {
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return this.next0.executeCached0(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCached2(VirtualFrame frameValue, Object leftValue, int rightValue) {
                if (SIMPLETYPES.isInteger(leftValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    return super.add(leftValueCast, rightValue);
                }
                return this.next0.executeCached2(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class Node1IntBooleanBooleanNode extends Node1BaseNode {

            Node1IntBooleanBooleanNode(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == Node1Polymorphic0Node.class || type == Node1Polymorphic1Node.class || type == Node1PolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                boolean leftValue;
                try {
                    leftValue = this.left.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightValue = this.right.execute(frameValue);
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(Node1StringBooleanIntNode.class, frameValue, ex.getResult(), rightValue, "Expected leftValue instanceof boolean"));
                }
                boolean rightValue;
                try {
                    rightValue = this.right.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(Node1StringBooleanIntNode.class, frameValue, leftValue, ex.getResult(), "Expected rightValue instanceof boolean"));
                }
                return super.add(leftValue, rightValue);
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SIMPLETYPES.expectBoolean(ex.getResult());
                }
                return SIMPLETYPES.expectBoolean(value);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            protected int executeCached0(VirtualFrame frameValue, Object leftValue, Object rightValue) throws UnexpectedResultException {
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isBoolean(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    boolean rightValueCast = SIMPLETYPES.asBoolean(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return this.next0.executeCached0(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCached1(VirtualFrame frameValue, boolean leftValue, Object rightValue) {
                if (SIMPLETYPES.isBoolean(rightValue)) {
                    boolean rightValueCast = SIMPLETYPES.asBoolean(rightValue);
                    return super.add(leftValue, rightValueCast);
                }
                return this.next0.executeCached1(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isBoolean(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    boolean rightValueCast = SIMPLETYPES.asBoolean(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class Node1StringBooleanIntNode extends Node1BaseNode {

            Node1StringBooleanIntNode(Node1BaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == Node1Polymorphic1Node.class || type == Node1Polymorphic2Node.class || type == Node1PolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public String executeString(VirtualFrame frameValue) throws UnexpectedResultException {
                boolean leftValue;
                try {
                    leftValue = this.left.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightValue = this.right.execute(frameValue);
                    return SIMPLETYPES.expectString(executeAndSpecialize0(Node1GenericNode.class, frameValue, ex.getResult(), rightValue, "Expected leftValue instanceof boolean"));
                }
                int rightValue;
                try {
                    rightValue = this.right.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectString(executeAndSpecialize0(Node1GenericNode.class, frameValue, leftValue, ex.getResult(), "Expected rightValue instanceof int"));
                }
                return super.add(leftValue, rightValue);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                String value;
                try {
                    value = this.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            protected Object executeCached1(VirtualFrame frameValue, boolean leftValue, Object rightValue) {
                if (SIMPLETYPES.isInteger(rightValue)) {
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValue, rightValueCast);
                }
                return this.next0.executeCached1(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCached2(VirtualFrame frameValue, Object leftValue, int rightValue) {
                if (SIMPLETYPES.isBoolean(leftValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    return super.add(leftValueCast, rightValue);
                }
                return this.next0.executeCached2(frameValue, leftValue, rightValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                if (SIMPLETYPES.isBoolean(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    boolean leftValueCast = SIMPLETYPES.asBoolean(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftValue, rightValue);
            }

        }
        @GeneratedBy(Node1.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class Node1GenericNode extends Node1BaseNode {

            Node1GenericNode(Node1BaseNode copy) {
                super(copy);
                this.next0 = null;
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                throw new AssertionError();
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return super.executeGeneric0(leftValue, rightValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftValue, Object rightValue) {
                throw new AssertionError("Should not be reached.");
            }

        }
    }
}
