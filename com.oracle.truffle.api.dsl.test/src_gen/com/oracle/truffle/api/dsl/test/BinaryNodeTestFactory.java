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
import com.oracle.truffle.api.dsl.test.BinaryNodeTest.AddNode;
import com.oracle.truffle.api.dsl.test.TypeSystemTest.ValueNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(BinaryNodeTest.class)
public final class BinaryNodeTestFactory {

    private BinaryNodeTestFactory() {
    }

    public static List<NodeFactory<AddNode>> getFactories() {
        return asList(AddNodeFactory.getInstance());
    }

    @GeneratedBy(AddNode.class)
    static final class AddNodeFactory implements NodeFactory<AddNode> {

        private static AddNodeFactory addNodeFactoryInstance;

        private AddNodeFactory() {
        }

        @Override
        public AddNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof ValueNode)) {
                return create((ValueNode) arguments[0], (ValueNode) arguments[1]);
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
            return asList(Arrays.<Class<?>>asList(ValueNode.class, ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class);
        }

        static AddNode createGeneric(AddNode thisNode) {
            return new AddGenericNode((AddBaseNode) thisNode);
        }

        static AddNode create(ValueNode left, ValueNode right) {
            return new AddUninitializedNode(left, right);
        }

        static NodeFactory<AddNode> getInstance() {
            if (addNodeFactoryInstance == null) {
                addNodeFactoryInstance = new AddNodeFactory();
            }
            return addNodeFactoryInstance;
        }

        @GeneratedBy(AddNode.class)
        private abstract static class AddBaseNode extends AddNode {

            @Child protected ValueNode left;
            @Child protected ValueNode right;

            AddBaseNode(ValueNode left, ValueNode right) {
                super();
                this.left = adoptChild(left);
                this.right = adoptChild(right);
            }

            AddBaseNode(AddBaseNode copy) {
                this.left = adoptChild(copy.left);
                this.right = adoptChild(copy.right);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object leftValue, Object rightValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == AddUninitializedNode.class);
                String message = createInfo0(reason, leftValue, rightValue);
                allowed = allowed || (minimumState == AddIntNode.class);
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    if (resultClass == null) {
                        result = super.add(leftValueCast, rightValueCast);
                        resultClass = AddIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new AddIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    result = super.add(leftValue, rightValue);
                    resultClass = AddGenericNode.class;
                }
                super.replace(new AddGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object leftValue, Object rightValue) {
                if (SIMPLETYPES.isInteger(leftValue) && SIMPLETYPES.isInteger(rightValue)) {
                    int leftValueCast = SIMPLETYPES.asInteger(leftValue);
                    int rightValueCast = SIMPLETYPES.asInteger(rightValue);
                    return super.add(leftValueCast, rightValueCast);
                }
                return super.add(leftValue, rightValue);
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
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class AddUninitializedNode extends AddBaseNode {

            AddUninitializedNode(ValueNode left, ValueNode right) {
                super(left, right);
            }

            AddUninitializedNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return super.executeAndSpecialize0(AddUninitializedNode.class, frameValue, leftValue, rightValue, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class AddIntNode extends AddBaseNode {

            AddIntNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int leftValue;
                try {
                    leftValue = this.left.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object rightValue = this.right.execute(frameValue);
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(AddGenericNode.class, frameValue, ex.getResult(), rightValue, "Expected leftValue instanceof int"));
                }
                int rightValue;
                try {
                    rightValue = this.right.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(AddGenericNode.class, frameValue, leftValue, ex.getResult(), "Expected rightValue instanceof int"));
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

        }
        @GeneratedBy(AddNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class AddGenericNode extends AddBaseNode {

            AddGenericNode(AddBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object leftValue = this.left.execute(frameValue);
                Object rightValue = this.right.execute(frameValue);
                return super.executeGeneric0(leftValue, rightValue);
            }

        }
    }
}
