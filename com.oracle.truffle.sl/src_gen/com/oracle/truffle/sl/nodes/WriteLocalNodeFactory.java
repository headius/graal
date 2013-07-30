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

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(WriteLocalNode.class)
public final class WriteLocalNodeFactory implements NodeFactory<WriteLocalNode> {

    private static WriteLocalNodeFactory instance;

    private WriteLocalNodeFactory() {
    }

    @Override
    public WriteLocalNode createNode(Object... arguments) {
        if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof FrameSlot) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
            return create((FrameSlot) arguments[0], (TypedNode) arguments[1]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public WriteLocalNode createNodeGeneric(WriteLocalNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<WriteLocalNode> getNodeClass() {
        return WriteLocalNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(FrameSlot.class, TypedNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(TypedNode.class);
    }

    public static WriteLocalNode createGeneric(WriteLocalNode thisNode) {
        return new WriteLocalObjectNode((WriteLocalBaseNode) thisNode);
    }

    public static WriteLocalNode create(FrameSlot slot, TypedNode rightNode) {
        return new WriteLocalUninitializedNode(slot, rightNode);
    }

    public static NodeFactory<WriteLocalNode> getInstance() {
        if (instance == null) {
            instance = new WriteLocalNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(WriteLocalNode.class)
    private abstract static class WriteLocalBaseNode extends WriteLocalNode {

        @Child protected TypedNode rightNode;

        WriteLocalBaseNode(FrameSlot slot, TypedNode rightNode) {
            super(slot);
            this.rightNode = adoptChild(rightNode);
        }

        WriteLocalBaseNode(WriteLocalBaseNode copy) {
            super(copy);
            this.rightNode = adoptChild(copy.rightNode);
        }

        protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object rightNodeValue, String reason) {
            neverPartOfCompilation();
            Object result = null;
            Class<?> resultClass = null;
            boolean allowed = (minimumState == WriteLocalUninitializedNode.class);
            String message = createInfo0(reason, frameValue, rightNodeValue);
            allowed = allowed || (minimumState == WriteLocalIntNode.class);
            if (SLTYPES.isInteger(rightNodeValue)) {
                int rightNodeValueCast = SLTYPES.asInteger(rightNodeValue);
                try {
                    if (resultClass == null) {
                        result = super.write(frameValue, rightNodeValueCast);
                        resultClass = WriteLocalIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new WriteLocalIntNode(this), message);
                        return result;
                    }
                } catch (FrameSlotTypeException rewriteEx) {
                    // fall through
                }
            }
            allowed = allowed || (minimumState == WriteLocalBooleanNode.class);
            if (SLTYPES.isBoolean(rightNodeValue)) {
                boolean rightNodeValueCast = SLTYPES.asBoolean(rightNodeValue);
                try {
                    if (resultClass == null) {
                        result = super.write(frameValue, rightNodeValueCast);
                        resultClass = WriteLocalBooleanNode.class;
                    }
                    if (allowed) {
                        super.replace(new WriteLocalBooleanNode(this), message);
                        return result;
                    }
                } catch (FrameSlotTypeException rewriteEx) {
                    // fall through
                }
            }
            allowed = allowed || (minimumState == WriteLocalObjectNode.class);
            if (resultClass == null) {
                result = super.writeGeneric(frameValue, rightNodeValue);
                resultClass = WriteLocalObjectNode.class;
            }
            super.replace(new WriteLocalObjectNode(this), message);
            return result;
            // unreachable Generic
        }

        @SuppressWarnings("unused")
        protected static String createInfo0(String message, VirtualFrame frameValue, Object rightNodeValue) {
            if (DetailedRewriteReasons) {
                StringBuilder builder = new StringBuilder(message);
                builder.append(" (");
                builder.append("rightNodeValue").append(" = ").append(rightNodeValue);
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
    @GeneratedBy(WriteLocalNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class WriteLocalUninitializedNode extends WriteLocalBaseNode {

        WriteLocalUninitializedNode(FrameSlot slot, TypedNode rightNode) {
            super(slot, rightNode);
        }

        WriteLocalUninitializedNode(WriteLocalBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            transferToInterpreter();
            Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
            return super.executeAndSpecialize0(WriteLocalUninitializedNode.class, frameValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated) {
            transferToInterpreter();
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(WriteLocalUninitializedNode.class, frameValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            transferToInterpreter();
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(WriteLocalUninitializedNode.class, frameValue, rightNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            transferToInterpreter();
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.executeAndSpecialize0(WriteLocalUninitializedNode.class, frameValue, rightNodeValue, "Uninitialized monomorphic");
        }

    }
    @GeneratedBy(WriteLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class WriteLocalIntNode extends WriteLocalBaseNode {

        WriteLocalIntNode(WriteLocalBaseNode copy) {
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
            int rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return SLTYPES.expectInteger(executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof int"));
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return SLTYPES.expectInteger(executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException"));
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
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated) {
            int rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            int rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            int rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectInteger(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof int");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalBooleanNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

    }
    @GeneratedBy(WriteLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class WriteLocalBooleanNode extends WriteLocalBaseNode {

        WriteLocalBooleanNode(WriteLocalBaseNode copy) {
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
            boolean rightNodeValue;
            try {
                rightNodeValue = this.rightNode.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return SLTYPES.expectBoolean(executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof boolean"));
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return SLTYPES.expectBoolean(executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException"));
            }
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
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated) {
            boolean rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectBoolean(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            boolean rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectBoolean(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            boolean rightNodeValue;
            try {
                rightNodeValue = SLTYPES.expectBoolean(rightNodeValueEvaluated);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, ex.getResult(), "Expected rightNodeValue instanceof boolean");
            }
            try {
                return super.write(frameValue, rightNodeValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(WriteLocalObjectNode.class, frameValue, rightNodeValue, "Thrown FrameSlotTypeException");
            }
        }

    }
    @GeneratedBy(WriteLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class WriteLocalObjectNode extends WriteLocalBaseNode {

        WriteLocalObjectNode(WriteLocalBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            Object rightNodeValue = this.rightNode.executeGeneric(frameValue);
            return super.writeGeneric(frameValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated) {
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.writeGeneric(frameValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated) {
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.writeGeneric(frameValue, rightNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object rightNodeValueEvaluated, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            Object rightNodeValue = rightNodeValueEvaluated;
            return super.writeGeneric(frameValue, rightNodeValue);
        }

    }
}
