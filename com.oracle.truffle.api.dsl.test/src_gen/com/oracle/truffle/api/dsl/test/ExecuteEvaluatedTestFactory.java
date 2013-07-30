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
import com.oracle.truffle.api.dsl.test.ExecuteEvaluatedTest.DoubleEvaluatedNode;
import com.oracle.truffle.api.dsl.test.ExecuteEvaluatedTest.EvaluatedNode;
import com.oracle.truffle.api.dsl.test.ExecuteEvaluatedTest.UseDoubleEvaluatedNode;
import com.oracle.truffle.api.dsl.test.ExecuteEvaluatedTest.UseEvaluatedNode;
import com.oracle.truffle.api.dsl.test.TypeSystemTest.ValueNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(ExecuteEvaluatedTest.class)
public final class ExecuteEvaluatedTestFactory {

    private ExecuteEvaluatedTestFactory() {
    }

    public static List<NodeFactory<? extends ValueNode>> getFactories() {
        return asList(EvaluatedNodeFactory.getInstance(), UseEvaluatedNodeFactory.getInstance(), DoubleEvaluatedNodeFactory.getInstance(), UseDoubleEvaluatedNodeFactory.getInstance());
    }

    @GeneratedBy(EvaluatedNode.class)
    static final class EvaluatedNodeFactory implements NodeFactory<EvaluatedNode> {

        private static EvaluatedNodeFactory evaluatedNodeFactoryInstance;

        private EvaluatedNodeFactory() {
        }

