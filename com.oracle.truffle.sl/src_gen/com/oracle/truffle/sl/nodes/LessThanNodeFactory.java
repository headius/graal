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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(LessThanNode.class)
public final class LessThanNodeFactory implements NodeFactory<LessThanNode> {

    private static LessThanNodeFactory instance;

    private LessThanNodeFactory() {
    }

    @Override
    public LessThanNode createNode(Object... arguments) {
        if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
            return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public LessThanNode createNodeGeneric(LessThanNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<LessThanNode> getNodeClass() {
        return LessThanNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
    }

    public static LessThanNode createGeneric(LessThanNode thisNode) {
        return new LessThanGenericNode((LessThanBaseNode) thisNode);
    }

    public static LessThanNode create(TypedNode leftNode, TypedNode rightNode) {
        return new LessThanUninitializedNode(leftNode, rightNode);
    }

    public static NodeFactory<LessThanNode> getInstance() {
        if (instance == null) {
            instance = new LessThanNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(LessThanNode.class)
    private abstract static class LessThanBaseNode extends LessThanNode {

        @Child protected TypedNode leftNode;
        @Child protected TypedNode rightNode;
        @Child protected LessThanBaseNode next0;

        LessThanBaseNode(TypedNode leftNode, TypedNode rightNode) {
            super();
            this.leftNode = adoptChild(leftNode);
            this.rightNode = adoptChild(rightNode);
        }

        LessThanBaseNode(LessThanBaseNode copy) {
            this.leftNode = adoptChild(copy.leftNode);
            this.rightNode = adoptChild(copy.rightNode);
            this.next0 = adoptChild(copy.next0);
        }

        protected void setNext0(LessThanBaseNode next0) {
            this.next0 = adoptChild(next0);
        }

        protected abstract boolean isCompatible0(Class<?> type);

        private LessThanBaseNode createSpezialization0(Class<?> clazz) {
            LessThanBaseNode node;
            if (clazz == LessThanIntNode.class) {
                node = new LessThanIntNode(this);
            } else if (clazz == LessThanBigIntegerNode.class) {
                node = new LessThanBigIntegerNode(this);
            } else if (clazz == LessThanObjectNode.class) {
                node = new LessThanObjectNode(this);
            } else {
                throw new AssertionError();
            }
            node.setNext0(new LessThanUninitializedNode(this));
            return node;
        }

        protected abstract Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated);

        protected boolean executeCached0(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) throws UnexpectedResultException {
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = rightNodeValueEvaluated;
            return SLTYPES.expectBoolean(this.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue));
        }

        @SuppressWarnings("unused")
        protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue, String reason) {
            neverPartOfCompilation();
            Object result = null;
            Class<?> resultClass = null;
            boolean allowed = (minimumState == LessThanUninitializedNode.class);
            String message = createInfo0(reason, leftNodeValue, rightNodeValue);
            allowed = allowed || (minimumState == LessThanIntNode.class);
            if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                if (resultClass == null) {
                    result = super.doInteger(leftNodeValueCast, rightNodeValueCast);
                    resultClass = LessThanIntNode.class;
                }
                if (allowed) {
                    super.replace(new LessThanIntNode(this), message);
                    return result;
                }
            }
            allowed = allowed || (minimumState == LessThanBigIntegerNode.class);
            if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                if (resultClass == null) {
                    result = super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
                    resultClass = LessThanBigIntegerNode.class;
                }
                if (allowed) {
                    super.replace(new LessThanBigIntegerNode(this), message);
                    return result;
                }
            }
            allowed = allowed || (minimumState == LessThanObjectNode.class);
            if (super.isString(leftNodeValue, rightNodeValue)) {
                if (resultClass == null) {
                    result = super.doString(leftNodeValue, rightNodeValue);
                    resultClass = LessThanObjectNode.class;
                }
                if (allowed) {
                    super.replace(new LessThanObjectNode(this), message);
                    return result;
                }
            }
            if (resultClass == null) {
                throw new UnsupportedOperationException(createInfo0("Unsupported values", leftNodeValue, rightNodeValue));
            }
            LessThanPolymorphicNode polymorphic = new LessThanPolymorphicNode(this);
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
                return super.doInteger(leftNodeValueCast, rightNodeValueCast);
            }
            if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                return super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
            }
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
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
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.POLYMORPHIC)
    private static class LessThanPolymorphicNode extends LessThanBaseNode {

        LessThanPolymorphicNode(LessThanBaseNode copy) {
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
            if (isCompatible0(LessThanPolymorphic0Node.class)) {
                super.replace(new LessThanPolymorphic0Node(this), "Optimized polymorphic types for (boolean, Object, Object)");
            } else if (isCompatible0(LessThanPolymorphicNode.class)) {
                super.replace(new LessThanPolymorphicNode(this), "Optimized polymorphic types for (Object, Object, Object)");
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
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.POLYMORPHIC)
    private static final class LessThanPolymorphic0Node extends LessThanPolymorphicNode {

        LessThanPolymorphic0Node(LessThanBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            try {
                this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                // ignore
            }
        }

        @Override
        public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                return SLTYPES.expectInteger(ex.getResult());
            }
            return SLTYPES.expectInteger(value);
        }

        @Override
        public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
            Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
            Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
            return next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
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
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class LessThanUninitializedNode extends LessThanBaseNode {

        LessThanUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
            super(leftNode, rightNode);
        }

        LessThanUninitializedNode(LessThanBaseNode copy) {
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
            return super.executeAndSpecialize0(LessThanUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
            return super.executeAndSpecialize0(LessThanUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(LessThanUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(LessThanUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
            transferToInterpreter();
            Node searchNode = super.getParent();
            int depth = 0;
            while (searchNode != null) {
                depth++;
                searchNode = searchNode.getParent();
                if (searchNode instanceof LessThanPolymorphicNode) {
                    break;
                }
            }
            assert searchNode instanceof LessThanPolymorphicNode;
            LessThanPolymorphicNode polymorphic = (LessThanPolymorphicNode) searchNode;
            if (depth >= 2) {
                searchNode.replace(new LessThanGenericNode(this), "Polymorphic limit reached (2)");
                return super.executeGeneric0(leftNodeValue, rightNodeValue);
            } else {
                super.setNext0(new LessThanUninitializedNode(this));
                Object result = executeAndSpecialize0(LessThanUninitializedNode.class, frameValue, leftNodeValue, rightNodeValue, "Uninitialized polymorphic (" + depth + "/2)");
                polymorphic.optimizeTypes();
                return result;
            }
        }

    }
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class LessThanIntNode extends LessThanBaseNode {

        LessThanIntNode(LessThanBaseNode copy) {
            super(copy);
        }

        @Override
        protected boolean isCompatible0(Class<?> type) {
            if (type == LessThanPolymorphic0Node.class || type == LessThanPolymorphicNode.class) {
                return next0.isCompatible0(type);
            }
            return false;
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            try {
                this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                // ignore
            }
        }

        @Override
        public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                return SLTYPES.expectInteger(ex.getResult());
            }
            return SLTYPES.expectInteger(value);
        }

        @Override
        public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
            int leftNodeValue;
            try {
                leftNodeValue = this.leftNode.executeInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return SLTYPES.expectBoolean(executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int"));
            }
            int rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return SLTYPES.expectBoolean(executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
            }
            return super.doInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
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
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
            }
            int rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            return super.doInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            int leftNodeValue;
            try {
                leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                Object rightNodeValue = rightNodeValueEvaluated;
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
            }
            int rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            return super.doInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            int leftNodeValue;
            try {
                leftNodeValue = SLTYPES.expectInteger(leftNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                Object rightNodeValue = rightNodeValueEvaluated;
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof int");
            }
            int rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanBigIntegerNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            return super.doInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        protected boolean executeCached0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) throws UnexpectedResultException {
            if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                return super.doInteger(leftNodeValueCast, rightNodeValueCast);
            }
            return this.next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
        }

        @Override
        protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
            if (SLTYPES.isInteger(leftNodeValue) && SLTYPES.isInteger(rightNodeValue)) {
                int leftNodeValueCast = SLTYPES.asInteger(leftNodeValue);
                int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                return super.doInteger(leftNodeValueCast, rightNodeValueCast);
            }
            return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
        }

    }
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class LessThanBigIntegerNode extends LessThanBaseNode {

        LessThanBigIntegerNode(LessThanBaseNode copy) {
            super(copy);
        }

        @Override
        protected boolean isCompatible0(Class<?> type) {
            if (type == LessThanPolymorphic0Node.class || type == LessThanPolymorphicNode.class) {
                return next0.isCompatible0(type);
            }
            return false;
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            try {
                this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                // ignore
            }
        }

        @Override
        public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                return SLTYPES.expectInteger(ex.getResult());
            }
            return SLTYPES.expectInteger(value);
        }

        @Override
        public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
            BigInteger leftNodeValue;
            try {
                leftNodeValue = this.leftNode.executeBigInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
                return SLTYPES.expectBoolean(executeAndSpecialize0(LessThanObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger"));
            }
            BigInteger rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeBigInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return SLTYPES.expectBoolean(executeAndSpecialize0(LessThanObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger"));
            }
            return super.doBigInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
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
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
            }
            BigInteger rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeBigInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
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
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
            }
            BigInteger rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
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
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, ex.getResult(), rightNodeValue, "Expected leftNodeValue instanceof BigInteger");
            }
            BigInteger rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectBigInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(LessThanObjectNode.class, frameValue, leftNodeValue, ex.getResult(), "Expected rightNodeValue instanceof BigInteger");
            }
            return super.doBigInteger(leftNodeValue, rightNodeValue);
        }

        @Override
        protected boolean executeCached0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) throws UnexpectedResultException {
            if (SLTYPES.isBigInteger(leftNodeValue) && SLTYPES.isBigInteger(rightNodeValue)) {
                BigInteger leftNodeValueCast = SLTYPES.asBigInteger(leftNodeValue);
                BigInteger rightNodeValueCast = SLTYPES.asBigInteger(rightNodeValue);
                return super.doBigInteger(leftNodeValueCast, rightNodeValueCast);
            }
            return this.next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
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
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class LessThanObjectNode extends LessThanBaseNode {

        LessThanObjectNode(LessThanBaseNode copy) {
            super(copy);
        }

        @Override
        protected boolean isCompatible0(Class<?> type) {
            if (type == LessThanPolymorphic0Node.class || type == LessThanPolymorphicNode.class) {
                return next0.isCompatible0(type);
            }
            return false;
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            try {
                this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                // ignore
            }
        }

        @Override
        public int executeInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                return SLTYPES.expectInteger(ex.getResult());
            }
            return SLTYPES.expectInteger(value);
        }

        @Override
        public boolean executeBoolean(VirtualFrame frameValue) throws UnexpectedResultException {
            Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
            Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
            }
            transferToInterpreter();
            return SLTYPES.expectBoolean(executeAndSpecialize0(LessThanGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed"));
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            boolean value;
            try {
                value = this.executeBoolean(frameValue);
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
                return super.doString(leftNodeValue, rightNodeValue);
            }
            transferToInterpreter();
            return executeAndSpecialize0(LessThanGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = rightNodeValueEvaluated;
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
            }
            transferToInterpreter();
            return executeAndSpecialize0(LessThanGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            Object leftNodeValue = leftNodeValueEvaluated;
            Object rightNodeValue = rightNodeValueEvaluated;
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
            }
            transferToInterpreter();
            return executeAndSpecialize0(LessThanGenericNode.class, frameValue, leftNodeValue, rightNodeValue, "One of guards [isString] failed");
        }

        @Override
        protected boolean executeCached0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) throws UnexpectedResultException {
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
            }
            return this.next0.executeCached0(frameValue, leftNodeValue, rightNodeValue);
        }

        @Override
        protected Object executeCachedGeneric0(VirtualFrame frameValue, Object leftNodeValue, Object rightNodeValue) {
            if (super.isString(leftNodeValue, rightNodeValue)) {
                return super.doString(leftNodeValue, rightNodeValue);
            }
            return this.next0.executeCachedGeneric0(frameValue, leftNodeValue, rightNodeValue);
        }

    }
    @GeneratedBy(LessThanNode.class)
    @NodeInfo(kind = Kind.GENERIC)
    private static final class LessThanGenericNode extends LessThanBaseNode {

        LessThanGenericNode(LessThanBaseNode copy) {
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
