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
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(TernaryNode.class)
public final class TernaryNodeFactory implements NodeFactory<TernaryNode> {

    private static TernaryNodeFactory instance;

    private TernaryNodeFactory() {
    }

    @Override
    public TernaryNode createNode(Object... arguments) {
        if (arguments.length == 3 && (arguments[0] == null || arguments[0] instanceof ConditionNode) && (arguments[1] == null || arguments[1] instanceof TypedNode) && (arguments[2] == null ||
                    arguments[2] instanceof TypedNode)) {
            return create((ConditionNode) arguments[0], (TypedNode) arguments[1], (TypedNode) arguments[2]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public TernaryNode createNodeGeneric(TernaryNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<TernaryNode> getNodeClass() {
        return TernaryNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(ConditionNode.class, TypedNode.class, TypedNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(ConditionNode.class, TypedNode.class, TypedNode.class);
    }

    public static TernaryNode createGeneric(TernaryNode thisNode) {
        return new TernaryObjectNode((TernaryBaseNode) thisNode);
    }

    public static TernaryNode create(ConditionNode conditionNode, TypedNode ifPartNode, TypedNode elsePartNode) {
        return new TernaryUninitializedNode(conditionNode, ifPartNode, elsePartNode);
    }

    public static NodeFactory<TernaryNode> getInstance() {
        if (instance == null) {
            instance = new TernaryNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(TernaryNode.class)
    private abstract static class TernaryBaseNode extends TernaryNode {

        @Child protected ConditionNode conditionNode;
        @Child protected TypedNode ifPartNode;
        @Child protected TypedNode elsePartNode;

        TernaryBaseNode(ConditionNode conditionNode, TypedNode ifPartNode, TypedNode elsePartNode) {
            super();
            this.conditionNode = adoptChild(conditionNode);
            this.ifPartNode = adoptChild(ifPartNode);
            this.elsePartNode = adoptChild(elsePartNode);
        }

        TernaryBaseNode(TernaryBaseNode copy) {
            this.conditionNode = adoptChild(copy.conditionNode);
            this.ifPartNode = adoptChild(copy.ifPartNode);
            this.elsePartNode = adoptChild(copy.elsePartNode);
        }

        @SuppressWarnings("unused")
        protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, boolean conditionNodeValue, boolean hasIfPartNodeValue, Object ifPartNodeValue, boolean
                    hasElsePartNodeValue, Object elsePartNodeValue, String reason) {
            neverPartOfCompilation();
            Object result = null;
            Class<?> resultClass = null;
            boolean allowed = (minimumState == TernaryUninitializedNode.class);
            String message = createInfo0(reason, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
            allowed = allowed || (minimumState == TernaryIntNode.class);
            if ((!hasIfPartNodeValue || SLTYPES.isInteger(ifPartNodeValue)) && (!hasElsePartNodeValue || SLTYPES.isInteger(elsePartNodeValue))) {
                int ifPartNodeValueCast = 0;
                if (hasIfPartNodeValue) {
                    ifPartNodeValueCast = SLTYPES.asInteger(ifPartNodeValue);
                }
                int elsePartNodeValueCast = 0;
                if (hasElsePartNodeValue) {
                    elsePartNodeValueCast = SLTYPES.asInteger(elsePartNodeValue);
                }
                if (resultClass == null) {
                    result = super.doInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValueCast, hasElsePartNodeValue, elsePartNodeValueCast);
                    resultClass = TernaryIntNode.class;
                }
                if (allowed) {
                    super.replace(new TernaryIntNode(this), message);
                    return result;
                }
            }
            allowed = allowed || (minimumState == TernaryBigIntegerNode.class);
            if ((!hasIfPartNodeValue || SLTYPES.isBigInteger(ifPartNodeValue)) && (!hasElsePartNodeValue || SLTYPES.isBigInteger(elsePartNodeValue))) {
                BigInteger ifPartNodeValueCast = null;
                if (hasIfPartNodeValue) {
                    ifPartNodeValueCast = SLTYPES.asBigInteger(ifPartNodeValue);
                }
                BigInteger elsePartNodeValueCast = null;
                if (hasElsePartNodeValue) {
                    elsePartNodeValueCast = SLTYPES.asBigInteger(elsePartNodeValue);
                }
                if (resultClass == null) {
                    result = super.doBigInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValueCast, hasElsePartNodeValue, elsePartNodeValueCast);
                    resultClass = TernaryBigIntegerNode.class;
                }
                if (allowed) {
                    super.replace(new TernaryBigIntegerNode(this), message);
                    return result;
                }
            }
            allowed = allowed || (minimumState == TernaryObjectNode.class);
            if (resultClass == null) {
                result = super.doObject(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
                resultClass = TernaryObjectNode.class;
            }
            super.replace(new TernaryObjectNode(this), message);
            return result;
            // unreachable Generic
        }

        @SuppressWarnings("unused")
        protected static String createInfo0(String message, boolean conditionNodeValue, boolean hasIfPartNodeValue, Object ifPartNodeValue, boolean hasElsePartNodeValue, Object elsePartNodeValue) {
            if (DetailedRewriteReasons) {
                StringBuilder builder = new StringBuilder(message);
                builder.append(" (");
                builder.append("conditionNodeValue").append(" = ").append(conditionNodeValue);
                builder.append(" (boolean)");
                builder.append(", ").append("ifPartNodeValue").append(" = ").append(ifPartNodeValue);
                if (ifPartNodeValue != null) {
                    builder.append(" (").append(ifPartNodeValue.getClass().getSimpleName()).append(")");
                }
                builder.append(", ").append("elsePartNodeValue").append(" = ").append(elsePartNodeValue);
                if (elsePartNodeValue != null) {
                    builder.append(" (").append(elsePartNodeValue.getClass().getSimpleName()).append(")");
                }
                builder.append(")");
                return builder.toString();
            } else {
                return message;
            }
        }

    }
    @GeneratedBy(TernaryNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class TernaryUninitializedNode extends TernaryBaseNode {

        TernaryUninitializedNode(ConditionNode conditionNode, TypedNode ifPartNode, TypedNode elsePartNode) {
            super(conditionNode, ifPartNode, elsePartNode);
        }

        TernaryUninitializedNode(TernaryBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            transferToInterpreter();
            boolean conditionNodeValue = this.conditionNode.executeCondition(frameValue);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                ifPartNodeValue = this.ifPartNode.executeGeneric(frameValue);
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.executeAndSpecialize0(TernaryUninitializedNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated) {
            transferToInterpreter();
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                ifPartNodeValue = this.ifPartNode.executeGeneric(frameValue);
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.executeAndSpecialize0(TernaryUninitializedNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated) {
            transferToInterpreter();
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = ifPartNodeValueEvaluated;
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.executeAndSpecialize0(TernaryUninitializedNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue, "Uninitialized monomorphic");
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated, Object elsePartNodeValueEvaluated) {
            transferToInterpreter();
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = ifPartNodeValueEvaluated;
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = elsePartNodeValueEvaluated;
            return super.executeAndSpecialize0(TernaryUninitializedNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue, "Uninitialized monomorphic");
        }

    }
    @GeneratedBy(TernaryNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class TernaryIntNode extends TernaryBaseNode {

        TernaryIntNode(TernaryBaseNode copy) {
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
            boolean conditionNodeValue = this.conditionNode.executeCondition(frameValue);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            int ifPartNodeValue = 0;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = this.ifPartNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return SLTYPES.expectInteger(executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue,
                                elsePartNodeValue, "Expected ifPartNodeValue instanceof int"));
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            int elsePartNodeValue = 0;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectInteger(executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue,
                                ex.getResult(), "Expected elsePartNodeValue instanceof int"));
                }
            }
            return super.doInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
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
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            int ifPartNodeValue = 0;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = this.ifPartNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof int");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            int elsePartNodeValue = 0;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof int");
                }
            }
            return super.doInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            int ifPartNodeValue = 0;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = SLTYPES.expectInteger(ifPartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof int");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            int elsePartNodeValue = 0;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof int");
                }
            }
            return super.doInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated, Object elsePartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            int ifPartNodeValue = 0;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = SLTYPES.expectInteger(ifPartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = elsePartNodeValueEvaluated;
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof int");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            int elsePartNodeValue = 0;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = SLTYPES.expectInteger(elsePartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryBigIntegerNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof int");
                }
            }
            return super.doInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

    }
    @GeneratedBy(TernaryNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class TernaryBigIntegerNode extends TernaryBaseNode {

        TernaryBigIntegerNode(TernaryBaseNode copy) {
            super(copy);
        }

        @Override
        public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            boolean conditionNodeValue = this.conditionNode.executeCondition(frameValue);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            BigInteger ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = this.ifPartNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue,
                                elsePartNodeValue, "Expected ifPartNodeValue instanceof BigInteger"));
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            BigInteger elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SLTYPES.expectBigInteger(executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue,
                                ex.getResult(), "Expected elsePartNodeValue instanceof BigInteger"));
                }
            }
            return super.doBigInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
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
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            BigInteger ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = this.ifPartNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof BigInteger");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            BigInteger elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof BigInteger");
                }
            }
            return super.doBigInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            BigInteger ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = SLTYPES.expectBigInteger(ifPartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = null;
                    if (hasElsePartNodeValue) {
                        elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
                    }
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof BigInteger");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            BigInteger elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = this.elsePartNode.executeBigInteger(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof BigInteger");
                }
            }
            return super.doBigInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated, Object elsePartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            BigInteger ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                try {
                    ifPartNodeValue = SLTYPES.expectBigInteger(ifPartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ex.getResult());
                    Object elsePartNodeValue = elsePartNodeValueEvaluated;
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ex.getResult(), hasElsePartNodeValue, elsePartNodeValue, "Expected ifPartNodeValue instanceof BigInteger");
                }
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            BigInteger elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                try {
                    elsePartNodeValue = SLTYPES.expectBigInteger(elsePartNodeValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(TernaryObjectNode.class, frameValue, conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, ex.getResult(), "Expected elsePartNodeValue instanceof BigInteger");
                }
            }
            return super.doBigInteger(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

    }
    @GeneratedBy(TernaryNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class TernaryObjectNode extends TernaryBaseNode {

        TernaryObjectNode(TernaryBaseNode copy) {
            super(copy);
        }

        @Override
        public Object executeGeneric(VirtualFrame frameValue) {
            boolean conditionNodeValue = this.conditionNode.executeCondition(frameValue);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                ifPartNodeValue = this.ifPartNode.executeGeneric(frameValue);
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.doObject(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = null;
            if (hasIfPartNodeValue) {
                ifPartNodeValue = this.ifPartNode.executeGeneric(frameValue);
            }
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.doObject(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = ifPartNodeValueEvaluated;
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = null;
            if (hasElsePartNodeValue) {
                elsePartNodeValue = this.elsePartNode.executeGeneric(frameValue);
            }
            return super.doObject(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object conditionNodeValueEvaluated, Object ifPartNodeValueEvaluated, Object elsePartNodeValueEvaluated) {
            boolean conditionNodeValue = SLTYPES.asBoolean(conditionNodeValueEvaluated);
            boolean hasIfPartNodeValue = super.needsIfPart(conditionNodeValue);
            Object ifPartNodeValue = ifPartNodeValueEvaluated;
            boolean hasElsePartNodeValue = super.needsElsePart(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue);
            Object elsePartNodeValue = elsePartNodeValueEvaluated;
            return super.doObject(conditionNodeValue, hasIfPartNodeValue, ifPartNodeValue, hasElsePartNodeValue, elsePartNodeValue);
        }

    }
}