        @Override
        public EvaluatedNode createNode(Object... arguments) {
            if (arguments.length == 1 && (arguments[0] == null || arguments[0] instanceof ValueNode)) {
                return create((ValueNode) arguments[0]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public EvaluatedNode createNodeGeneric(EvaluatedNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<EvaluatedNode> getNodeClass() {
            return EvaluatedNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class);
        }

        static EvaluatedNode createGeneric(EvaluatedNode thisNode) {
            return new EvaluatedGenericNode((EvaluatedBaseNode) thisNode);
        }

        static EvaluatedNode create(ValueNode exp) {
            return new EvaluatedUninitializedNode(exp);
        }

        static NodeFactory<EvaluatedNode> getInstance() {
            if (evaluatedNodeFactoryInstance == null) {
                evaluatedNodeFactoryInstance = new EvaluatedNodeFactory();
            }
            return evaluatedNodeFactoryInstance;
        }

        @GeneratedBy(EvaluatedNode.class)
        private abstract static class EvaluatedBaseNode extends EvaluatedNode {

            @Child protected ValueNode exp;

            EvaluatedBaseNode(ValueNode exp) {
                super();
                this.exp = adoptChild(exp);
            }

            EvaluatedBaseNode(EvaluatedBaseNode copy) {
                this.exp = adoptChild(copy.exp);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object expValue, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == EvaluatedUninitializedNode.class);
                String message = createInfo0(reason, expValue);
                allowed = allowed || (minimumState == EvaluatedIntNode.class);
                if (SIMPLETYPES.isInteger(expValue)) {
                    int expValueCast = SIMPLETYPES.asInteger(expValue);
                    if (resultClass == null) {
                        result = super.doExecuteWith(expValueCast);
                        resultClass = EvaluatedIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new EvaluatedIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", expValue));
                }
                super.replace(new EvaluatedGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object expValue) {
                if (SIMPLETYPES.isInteger(expValue)) {
                    int expValueCast = SIMPLETYPES.asInteger(expValue);
                    return super.doExecuteWith(expValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", expValue));
            }

            protected static String createInfo0(String message, Object expValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("expValue").append(" = ").append(expValue);
                    if (expValue != null) {
                        builder.append(" (").append(expValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(EvaluatedNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class EvaluatedUninitializedNode extends EvaluatedBaseNode {

            EvaluatedUninitializedNode(ValueNode exp) {
                super(exp);
            }

            EvaluatedUninitializedNode(EvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object expValue = this.exp.execute(frameValue);
                return super.executeAndSpecialize0(EvaluatedUninitializedNode.class, frameValue, expValue, "Uninitialized monomorphic");
            }

            @Override
            public int executeIntEvaluated(VirtualFrame frameValue, Object expValueEvaluated) throws UnexpectedResultException {
                Object expValue = expValueEvaluated;
                return SIMPLETYPES.expectInteger(this.executeEvaluated(frameValue, expValue));
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object expValueEvaluated) {
                transferToInterpreter();
                Object expValue = expValueEvaluated;
                return super.executeAndSpecialize0(EvaluatedUninitializedNode.class, frameValue, expValue, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(EvaluatedNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class EvaluatedIntNode extends EvaluatedBaseNode {

            EvaluatedIntNode(ValueNode exp) {
                super(exp);
            }

            EvaluatedIntNode(EvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int expValue;
                try {
                    expValue = this.exp.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(EvaluatedGenericNode.class, frameValue, ex.getResult(), "Expected expValue instanceof int"));
                }
                return super.doExecuteWith(expValue);
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
            public int executeIntEvaluated(VirtualFrame frameValue, Object expValueEvaluated) throws UnexpectedResultException {
                int expValue;
                try {
                    expValue = SIMPLETYPES.expectInteger(expValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(EvaluatedGenericNode.class, frameValue, ex.getResult(), "Expected expValue instanceof int"));
                }
                return super.doExecuteWith(expValue);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object expValueEvaluated) {
                Object expValue = expValueEvaluated;
                int value;
                try {
                    value = this.executeIntEvaluated(frameValue, expValue);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

        }
        @GeneratedBy(EvaluatedNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class EvaluatedGenericNode extends EvaluatedBaseNode {

            EvaluatedGenericNode(ValueNode exp) {
                super(exp);
            }

            EvaluatedGenericNode(EvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object expValue = this.exp.execute(frameValue);
                return super.executeGeneric0(expValue);
            }

            @Override
            public int executeIntEvaluated(VirtualFrame frameValue, Object expValueEvaluated) throws UnexpectedResultException {
                Object expValue = expValueEvaluated;
                return SIMPLETYPES.expectInteger(this.executeEvaluated(frameValue, expValue));
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object expValueEvaluated) {
                Object expValue = expValueEvaluated;
                return super.executeGeneric0(expValue);
            }

        }
    }
    @GeneratedBy(UseEvaluatedNode.class)
    static final class UseEvaluatedNodeFactory implements NodeFactory<UseEvaluatedNode> {

        private static UseEvaluatedNodeFactory useEvaluatedNodeFactoryInstance;

        private UseEvaluatedNodeFactory() {
        }

        @Override
        public UseEvaluatedNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof EvaluatedNode)) {
                return create((ValueNode) arguments[0], (EvaluatedNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public UseEvaluatedNode createNodeGeneric(UseEvaluatedNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<UseEvaluatedNode> getNodeClass() {
            return UseEvaluatedNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class, EvaluatedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, EvaluatedNode.class);
        }

        static UseEvaluatedNode createGeneric(UseEvaluatedNode thisNode) {
            return new UseEvaluatedGenericNode((UseEvaluatedBaseNode) thisNode);
        }

        static UseEvaluatedNode create(ValueNode exp0, EvaluatedNode exp1) {
            return new UseEvaluatedUninitializedNode(exp0, exp1);
        }

        static NodeFactory<UseEvaluatedNode> getInstance() {
            if (useEvaluatedNodeFactoryInstance == null) {
                useEvaluatedNodeFactoryInstance = new UseEvaluatedNodeFactory();
            }
            return useEvaluatedNodeFactoryInstance;
        }

        @GeneratedBy(UseEvaluatedNode.class)
        private abstract static class UseEvaluatedBaseNode extends UseEvaluatedNode {

            @Child protected ValueNode exp0;
            @Child protected EvaluatedNode exp1;

            UseEvaluatedBaseNode(ValueNode exp0, EvaluatedNode exp1) {
                super();
                this.exp0 = adoptChild(exp0);
                this.exp1 = adoptChild(exp1);
            }

            UseEvaluatedBaseNode(UseEvaluatedBaseNode copy) {
                this.exp0 = adoptChild(copy.exp0);
                this.exp1 = adoptChild(copy.exp1);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object exp0Value, Object exp1Value, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == UseEvaluatedUninitializedNode.class);
                String message = createInfo0(reason, exp0Value, exp1Value);
                allowed = allowed || (minimumState == UseEvaluatedIntNode.class);
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    if (resultClass == null) {
                        result = super.call(exp0ValueCast, exp1ValueCast);
                        resultClass = UseEvaluatedIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new UseEvaluatedIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value));
                }
                super.replace(new UseEvaluatedGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object exp0Value, Object exp1Value) {
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    return super.call(exp0ValueCast, exp1ValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value));
            }

            protected static String createInfo0(String message, Object exp0Value, Object exp1Value) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("exp0Value").append(" = ").append(exp0Value);
                    if (exp0Value != null) {
                        builder.append(" (").append(exp0Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("exp1Value").append(" = ").append(exp1Value);
                    if (exp1Value != null) {
                        builder.append(" (").append(exp1Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(UseEvaluatedNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class UseEvaluatedUninitializedNode extends UseEvaluatedBaseNode {

            UseEvaluatedUninitializedNode(ValueNode exp0, EvaluatedNode exp1) {
                super(exp0, exp1);
            }

            UseEvaluatedUninitializedNode(UseEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.executeEvaluated(frameValue, exp0Value);
                return super.executeAndSpecialize0(UseEvaluatedUninitializedNode.class, frameValue, exp0Value, exp1Value, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(UseEvaluatedNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class UseEvaluatedIntNode extends UseEvaluatedBaseNode {

            UseEvaluatedIntNode(UseEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int exp0Value;
                try {
                    exp0Value = this.exp0.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object exp1Value = this.exp1.executeEvaluated(frameValue, ex.getResult());
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(UseEvaluatedGenericNode.class, frameValue, ex.getResult(), exp1Value, "Expected exp0Value instanceof int"));
                }
                int exp1Value;
                try {
                    exp1Value = this.exp1.executeIntEvaluated(frameValue, exp0Value);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(UseEvaluatedGenericNode.class, frameValue, exp0Value, ex.getResult(), "Expected exp1Value instanceof int"));
                }
                return super.call(exp0Value, exp1Value);
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
        @GeneratedBy(UseEvaluatedNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class UseEvaluatedGenericNode extends UseEvaluatedBaseNode {

            UseEvaluatedGenericNode(UseEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.executeEvaluated(frameValue, exp0Value);
                return super.executeGeneric0(exp0Value, exp1Value);
            }

        }
    }
    @GeneratedBy(DoubleEvaluatedNode.class)
    static final class DoubleEvaluatedNodeFactory implements NodeFactory<DoubleEvaluatedNode> {

        private static DoubleEvaluatedNodeFactory doubleEvaluatedNodeFactoryInstance;

        private DoubleEvaluatedNodeFactory() {
        }

        @Override
        public DoubleEvaluatedNode createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof ValueNode)) {
                return create((ValueNode) arguments[0], (ValueNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public DoubleEvaluatedNode createNodeGeneric(DoubleEvaluatedNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<DoubleEvaluatedNode> getNodeClass() {
            return DoubleEvaluatedNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class, ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class);
        }

        static DoubleEvaluatedNode createGeneric(DoubleEvaluatedNode thisNode) {
            return new DoubleEvaluatedGenericNode((DoubleEvaluatedBaseNode) thisNode);
        }

        static DoubleEvaluatedNode create(ValueNode exp0, ValueNode exp1) {
            return new DoubleEvaluatedUninitializedNode(exp0, exp1);
        }

        static NodeFactory<DoubleEvaluatedNode> getInstance() {
            if (doubleEvaluatedNodeFactoryInstance == null) {
                doubleEvaluatedNodeFactoryInstance = new DoubleEvaluatedNodeFactory();
            }
            return doubleEvaluatedNodeFactoryInstance;
        }

        @GeneratedBy(DoubleEvaluatedNode.class)
        private abstract static class DoubleEvaluatedBaseNode extends DoubleEvaluatedNode {

            @Child protected ValueNode exp0;
            @Child protected ValueNode exp1;

            DoubleEvaluatedBaseNode(ValueNode exp0, ValueNode exp1) {
                super();
                this.exp0 = adoptChild(exp0);
                this.exp1 = adoptChild(exp1);
            }

            DoubleEvaluatedBaseNode(DoubleEvaluatedBaseNode copy) {
                this.exp0 = adoptChild(copy.exp0);
                this.exp1 = adoptChild(copy.exp1);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object exp0Value, Object exp1Value, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == DoubleEvaluatedUninitializedNode.class);
                String message = createInfo0(reason, exp0Value, exp1Value);
                allowed = allowed || (minimumState == DoubleEvaluatedIntNode.class);
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    if (resultClass == null) {
                        result = super.doExecuteWith(exp0ValueCast, exp1ValueCast);
                        resultClass = DoubleEvaluatedIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new DoubleEvaluatedIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value));
                }
                super.replace(new DoubleEvaluatedGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object exp0Value, Object exp1Value) {
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    return super.doExecuteWith(exp0ValueCast, exp1ValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value));
            }

            protected static String createInfo0(String message, Object exp0Value, Object exp1Value) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("exp0Value").append(" = ").append(exp0Value);
                    if (exp0Value != null) {
                        builder.append(" (").append(exp0Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("exp1Value").append(" = ").append(exp1Value);
                    if (exp1Value != null) {
                        builder.append(" (").append(exp1Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(DoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class DoubleEvaluatedUninitializedNode extends DoubleEvaluatedBaseNode {

            DoubleEvaluatedUninitializedNode(ValueNode exp0, ValueNode exp1) {
                super(exp0, exp1);
            }

            DoubleEvaluatedUninitializedNode(DoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.execute(frameValue);
                return super.executeAndSpecialize0(DoubleEvaluatedUninitializedNode.class, frameValue, exp0Value, exp1Value, "Uninitialized monomorphic");
            }

            @Override
            public int executeIntEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) throws UnexpectedResultException {
                Object exp0Value = exp0ValueEvaluated;
                Object exp1Value = exp1ValueEvaluated;
                return SIMPLETYPES.expectInteger(this.executeEvaluated(frameValue, exp0Value, exp1Value));
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) {
                transferToInterpreter();
                Object exp0Value = exp0ValueEvaluated;
                Object exp1Value = exp1ValueEvaluated;
                return super.executeAndSpecialize0(DoubleEvaluatedUninitializedNode.class, frameValue, exp0Value, exp1Value, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(DoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class DoubleEvaluatedIntNode extends DoubleEvaluatedBaseNode {

            DoubleEvaluatedIntNode(DoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int exp0Value;
                try {
                    exp0Value = this.exp0.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object exp1Value = this.exp1.execute(frameValue);
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(DoubleEvaluatedGenericNode.class, frameValue, ex.getResult(), exp1Value, "Expected exp0Value instanceof int"));
                }
                int exp1Value;
                try {
                    exp1Value = this.exp1.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(DoubleEvaluatedGenericNode.class, frameValue, exp0Value, ex.getResult(), "Expected exp1Value instanceof int"));
                }
                return super.doExecuteWith(exp0Value, exp1Value);
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
            public int executeIntEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) throws UnexpectedResultException {
                int exp0Value;
                try {
                    exp0Value = SIMPLETYPES.expectInteger(exp0ValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object exp1Value = exp1ValueEvaluated;
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(DoubleEvaluatedGenericNode.class, frameValue, ex.getResult(), exp1Value, "Expected exp0Value instanceof int"));
                }
                int exp1Value;
                try {
                    exp1Value = SIMPLETYPES.expectInteger(exp1ValueEvaluated);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(DoubleEvaluatedGenericNode.class, frameValue, exp0Value, ex.getResult(), "Expected exp1Value instanceof int"));
                }
                return super.doExecuteWith(exp0Value, exp1Value);
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) {
                Object exp0Value = exp0ValueEvaluated;
                Object exp1Value = exp1ValueEvaluated;
                int value;
                try {
                    value = this.executeIntEvaluated(frameValue, exp0Value, exp1Value);
                } catch (UnexpectedResultException ex) {
                    return ex.getResult();
                }
                return value;
            }

        }
        @GeneratedBy(DoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class DoubleEvaluatedGenericNode extends DoubleEvaluatedBaseNode {

            DoubleEvaluatedGenericNode(DoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.execute(frameValue);
                return super.executeGeneric0(exp0Value, exp1Value);
            }

            @Override
            public int executeIntEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) throws UnexpectedResultException {
                Object exp0Value = exp0ValueEvaluated;
                Object exp1Value = exp1ValueEvaluated;
                return SIMPLETYPES.expectInteger(this.executeEvaluated(frameValue, exp0Value, exp1Value));
            }

            @Override
            public Object executeEvaluated(VirtualFrame frameValue, Object exp0ValueEvaluated, Object exp1ValueEvaluated) {
                Object exp0Value = exp0ValueEvaluated;
                Object exp1Value = exp1ValueEvaluated;
                return super.executeGeneric0(exp0Value, exp1Value);
            }

        }
    }
    @GeneratedBy(UseDoubleEvaluatedNode.class)
    static final class UseDoubleEvaluatedNodeFactory implements NodeFactory<UseDoubleEvaluatedNode> {

        private static UseDoubleEvaluatedNodeFactory useDoubleEvaluatedNodeFactoryInstance;

        private UseDoubleEvaluatedNodeFactory() {
        }

        @Override
        public UseDoubleEvaluatedNode createNode(Object... arguments) {
            if (arguments.length == 3 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof ValueNode) && (arguments[2] == null ||
                        arguments[2] instanceof DoubleEvaluatedNode)) {
                return create((ValueNode) arguments[0], (ValueNode) arguments[1], (DoubleEvaluatedNode) arguments[2]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public UseDoubleEvaluatedNode createNodeGeneric(UseDoubleEvaluatedNode thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<UseDoubleEvaluatedNode> getNodeClass() {
            return UseDoubleEvaluatedNode.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class, ValueNode.class, DoubleEvaluatedNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class, DoubleEvaluatedNode.class);
        }

        static UseDoubleEvaluatedNode createGeneric(UseDoubleEvaluatedNode thisNode) {
            return new UseDoubleEvaluatedGenericNode((UseDoubleEvaluatedBaseNode) thisNode);
        }

        static UseDoubleEvaluatedNode create(ValueNode exp0, ValueNode exp1, DoubleEvaluatedNode exp2) {
            return new UseDoubleEvaluatedUninitializedNode(exp0, exp1, exp2);
        }

        static NodeFactory<UseDoubleEvaluatedNode> getInstance() {
            if (useDoubleEvaluatedNodeFactoryInstance == null) {
                useDoubleEvaluatedNodeFactoryInstance = new UseDoubleEvaluatedNodeFactory();
            }
            return useDoubleEvaluatedNodeFactoryInstance;
        }

        @GeneratedBy(UseDoubleEvaluatedNode.class)
        private abstract static class UseDoubleEvaluatedBaseNode extends UseDoubleEvaluatedNode {

            @Child protected ValueNode exp0;
            @Child protected ValueNode exp1;
            @Child protected DoubleEvaluatedNode exp2;

            UseDoubleEvaluatedBaseNode(ValueNode exp0, ValueNode exp1, DoubleEvaluatedNode exp2) {
                super();
                this.exp0 = adoptChild(exp0);
                this.exp1 = adoptChild(exp1);
                this.exp2 = adoptChild(exp2);
            }

            UseDoubleEvaluatedBaseNode(UseDoubleEvaluatedBaseNode copy) {
                this.exp0 = adoptChild(copy.exp0);
                this.exp1 = adoptChild(copy.exp1);
                this.exp2 = adoptChild(copy.exp2);
            }

            @SuppressWarnings("unused")
            protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object exp0Value, Object exp1Value, Object exp2Value, String reason) {
                neverPartOfCompilation();
                Object result = null;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == UseDoubleEvaluatedUninitializedNode.class);
                String message = createInfo0(reason, exp0Value, exp1Value, exp2Value);
                allowed = allowed || (minimumState == UseDoubleEvaluatedIntNode.class);
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value) && SIMPLETYPES.isInteger(exp2Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    int exp2ValueCast = SIMPLETYPES.asInteger(exp2Value);
                    if (resultClass == null) {
                        result = super.call(exp0ValueCast, exp1ValueCast, exp2ValueCast);
                        resultClass = UseDoubleEvaluatedIntNode.class;
                    }
                    if (allowed) {
                        super.replace(new UseDoubleEvaluatedIntNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value, exp2Value));
                }
                super.replace(new UseDoubleEvaluatedGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected Object executeGeneric0(Object exp0Value, Object exp1Value, Object exp2Value) {
                if (SIMPLETYPES.isInteger(exp0Value) && SIMPLETYPES.isInteger(exp1Value) && SIMPLETYPES.isInteger(exp2Value)) {
                    int exp0ValueCast = SIMPLETYPES.asInteger(exp0Value);
                    int exp1ValueCast = SIMPLETYPES.asInteger(exp1Value);
                    int exp2ValueCast = SIMPLETYPES.asInteger(exp2Value);
                    return super.call(exp0ValueCast, exp1ValueCast, exp2ValueCast);
                }
                throw new UnsupportedOperationException(createInfo0("Unsupported values", exp0Value, exp1Value, exp2Value));
            }

            protected static String createInfo0(String message, Object exp0Value, Object exp1Value, Object exp2Value) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("exp0Value").append(" = ").append(exp0Value);
                    if (exp0Value != null) {
                        builder.append(" (").append(exp0Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("exp1Value").append(" = ").append(exp1Value);
                    if (exp1Value != null) {
                        builder.append(" (").append(exp1Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("exp2Value").append(" = ").append(exp2Value);
                    if (exp2Value != null) {
                        builder.append(" (").append(exp2Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(UseDoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class UseDoubleEvaluatedUninitializedNode extends UseDoubleEvaluatedBaseNode {

            UseDoubleEvaluatedUninitializedNode(ValueNode exp0, ValueNode exp1, DoubleEvaluatedNode exp2) {
                super(exp0, exp1, exp2);
            }

            UseDoubleEvaluatedUninitializedNode(UseDoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                transferToInterpreter();
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.execute(frameValue);
                Object exp2Value = this.exp2.executeEvaluated(frameValue, exp0Value, exp1Value);
                return super.executeAndSpecialize0(UseDoubleEvaluatedUninitializedNode.class, frameValue, exp0Value, exp1Value, exp2Value, "Uninitialized monomorphic");
            }

        }
        @GeneratedBy(UseDoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class UseDoubleEvaluatedIntNode extends UseDoubleEvaluatedBaseNode {

            UseDoubleEvaluatedIntNode(UseDoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int exp0Value;
                try {
                    exp0Value = this.exp0.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object exp1Value = this.exp1.execute(frameValue);
                    Object exp2Value = this.exp2.executeEvaluated(frameValue, ex.getResult(), exp1Value);
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(UseDoubleEvaluatedGenericNode.class, frameValue, ex.getResult(), exp1Value, exp2Value, "Expected exp0Value instanceof int"));
                }
                int exp1Value;
                try {
                    exp1Value = this.exp1.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object exp2Value = this.exp2.executeEvaluated(frameValue, exp0Value, ex.getResult());
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(UseDoubleEvaluatedGenericNode.class, frameValue, exp0Value, ex.getResult(), exp2Value, "Expected exp1Value instanceof int"));
                }
                int exp2Value;
                try {
                    exp2Value = this.exp2.executeIntEvaluated(frameValue, exp0Value, exp1Value);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return SIMPLETYPES.expectInteger(executeAndSpecialize0(UseDoubleEvaluatedGenericNode.class, frameValue, exp0Value, exp1Value, ex.getResult(), "Expected exp2Value instanceof int"));
                }
                return super.call(exp0Value, exp1Value, exp2Value);
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
        @GeneratedBy(UseDoubleEvaluatedNode.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class UseDoubleEvaluatedGenericNode extends UseDoubleEvaluatedBaseNode {

            UseDoubleEvaluatedGenericNode(UseDoubleEvaluatedBaseNode copy) {
                super(copy);
            }

            @Override
            public Object execute(VirtualFrame frameValue) {
                Object exp0Value = this.exp0.execute(frameValue);
                Object exp1Value = this.exp1.execute(frameValue);
                Object exp2Value = this.exp2.executeEvaluated(frameValue, exp0Value, exp1Value);
                return super.executeGeneric0(exp0Value, exp1Value, exp2Value);
            }

        }
    }
}
