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
package com.oracle.truffle.sl.nodes;

import static com.oracle.truffle.api.CompilerAsserts.*;
import static com.oracle.truffle.api.CompilerDirectives.*;
import static com.oracle.truffle.api.TruffleOptions.*;
import static com.oracle.truffle.sl.SLTypesGen.*;
import static java.util.Arrays.*;

import com.oracle.truffle.api.CompilerDirectives.SlowPath;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.sl.nodes.ArithmeticNode.AddNode;
import com.oracle.truffle.sl.nodes.ArithmeticNode.DivNode;
import com.oracle.truffle.sl.nodes.ArithmeticNode.MulNode;
import com.oracle.truffle.sl.nodes.ArithmeticNode.SubNode;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(ArithmeticNode.class)
public final class ArithmeticNodeFactory {

    private ArithmeticNodeFactory() {
    }

    public static List<NodeFactory<? extends ArithmeticNode>> getFactories() {
        return asList(AddNodeFactory.getInstance(), SubNodeFactory.getInstance(), DivNodeFactory.getInstance(), MulNodeFactory.getInstance());
    }

    @GeneratedBy(AddNode.class)
    public static final class AddNodeFactory implements NodeFactory<AddNode> {

        private static AddNodeFactory addNodeFactoryInstance;

        private AddNodeFactory() {
        }

