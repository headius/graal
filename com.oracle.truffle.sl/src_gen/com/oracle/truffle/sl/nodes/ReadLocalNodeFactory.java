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

@GeneratedBy(ReadLocalNode.class)
public final class ReadLocalNodeFactory implements NodeFactory<ReadLocalNode> {

    private static ReadLocalNodeFactory instance;

    private ReadLocalNodeFactory() {
    }

    @Override
    public ReadLocalNode createNode(Object... arguments) {
        if (arguments.length == 1 && (arguments[0] == null || arguments[0] instanceof FrameSlot)) {
            return create((FrameSlot) arguments[0]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public ReadLocalNode createNodeGeneric(ReadLocalNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<ReadLocalNode> getNodeClass() {
        return ReadLocalNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(FrameSlot.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList();
    }

    public static ReadLocalNode createGeneric(ReadLocalNode thisNode) {
        return new ReadLocalObjectNode((ReadLocalBaseNode) thisNode);
    }

    public static ReadLocalNode create(FrameSlot slot) {
        return new ReadLocalUninitializedNode(slot);
    }

    public static NodeFactory<ReadLocalNode> getInstance() {
        if (instance == null) {
            instance = new ReadLocalNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(ReadLocalNode.class)
    private abstract static class ReadLocalBaseNode extends ReadLocalNode {

        ReadLocalBaseNode(FrameSlot slot) {
            super(slot);
        }

        ReadLocalBaseNode(ReadLocalBaseNode copy) {
            super(copy);
        }

        protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, String reason) {
            neverPartOfCompilation();
            Object result = null;
            Class<?> resultClass = null;
            boolean allowed = (minimumState == ReadLocalUninitializedNode.class);
            String message = createInfo0(reason, frameValue);
            allowed = allowed || (minimumState == ReadLocalIntNode.class);
            try {
                if (resultClass == null) {
                    result = super.doInteger(frameValue);
                    resultClass = ReadLocalIntNode.class;
                }
                if (allowed) {
                    super.replace(new ReadLocalIntNode(this), message);
                    return result;
                }
            } catch (FrameSlotTypeException rewriteEx) {
                // fall through
            }
            allowed = allowed || (minimumState == ReadLocalBooleanNode.class);
            try {
                if (resultClass == null) {
                    result = super.doBoolean(frameValue);
                    resultClass = ReadLocalBooleanNode.class;
                }
                if (allowed) {
                    super.replace(new ReadLocalBooleanNode(this), message);
                    return result;
                }
            } catch (FrameSlotTypeException rewriteEx) {
                // fall through
            }
            allowed = allowed || (minimumState == ReadLocalObjectNode.class);
            if (resultClass == null) {
                result = super.doObject(frameValue);
                resultClass = ReadLocalObjectNode.class;
            }
            super.replace(new ReadLocalObjectNode(this), message);
            return result;
            // unreachable Generic
        }

        @SuppressWarnings("unused")
        protected static String createInfo0(String message, VirtualFrame frameValue) {
            if (DetailedRewriteReasons) {
                StringBuilder builder = new StringBuilder(message);
                builder.append(" (");
                builder.append(")");
                return builder.toString();
            } else {
                return message;
            }
        }

    }
    @GeneratedBy(ReadLocalNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class ReadLocalUninitializedNode extends ReadLocalBaseNode {

        ReadLocalUninitializedNode(FrameSlot slot) {
            super(slot);
        }

        ReadLocalUninitializedNode(ReadLocalBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            transferToInterpreter();
            return super.executeAndSpecialize0(ReadLocalUninitializedNode.class, frameValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated) {
            transferToInterpreter();
            return super.executeAndSpecialize0(ReadLocalUninitializedNode.class, frameValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            transferToInterpreter();
            return super.executeAndSpecialize0(ReadLocalUninitializedNode.class, frameValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            transferToInterpreter();
            return super.executeAndSpecialize0(ReadLocalUninitializedNode.class, frameValue, "Uninitialized monomorphic");
        }

    }
    @GeneratedBy(ReadLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class ReadLocalIntNode extends ReadLocalBaseNode {

        ReadLocalIntNode(FrameSlot slot) {
            super(slot);
        }

        ReadLocalIntNode(ReadLocalBaseNode copy) {
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
            try {
                return super.doInteger(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return SLTYPES.expectInteger(executeAndSpecialize0(ReadLocalBooleanNode.class, frameValue, "Thrown FrameSlotTypeException"));
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
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated) {
            try {
                return super.doInteger(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalBooleanNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            try {
                return super.doInteger(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalBooleanNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            try {
                return super.doInteger(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalBooleanNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

    }
    @GeneratedBy(ReadLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class ReadLocalBooleanNode extends ReadLocalBaseNode {

        ReadLocalBooleanNode(FrameSlot slot) {
            super(slot);
        }

        ReadLocalBooleanNode(ReadLocalBaseNode copy) {
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
            try {
                return super.doBoolean(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return SLTYPES.expectBoolean(executeAndSpecialize0(ReadLocalObjectNode.class, frameValue, "Thrown FrameSlotTypeException"));
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
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated) {
            try {
                return super.doBoolean(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalObjectNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            try {
                return super.doBoolean(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalObjectNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            try {
                return super.doBoolean(frameValue);
            } catch (FrameSlotTypeException ex) {
                transferToInterpreter();
                return executeAndSpecialize0(ReadLocalObjectNode.class, frameValue, "Thrown FrameSlotTypeException");
            }
        }

    }
    @GeneratedBy(ReadLocalNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class ReadLocalObjectNode extends ReadLocalBaseNode {

        ReadLocalObjectNode(FrameSlot slot) {
            super(slot);
        }

        ReadLocalObjectNode(ReadLocalBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            return super.doObject(frameValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated) {
            return super.doObject(frameValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            return super.doObject(frameValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            return super.doObject(frameValue);
        }

    }
}
