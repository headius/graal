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
import java.util.Arrays;
import java.util.List;

@GeneratedBy(LogicalAndNode.class)
public final class LogicalAndNodeFactory implements NodeFactory<LogicalAndNode> {

    private static LogicalAndNodeFactory instance;

    private LogicalAndNodeFactory() {
    }

    @Override
    public LogicalAndNode createNode(Object... arguments) {
        if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof TypedNode) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
            return create((TypedNode) arguments[0], (TypedNode) arguments[1]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public LogicalAndNode createNodeGeneric(LogicalAndNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<LogicalAndNode> getNodeClass() {
        return LogicalAndNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(TypedNode.class, TypedNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(TypedNode.class, TypedNode.class);
    }

    public static LogicalAndNode createGeneric(LogicalAndNode thisNode) {
        return new LogicalAndGenericNode((LogicalAndBaseNode) thisNode);
    }

    public static LogicalAndNode create(TypedNode leftNode, TypedNode rightNode) {
        return new LogicalAndUninitializedNode(leftNode, rightNode);
    }

    public static NodeFactory<LogicalAndNode> getInstance() {
        if (instance == null) {
            instance = new LogicalAndNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(LogicalAndNode.class)
    private abstract static class LogicalAndBaseNode extends LogicalAndNode {

        @Child protected TypedNode leftNode;
        @Child protected TypedNode rightNode;

        LogicalAndBaseNode(TypedNode leftNode, TypedNode rightNode) {
            super();
            this.leftNode = adoptChild(leftNode);
            this.rightNode = adoptChild(rightNode);
        }

        LogicalAndBaseNode(LogicalAndBaseNode copy) {
            this.leftNode = adoptChild(copy.leftNode);
            this.rightNode = adoptChild(copy.rightNode);
        }

        @SuppressWarnings("unused")
        protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftNodeValue, boolean hasRightNodeValue, Object rightNodeValue, String reason) {
            neverPartOfCompilation();
            Object result = null;
            Class<?> resultClass = null;
            boolean allowed = (minimumState == LogicalAndUninitializedNode.class);
            String message = createInfo0(reason, leftNodeValue, hasRightNodeValue, rightNodeValue);
            allowed = allowed || (minimumState == LogicalAndBooleanNode.class);
            if (SLTYPES.isBoolean(leftNodeValue) && (!hasRightNodeValue || SLTYPES.isBoolean(rightNodeValue))) {
                boolean leftNodeValueCast = SLTYPES.asBoolean(leftNodeValue);
                boolean rightNodeValueCast = false;
                if (hasRightNodeValue) {
                    rightNodeValueCast = SLTYPES.asBoolean(rightNodeValue);
                }
                if (resultClass == null) {
                    result = super.doBoolean(leftNodeValueCast, hasRightNodeValue, rightNodeValueCast);
                    resultClass = LogicalAndBooleanNode.class;
                }
                if (allowed) {
                    super.replace(new LogicalAndBooleanNode(this), message);
                    return result;
                }
            }
            if (resultClass == null) {
                result = super.doGeneric(leftNodeValue, hasRightNodeValue, rightNodeValue);
                resultClass = LogicalAndGenericNode.class;
            }
            super.replace(new LogicalAndGenericNode(this), message);
            return result;
        }

        @SlowPath
        protected Object executeGeneric0(Object leftNodeValue, boolean hasRightNodeValue, Object rightNodeValue) {
            if (SLTYPES.isBoolean(leftNodeValue) && (!hasRightNodeValue || SLTYPES.isBoolean(rightNodeValue))) {
                boolean leftNodeValueCast = SLTYPES.asBoolean(leftNodeValue);
                boolean rightNodeValueCast = false;
                if (hasRightNodeValue) {
                    rightNodeValueCast = SLTYPES.asBoolean(rightNodeValue);
                }
                return super.doBoolean(leftNodeValueCast, hasRightNodeValue, rightNodeValueCast);
            }
            return super.doGeneric(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @SuppressWarnings("unused")
        protected static String createInfo0(String message, Object leftNodeValue, boolean hasRightNodeValue, Object rightNodeValue) {
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
    @GeneratedBy(LogicalAndNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class LogicalAndUninitializedNode extends LogicalAndBaseNode {

        LogicalAndUninitializedNode(TypedNode leftNode, TypedNode rightNode) {
            super(leftNode, rightNode);
        }

        LogicalAndUninitializedNode(LogicalAndBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            transferToInterpreter();
            Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = null;
            if (hasRightNodeValue) {
                rightNodeValue = this.rightNode.executeGeneric(frameValue);
            }
            return super.executeAndSpecialize0(LogicalAndUninitializedNode.class, frameValue, leftNodeValue, hasRightNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = null;
            if (hasRightNodeValue) {
                rightNodeValue = this.rightNode.executeGeneric(frameValue);
            }
            return super.executeAndSpecialize0(LogicalAndUninitializedNode.class, frameValue, leftNodeValue, hasRightNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(LogicalAndUninitializedNode.class, frameValue, leftNodeValue, hasRightNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            transferToInterpreter();
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(LogicalAndUninitializedNode.class, frameValue, leftNodeValue, hasRightNodeValue, rightNodeValue, "Uninitialized monomorphic");
        }

    }
    @GeneratedBy(LogicalAndNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class LogicalAndBooleanNode extends LogicalAndBaseNode {

        LogicalAndBooleanNode(LogicalAndBaseNode copy) {
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
            boolean leftNodeValue;
            try {
                leftNodeValue = this.leftNode.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                boolean hasRightNodeValue = super.needsRightNode(ex.getResult());
                Object rightNodeValue = null;
                if (hasRightNodeValue) {
                    rightNodeValue = this.rightNode.executeGeneric(frameValue);
                }
                return SLTYPES.expectBoolean(executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, ex.getResult(), hasRightNodeValue, rightNodeValue, "Expected leftNodeValue instanceof boolean"));
            }
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            boolean rightNodeValue = false;
            if (hasRightNodeValue) {
                try {
                    rightNodeValue = this.rightNode.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBoolean(executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, leftNodeValue, hasRightNodeValue, ex.getResult(), "Expected rightNodeValue instanceof boolean"));
                }
            }
            return super.doBoolean(leftNodeValue, hasRightNodeValue, rightNodeValue);
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
            boolean leftNodeValue;
            try {
                leftNodeValue = SLTYPES.expectBoolean(leftNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                boolean hasRightNodeValue = super.needsRightNode(ex.getResult());
                Object rightNodeValue = null;
                if (hasRightNodeValue) {
                    rightNodeValue = this.rightNode.executeGeneric(frameValue);
                }
                return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, ex.getResult(), hasRightNodeValue, rightNodeValue, "Expected leftNodeValue instanceof boolean");
            }
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            boolean rightNodeValue = false;
            if (hasRightNodeValue) {
                try {
                    rightNodeValue = this.rightNode.executeBoolean(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, leftNodeValue, hasRightNodeValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
                }
            }
            return super.doBoolean(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            boolean leftNodeValue;
            try {
                leftNodeValue = SLTYPES.expectBoolean(leftNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                boolean hasRightNodeValue = super.needsRightNode(ex.getResult());
                Object rightNodeValue = rightNodeValueEvaluated;
                return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, ex.getResult(), hasRightNodeValue, rightNodeValue, "Expected leftNodeValue instanceof boolean");
            }
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            boolean rightNodeValue = false;
            if (hasRightNodeValue) {
                try {
                    rightNodeValue = SLTYPES.expectBoolean(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, leftNodeValue, hasRightNodeValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
                }
            }
            return super.doBoolean(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            boolean leftNodeValue;
            try {
                leftNodeValue = SLTYPES.expectBoolean(leftNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                boolean hasRightNodeValue = super.needsRightNode(ex.getResult());
                Object rightNodeValue = rightNodeValueEvaluated;
                return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, ex.getResult(), hasRightNodeValue, rightNodeValue, "Expected leftNodeValue instanceof boolean");
            }
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            boolean rightNodeValue = false;
            if (hasRightNodeValue) {
                try {
                    rightNodeValue = SLTYPES.expectBoolean(rightNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(LogicalAndGenericNode.class, frameValue, leftNodeValue, hasRightNodeValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
                }
            }
            return super.doBoolean(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

    }
    @GeneratedBy(LogicalAndNode.class)
    @NodeInfo(kind = Kind.GENERIC)
    private static final class LogicalAndGenericNode extends LogicalAndBaseNode {

        LogicalAndGenericNode(LogicalAndBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            Object leftNodeValue = this.leftNode.executeGeneric(frameValue);
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = null;
            if (hasRightNodeValue) {
                rightNodeValue = this.rightNode.executeGeneric(frameValue);
            }
            return super.executeGeneric0(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated) {
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = null;
            if (hasRightNodeValue) {
                rightNodeValue = this.rightNode.executeGeneric(frameValue);
            }
            return super.executeGeneric0(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated) {
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeGeneric0(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object leftNodeValueEvaluated, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            Object leftNodeValue = leftNodeValueEvaluated;
            boolean hasRightNodeValue = super.needsRightNode(leftNodeValue);
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeGeneric0(leftNodeValue, hasRightNodeValue, rightNodeValue);
        }

    }
}