        @Override
        public AddNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
                return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public AddNode createNodeGeneric(AddNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<AddNode> getNodeClass() {
            return AddNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
        }

        public static AddNode createGeneric(AddNode thisNode) {
            return new AddGenericNode((AddBaseNode) thisNode);
        }

        public static AddNode create(TypedNode leftNode, TypedNode rightNode) {
            return new AddUninitializedNode(leftNode, rightNode);
        }

        public static NodeFactory<AddNode> getInstance() {
            if (addNodeFactoryInstance == null) {
                addNodeFactoryInstance = new AddNodeFactory();
            }
            return addNodeFactoryInstance;
        }

        @GeneratedBy(AddNode.class)
        private abstract static class AddBaseNode extends AddNode {

            @Child protected TypedNode leftNode;
            @Child protected TypedNode rightNode;
            @Child protected AddBaseNode next0;

            AddBaseNode(TypedNode leftNode, TypedNode rightNode) {
                super();
                this.leftNode = adoptChild(leftNode);
                this.rightNode = adoptChild(rightNode);
            }

            AddBaseNode(AddBaseNode copy) {
                this.leftNode = adoptChild(copy.leftNode);
                this.rightNode = adoptChild(copy.rightNode);
                this.next0 = adoptChild(copy.next0);
            }

            protected void setNext0(AddBaseNode next0) {
                this.next0 = adoptChild(next0);
            }

            protected abstract boolean isCompatible0(Class<?> type);

            private AddBaseNode createSpezialization0(Class<?> clazz) {
                AddBaseNode node;
                if (clazz == AddIntNode.class) {
                    node = new AddIntNode(this);
                } else if (clazz == AddBigIntegerNode.class) {
                    node = new AddBigIntegerNode(this);
                } else if (clazz == AddStringNode.class) {
                    node = new AddStringNode(this);
                } else if (clazz == AddStringObjectObjectNode.class) {
                    node = new AddStringObjectObjectNode(this);
                } else {
                    throw new AssertionError();
                }
                node.setNext0(new AddUninitializedNode(this));
                return node;
            }

            protected abstract Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated);

            protected String executeCached0(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) throws UnexpectedResultException {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return SLTYPES.expectString(this.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue));
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == AddUninitializedNode.class);
                String message = createInfo0(reason, leftNodeValue, rightNodeValue);
                allowed = allowed || (minimumState == AddIntNode.class);
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        if (resultClass == null) {
                            result = super.doInt(leftNodeValueCast, rightNodeValueCast);
                            resultClass = AddIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new AddIntNode(this), message);
                            return result;
                        }
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                allowed = allowed || (minimumState == AddBigIntegerNode.class);
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    if (resultClass == null) {
                        result = super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
                        resultClass = AddBigIntegerNode.class;
                    }
                    if (allowed) {
                        super.replace(new AddBigIntegerNode(this), message);
                        return result;
                    }
                }
                allowed = allowed || (minimumState == AddStringNode.class);
                if (SLTYPES.isString(leftNodeValue) && SLTYPES.isString(rightNodeValue)) {
                    String leftNodeValueCast = SLTYPES.asString(leftNodeValue);
                    String rightNodeValueCast = SLTYPES.asString(rightNodeValue);
                    if (resultClass == null) {
                        result = super.doString(leftNodeValueCast, rightNodeValueCast);
                        resultClass = AddStringNode.class;
                    }
                    if (allowed) {
                        super.replace(new AddStringNode(this), message);
                        return result;
                    }
                }
                allowed = allowed || (minimumState == AddStringObjectObjectNode.class);
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    if (resultClass == null) {
                        result = super.add(leftNodeValue, rightNodeValue);
                        resultClass = AddStringObjectObjectNode.class;
                    }
                    if (allowed) {
                        super.replace(new AddStringObjectObjectNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
                }
                AddPolymorphicNode polymorphic = new AddPolymorphicNode(this);
                this.leftNode = null;
                this.rightNode = null;
                super.replace(polymorphic, message);
                polymorphic.setNext0(this);
                setNext0(createSpezialization0(resultClass));
                polymorphic.optimizeTypes();
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        return super.doInt(leftNodeValueCast, rightNodeValueCast);
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    return super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
                }
                if (SLTYPES.isString(leftNodeValue) && SLTYPES.isString(rightNodeValue)) {
                    String leftNodeValueCast = SLTYPES.asString(leftNodeValue);
                    String rightNodeValueCast = SLTYPES.asString(rightNodeValue);
                    return super.doString(leftNodeValueCast, rightNodeValueCast);
                }
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
            }

            protected static String createInfo0(String message, Object leftNodeValue, Object rightNodeValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("leftNodeValue").append(" = ").append(leftNodeValue);
                    if (leftNodeValue != null) {
                        builder.append(" (").append(leftNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("rightNodeValue").append(" = ").append(rightNodeValue);
                    if (rightNodeValue != null) {
                        builder.append(" (").append(rightNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static class AddPolymorphicNode extends AddBaseNode {

            AddPolymorphicNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

            protected final void optimizeTypes() {
                if (isCompatible0(AddPolymorphic0Node.class)) {
                    super.replace(new AddPolymorphic0Node(this), "Optimized polymorphic types for (String, Object, Object)");
                } else if (isCompatible0(AddPolymorphicNode.class)) {
                    super.replace(new AddPolymorphicNode(this), "Optimized polymorphic types for (Object, Object, Object)");
                }
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                throw new AssertionError("Should not be reached.");
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                return type != getClass() && next0.isCompatible0(type);
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.POLYMORPHIC)
        private static final class AddPolymorphic0Node extends AddPolymorphicNode {

            AddPolymorphic0Node(AddBaseNode copy) {
                super(copy);
            }

            @Override
            public String executeString(VirtualFrame frameValue) throws UnexpectedResultException {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                String value;
                try {
                    value = this.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                try {
                    return next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                try {
                    return next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                try {
                    return next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class AddUninitializedNode extends AddBaseNode {

            AddUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
                super(leftNode, rightNode);
            }

            AddUninitializedNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                return true;
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                transferToInterpreter();
                Node searchNode = super.getParent();
                int depth = 0;
                while (searchNode != null) {
                    depth++;
                    searchNode = searchNode.getParent();
                    if (searchNode instanceof AddPolymorphicNode) {
                        break;
                    }
                }
                assert searchNode instanceof AddPolymorphicNode;
                AddPolymorphicNode polymorphic = (AddPolymorphicNode) searchNode;
                if (depth >= 3) {
                    searchNode.replace(new AddGenericNode(this), "Polymorphic limit reached (3)");
                    return super.executeGeneric0(leftNodeValue, rightNodeValue);
                } else {
                    super.setNext0(new AddUninitializedNode(this));
                    Object result = executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized polymorphic (" + depth + "/3)");
                    polymorphic.optimizeTypes();
                    return result;
                }
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class AddIntNode extends AddBaseNode {

            AddIntNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == AddPolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public void executeVoid(VirtualFrame frameValue) {
                try {
                    this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    // ignore
                }
            }

            @Override
            public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectInteger(executeAndSpecialize0(AddBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int"));
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
                }
                try {
                    return super.doInt(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException"));
                }
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SLTYPES.expectBoolean(ex.getResult());
                }
                return SLTYPES.expectBoolean(value);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.doInt(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.doInt(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.doInt(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        return super.doInt(leftNodeValueCast, rightNodeValueCast);
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class AddBigIntegerNode extends AddBaseNode {

            AddBigIntegerNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == AddPolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(AddStringNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger"));
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(AddStringNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger"));
                }
                return super.doBigInteger(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                BigInteger value;
                try {
                    value = this.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(AddStringNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.doBigInteger(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddStringNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.doBigInteger(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddStringNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.doBigInteger(leftNodeValue, rightNodeValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    return super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class AddStringNode extends AddBaseNode {

            AddStringNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == AddPolymorphic0Node.class || type == AddPolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public String executeString(VirtualFrame frameValue) throws UnexpectedResultException {
                String leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectString(executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof String"));
                }
                String rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectString(executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof String"));
                }
                return super.doString(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                String value;
                try {
                    value = this.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                String leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectString(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof String");
                }
                String rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof String");
                }
                return super.doString(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                String leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectString(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof String");
                }
                String rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectString(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof String");
                }
                return super.doString(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                String leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectString(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof String");
                }
                String rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectString(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(AddStringObjectObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof String");
                }
                return super.doString(leftNodeValue, rightNodeValue);
            }

            @Override
            protected String executeCached0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) throws UnexpectedResultException {
                if (SLTYPES.isString(leftNodeValue) && SLTYPES.isString(rightNodeValue)) {
                    String leftNodeValueCast = SLTYPES.asString(leftNodeValue);
                    String rightNodeValueCast = SLTYPES.asString(rightNodeValue);
                    return super.doString(leftNodeValueCast, rightNodeValueCast);
                }
                return this.next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isString(leftNodeValue) && SLTYPES.isString(rightNodeValue)) {
                    String leftNodeValueCast = SLTYPES.asString(leftNodeValue);
                    String rightNodeValueCast = SLTYPES.asString(rightNodeValue);
                    return super.doString(leftNodeValueCast, rightNodeValueCast);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class AddStringObjectObjectNode extends AddBaseNode {

            AddStringObjectObjectNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                if (type == AddPolymorphic0Node.class || type == AddPolymorphicNode.class) {
                    return next0.isCompatible0(type);
                }
                return false;
            }

            @Override
            public String executeString(VirtualFrame frameValue) throws UnexpectedResultException {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                transferToInterpreter();
                return SLTYPES.expectString(executeAndSpecialize0(AddGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed"));
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                String value;
                try {
                    value = this.executeString(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                transferToInterpreter();
                return executeAndSpecialize0(AddGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                transferToInterpreter();
                return executeAndSpecialize0(AddGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                transferToInterpreter();
                return executeAndSpecialize0(AddGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
            }

            @Override
            protected String executeCached0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) throws UnexpectedResultException {
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                return this.next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                if (super.isString(leftNodeValue, rightNodeValue)) {
                    return super.add(leftNodeValue, rightNodeValue);
                }
                return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class AddGenericNode extends AddBaseNode {

            AddGenericNode(AddBaseNode copy) {
                super(copy);
                this.next0 = null;
            }

            @Override
            protected boolean isCompatible0(Class<?> type) {
                throw new AssertionError();
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
                throw new AssertionError("Should not be reached.");
            }

        }
    }
    @GeneratedBy(SubNode.class)
    public static final class SubNodeFactory implements NodeFactory<SubNode> {

        private static SubNodeFactory subNodeFactoryInstance;

        private SubNodeFactory() {
        }

        @Override
        public SubNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
                return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public SubNode createNodeGeneric(SubNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<SubNode> getNodeClass() {
            return SubNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
        }

        public static SubNode createGeneric(SubNode thisNode) {
            return new SubGenericNode((SubBaseNode) thisNode);
        }

        public static SubNode create(TypedNode leftNode, TypedNode rightNode) {
            return new SubUninitializedNode(leftNode, rightNode);
        }

        public static NodeFactory<SubNode> getInstance() {
            if (subNodeFactoryInstance == null) {
                subNodeFactoryInstance = new SubNodeFactory();
            }
            return subNodeFactoryInstance;
        }

        @GeneratedBy(SubNode.class)
        private abstract static class SubBaseNode extends SubNode {

            @Child protected TypedNode leftNode;
            @Child protected TypedNode rightNode;

            SubBaseNode(TypedNode leftNode, TypedNode rightNode) {
                super();
                this.leftNode = adoptChild(leftNode);
                this.rightNode = adoptChild(rightNode);
            }

            SubBaseNode(SubBaseNode copy) {
                this.leftNode = adoptChild(copy.leftNode);
                this.rightNode = adoptChild(copy.rightNode);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == SubUninitializedNode.class);
                String message = createInfo0(reason, leftNodeValue, rightNodeValue);
                allowed = allowed || (minimumState == SubIntNode.class);
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        if (resultClass == null) {
                            result = super.sub(leftNodeValueCast, rightNodeValueCast);
                            resultClass = SubIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new SubIntNode(this), message);
                            return result;
                        }
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                allowed = allowed || (minimumState == SubBigIntegerNode.class);
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    if (resultClass == null) {
                        result = super.sub(leftNodeValueCast, rightNodeValueCast);
                        resultClass = SubBigIntegerNode.class;
                    }
                    if (allowed) {
                        super.replace(new SubBigIntegerNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
                }
                super.replace(new SubGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        return super.sub(leftNodeValueCast, rightNodeValueCast);
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    return super.sub(leftNodeValueCast, rightNodeValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
            }

            protected static String createInfo0(String message, Object leftNodeValue, Object rightNodeValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("leftNodeValue").append(" = ").append(leftNodeValue);
                    if (leftNodeValue != null) {
                        builder.append(" (").append(leftNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("rightNodeValue").append(" = ").append(rightNodeValue);
                    if (rightNodeValue != null) {
                        builder.append(" (").append(rightNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(SubNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class SubUninitializedNode extends SubBaseNode {

            SubUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
                super(leftNode, rightNode);
            }

            SubUninitializedNode(SubBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(SubUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(SubUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(SubUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(SubUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(SubNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class SubIntNode extends SubBaseNode {

            SubIntNode(SubBaseNode copy) {
                super(copy);
            }

            @Override
            public void executeVoid(VirtualFrame frameValue) {
                try {
                    this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    // ignore
                }
            }

            @Override
            public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectInteger(executeAndSpecialize0(SubBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int"));
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
                }
                try {
                    return super.sub(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException"));
                }
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SLTYPES.expectBoolean(ex.getResult());
                }
                return SLTYPES.expectBoolean(value);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.sub(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.sub(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.sub(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

        }
        @GeneratedBy(SubNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class SubBigIntegerNode extends SubBaseNode {

            SubBigIntegerNode(SubBaseNode copy) {
                super(copy);
            }

            @Override
            public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(SubGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger"));
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(SubGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger"));
                }
                return super.sub(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                BigInteger value;
                try {
                    value = this.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.sub(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.sub(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(SubGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.sub(leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(SubNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class SubGenericNode extends SubBaseNode {

            SubGenericNode(SubBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

        }
    }
    @GeneratedBy(DivNode.class)
    public static final class DivNodeFactory implements NodeFactory<DivNode> {

        private static DivNodeFactory divNodeFactoryInstance;

        private DivNodeFactory() {
        }

        @Override
        public DivNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
                return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public DivNode createNodeGeneric(DivNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<DivNode> getNodeClass() {
            return DivNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
        }

        public static DivNode createGeneric(DivNode thisNode) {
            return new DivGenericNode((DivBaseNode) thisNode);
        }

        public static DivNode create(TypedNode leftNode, TypedNode rightNode) {
            return new DivUninitializedNode(leftNode, rightNode);
        }

        public static NodeFactory<DivNode> getInstance() {
            if (divNodeFactoryInstance == null) {
                divNodeFactoryInstance = new DivNodeFactory();
            }
            return divNodeFactoryInstance;
        }

        @GeneratedBy(DivNode.class)
        private abstract static class DivBaseNode extends DivNode {

            @Child protected TypedNode leftNode;
            @Child protected TypedNode rightNode;

            DivBaseNode(TypedNode leftNode, TypedNode rightNode) {
                super();
                this.leftNode = adoptChild(leftNode);
                this.rightNode = adoptChild(rightNode);
            }

            DivBaseNode(DivBaseNode copy) {
                this.leftNode = adoptChild(copy.leftNode);
                this.rightNode = adoptChild(copy.rightNode);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == DivUninitializedNode.class);
                String message = createInfo0(reason, leftNodeValue, rightNodeValue);
                allowed = allowed || (minimumState == DivIntNode.class);
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        if (resultClass == null) {
                            result = super.div(leftNodeValueCast, rightNodeValueCast);
                            resultClass = DivIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new DivIntNode(this), message);
                            return result;
                        }
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                allowed = allowed || (minimumState == DivBigIntegerNode.class);
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    if (resultClass == null) {
                        result = super.div(leftNodeValueCast, rightNodeValueCast);
                        resultClass = DivBigIntegerNode.class;
                    }
                    if (allowed) {
                        super.replace(new DivBigIntegerNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
                }
                super.replace(new DivGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        return super.div(leftNodeValueCast, rightNodeValueCast);
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    return super.div(leftNodeValueCast, rightNodeValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
            }

            protected static String createInfo0(String message, Object leftNodeValue, Object rightNodeValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("leftNodeValue").append(" = ").append(leftNodeValue);
                    if (leftNodeValue != null) {
                        builder.append(" (").append(leftNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("rightNodeValue").append(" = ").append(rightNodeValue);
                    if (rightNodeValue != null) {
                        builder.append(" (").append(rightNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(DivNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class DivUninitializedNode extends DivBaseNode {

            DivUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
                super(leftNode, rightNode);
            }

            DivUninitializedNode(DivBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(DivUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(DivUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(DivUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(DivUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(DivNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class DivIntNode extends DivBaseNode {

            DivIntNode(DivBaseNode copy) {
                super(copy);
            }

            @Override
            public void executeVoid(VirtualFrame frameValue) {
                try {
                    this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    // ignore
                }
            }

            @Override
            public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectInteger(executeAndSpecialize0(DivBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int"));
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
                }
                try {
                    return super.div(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException"));
                }
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SLTYPES.expectBoolean(ex.getResult());
                }
                return SLTYPES.expectBoolean(value);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.div(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.div(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.div(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

        }
        @GeneratedBy(DivNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class DivBigIntegerNode extends DivBaseNode {

            DivBigIntegerNode(DivBaseNode copy) {
                super(copy);
            }

            @Override
            public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(DivGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger"));
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(DivGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger"));
                }
                return super.div(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                BigInteger value;
                try {
                    value = this.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.div(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.div(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(DivGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.div(leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(DivNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class DivGenericNode extends DivBaseNode {

            DivGenericNode(DivBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

        }
    }
    @GeneratedBy(MulNode.class)
    public static final class MulNodeFactory implements NodeFactory<MulNode> {

        private static MulNodeFactory mulNodeFactoryInstance;

        private MulNodeFactory() {
        }

        @Override
        public MulNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
                return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public MulNode createNodeGeneric(MulNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<MulNode> getNodeClass() {
            return MulNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
        }

        public static MulNode createGeneric(MulNode thisNode) {
            return new MulGenericNode((MulBaseNode) thisNode);
        }

        public static MulNode create(TypedNode leftNode, TypedNode rightNode) {
            return new MulUninitializedNode(leftNode, rightNode);
        }

        public static NodeFactory<MulNode> getInstance() {
            if (mulNodeFactoryInstance == null) {
                mulNodeFactoryInstance = new MulNodeFactory();
            }
            return mulNodeFactoryInstance;
        }

        @GeneratedBy(MulNode.class)
        private abstract static class MulBaseNode extends MulNode {

            @Child protected TypedNode leftNode;
            @Child protected TypedNode rightNode;

            MulBaseNode(TypedNode leftNode, TypedNode rightNode) {
                super();
                this.leftNode = adoptChild(leftNode);
                this.rightNode = adoptChild(rightNode);
            }

            MulBaseNode(MulBaseNode copy) {
                this.leftNode = adoptChild(copy.leftNode);
                this.rightNode = adoptChild(copy.rightNode);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == MulUninitializedNode.class);
                String message = createInfo0(reason, leftNodeValue, rightNodeValue);
                allowed = allowed || (minimumState == MulIntNode.class);
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        if (resultClass == null) {
                            result = super.mul(leftNodeValueCast, rightNodeValueCast);
                            resultClass = MulIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new MulIntNode(this), message);
                            return result;
                        }
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                allowed = allowed || (minimumState == MulBigIntegerNode.class);
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    if (resultClass == null) {
                        result = super.mul(leftNodeValueCast, rightNodeValueCast);
                        resultClass = MulBigIntegerNode.class;
                    }
                    if (allowed) {
                        super.replace(new MulBigIntegerNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
                }
                super.replace(new MulGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftNodeValue, Object rightNodeValue) {
                if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                    int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                    int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                    try {
                        return super.mul(leftNodeValueCast, rightNodeValueCast);
                    } catch (ArithmeticException rewriteEx) {
                        // fall through
                    }
                }
                if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                    BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                    BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                    return super.mul(leftNodeValueCast, rightNodeValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
            }

            protected static String createInfo0(String message, Object leftNodeValue, Object rightNodeValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("leftNodeValue").append(" = ").append(leftNodeValue);
                    if (leftNodeValue != null) {
                        builder.append(" (").append(leftNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("rightNodeValue").append(" = ").append(rightNodeValue);
                    if (rightNodeValue != null) {
                        builder.append(" (").append(rightNodeValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(MulNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class MulUninitializedNode extends MulBaseNode {

            MulUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
                super(leftNode, rightNode);
            }

            MulUninitializedNode(MulBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(MulUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeAndSpecialize0(MulUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(MulUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                transferToInterpreter();
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeAndSpecialize0(MulUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(MulNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class MulIntNode extends MulBaseNode {

            MulIntNode(MulBaseNode copy) {
                super(copy);
            }

            @Override
            public void executeVoid(VirtualFrame frameValue) {
                try {
                    this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    // ignore
                }
            }

            @Override
            public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectInteger(executeAndSpecialize0(MulBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int"));
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
                }
                try {
                    return super.mul(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException"));
                }
            }

            @Override
            public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return SLTYPES.expectBoolean(ex.getResult());
                }
                return SLTYPES.expectBoolean(value);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                int value;
                try {
                    value = this.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.mul(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.mul(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                int leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
                }
                int rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
                }
                try {
                    return super.mul(leftNodeValue, rightNodeValue);
                } catch (ArithmeticException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulBigIntegerNode.class, frameValue, leftNodeValue, rightNodeValue, "Thrown ArithmeticException");
                }
            }

        }
        @GeneratedBy(MulNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class MulBigIntegerNode extends MulBaseNode {

            MulBigIntegerNode(MulBaseNode copy) {
                super(copy);
            }

            @Override
            public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = this.leftNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(MulGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger"));
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(MulGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger"));
                }
                return super.mul(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                BigInteger value;
                try {
                    value = this.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = this.rightNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.mul(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.mul(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                BigInteger leftNodeValue;
                try {
                    leftNodeValue = SLTYPES.expectBigInteger(leftNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightNodeValue = rightNodeValueEvaluated;
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
                }
                BigInteger rightNodeValue;
                try {
                    rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(MulGenericNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
                }
                return super.mul(leftNodeValue, rightNodeValue);
            }

        }
        @GeneratedBy(MulNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class MulGenericNode extends MulBaseNode {

            MulGenericNode(MulBaseNode copy) {
                super(copy);
            }

            @Override
            public Object executeGeneric(VirtualFrame frameValue) {
                Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
                Object leftNodeValue = leftNodeValueEvaluated;
                Object rightNodeValue = rightNodeValueEvaluated;
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            }

        }
    }
}
