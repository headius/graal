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
import com.oracle.truffle.api.dsl.test.GuardsTest.GlobalFlagGuard;
import com.oracle.truffle.api.dsl.test.GuardsTest.InvocationGuard;
import com.oracle.truffle.api.dsl.test.TypeSystemTest.ValueNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(GuardsTest.class)
public final class GuardsTestFactory {

    private GuardsTestFactory() {
    }

    public static List<NodeFactory<? extends ValueNode>> getFactories() {
        return asList(InvocationGuardFactory.getInstance(), GlobalFlagGuardFactory.getInstance());
    }

    @GeneratedBy(InvocationGuard.class)
    public static final class InvocationGuardFactory implements NodeFactory<InvocationGuard> {

        private static InvocationGuardFactory invocationGuardFactoryInstance;

        private InvocationGuardFactory() {
        }

        @Override
        public InvocationGuard createNode(Object... arguments) {
            if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof ValueNode) && (arguments[1] == null || arguments[1] instanceof ValueNode)) {
                return create((ValueNode) arguments[0], (ValueNode) arguments[1]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public InvocationGuard createNodeGeneric(InvocationGuard thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<InvocationGuard> getNodeClass() {
            return InvocationGuard.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class, ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class);
        }

        public static InvocationGuard createGeneric(InvocationGuard thisNode) {
            return new InvocationGuardGenericNode((InvocationGuardBaseNode) thisNode);
        }

        public static InvocationGuard create(ValueNode value0, ValueNode value1) {
            return new InvocationGuardUninitializedNode(value0, value1);
        }

        public static NodeFactory<InvocationGuard> getInstance() {
            if (invocationGuardFactoryInstance == null) {
                invocationGuardFactoryInstance = new InvocationGuardFactory();
            }
            return invocationGuardFactoryInstance;
        }

        @GeneratedBy(InvocationGuard.class)
        private abstract static class InvocationGuardBaseNode extends InvocationGuard {

            @Child protected ValueNode value0;
            @Child protected ValueNode value1;

            InvocationGuardBaseNode(ValueNode value0, ValueNode value1) {
                super();
                this.value0 = adoptChild(value0);
                this.value1 = adoptChild(value1);
            }

            InvocationGuardBaseNode(InvocationGuardBaseNode copy) {
                this.value0 = adoptChild(copy.value0);
                this.value1 = adoptChild(copy.value1);
            }

            @SuppressWarnings("unused")
            protected int executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object value0Value, Object value1Value, String reason) {
                neverPartOfCompilation();
                int result = 0;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == InvocationGuardUninitializedNode.class);
                String message = createInfo0(reason, value0Value, value1Value);
                allowed = allowed || (minimumState == InvocationGuardIntNode.class);
                if (SIMPLETYPES.isInteger(value0Value) && SIMPLETYPES.isInteger(value1Value)) {
                    int value0ValueCast = SIMPLETYPES.asInteger(value0Value);
                    int value1ValueCast = SIMPLETYPES.asInteger(value1Value);
                    if (super.guard(value0ValueCast, value1ValueCast)) {
                        if (resultClass == null) {
                            result = super.doSpecialized(value0ValueCast, value1ValueCast);
                            resultClass = InvocationGuardIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new InvocationGuardIntNode(this), message);
                            return result;
                        }
                    }
                }
                if (resultClass == null) {
                    result = super.doGeneric(value0Value, value1Value);
                    resultClass = InvocationGuardGenericNode.class;
                }
                super.replace(new InvocationGuardGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected int executeGeneric0(Object value0Value, Object value1Value) {
                if (SIMPLETYPES.isInteger(value0Value) && SIMPLETYPES.isInteger(value1Value)) {
                    int value0ValueCast = SIMPLETYPES.asInteger(value0Value);
                    int value1ValueCast = SIMPLETYPES.asInteger(value1Value);
                    if (super.guard(value0ValueCast, value1ValueCast)) {
                        return super.doSpecialized(value0ValueCast, value1ValueCast);
                    }
                }
                return super.doGeneric(value0Value, value1Value);
            }

            protected static String createInfo0(String message, Object value0Value, Object value1Value) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("value0Value").append(" = ").append(value0Value);
                    if (value0Value != null) {
                        builder.append(" (").append(value0Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(", ").append("value1Value").append(" = ").append(value1Value);
                    if (value1Value != null) {
                        builder.append(" (").append(value1Value.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(InvocationGuard.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class InvocationGuardUninitializedNode extends InvocationGuardBaseNode {

            InvocationGuardUninitializedNode(ValueNode value0, ValueNode value1) {
                super(value0, value1);
            }

            InvocationGuardUninitializedNode(InvocationGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                transferToInterpreter();
                Object value0Value = this.value0.execute(frameValue);
                Object value1Value = this.value1.execute(frameValue);
                return super.executeAndSpecialize0(InvocationGuardUninitializedNode.class, frameValue, value0Value, value1Value, "Uninitialized monomorphic");
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
        @GeneratedBy(InvocationGuard.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class InvocationGuardIntNode extends InvocationGuardBaseNode {

            InvocationGuardIntNode(InvocationGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                int value0Value;
                try {
                    value0Value = this.value0.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    Object value1Value = this.value1.execute(frameValue);
                    return executeAndSpecialize0(InvocationGuardGenericNode.class, frameValue, ex.getResult(), value1Value, "Expected value0Value instanceof int");
                }
                int value1Value;
                try {
                    value1Value = this.value1.executeInt(frameValue);
                } catch (UnexpectedResultException ex) {
                    transferToInterpreter();
                    return executeAndSpecialize0(InvocationGuardGenericNode.class, frameValue, value0Value, ex.getResult(), "Expected value1Value instanceof int");
                }
                if (super.guard(value0Value, value1Value)) {
                    return super.doSpecialized(value0Value, value1Value);
                }
                transferToInterpreter();
                return executeAndSpecialize0(InvocationGuardGenericNode.class, frameValue, value0Value, value1Value, "One of guards [guard] failed");
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
        @GeneratedBy(InvocationGuard.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class InvocationGuardGenericNode extends InvocationGuardBaseNode {

            InvocationGuardGenericNode(InvocationGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                Object value0Value = this.value0.execute(frameValue);
                Object value1Value = this.value1.execute(frameValue);
                return super.executeGeneric0(value0Value, value1Value);
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
    }
    @GeneratedBy(GlobalFlagGuard.class)
    public static final class GlobalFlagGuardFactory implements NodeFactory<GlobalFlagGuard> {

        private static GlobalFlagGuardFactory globalFlagGuardFactoryInstance;

        private GlobalFlagGuardFactory() {
        }

        @Override
        public GlobalFlagGuard createNode(Object... arguments) {
            if (arguments.length == 1 && (arguments[0] == null || arguments[0] instanceof ValueNode)) {
                return create((ValueNode) arguments[0]);
            } else {
                throw new IllegalArgumentException("Invalid create signature.");
            }
        }

        @Override
        public GlobalFlagGuard createNodeGeneric(GlobalFlagGuard thisNode) {
            return createGeneric(thisNode);
        }

        @Override
        public Class<GlobalFlagGuard> getNodeClass() {
            return GlobalFlagGuard.class;
        }

        @Override
        public List<List<Class<?>>> getNodeSignatures() {
            return asList(Arrays.<Class<?>>asList(ValueNode.class));
        }

        @Override
        public List<Class<? extends Node>> getExecutionSignature() {
            return Arrays.<Class<? extends Node>>asList(ValueNode.class);
        }

        public static GlobalFlagGuard createGeneric(GlobalFlagGuard thisNode) {
            return new GlobalFlagGuardGenericNode((GlobalFlagGuardBaseNode) thisNode);
        }

        public static GlobalFlagGuard create(ValueNode expression) {
            return new GlobalFlagGuardUninitializedNode(expression);
        }

        public static NodeFactory<GlobalFlagGuard> getInstance() {
            if (globalFlagGuardFactoryInstance == null) {
                globalFlagGuardFactoryInstance = new GlobalFlagGuardFactory();
            }
            return globalFlagGuardFactoryInstance;
        }

        @GeneratedBy(GlobalFlagGuard.class)
        private abstract static class GlobalFlagGuardBaseNode extends GlobalFlagGuard {

            @Child protected ValueNode expression;

            GlobalFlagGuardBaseNode(ValueNode expression) {
                super();
                this.expression = adoptChild(expression);
            }

            GlobalFlagGuardBaseNode(GlobalFlagGuardBaseNode copy) {
                this.expression = adoptChild(copy.expression);
            }

            @SuppressWarnings("unused")
            protected int executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object expressionValue, String reason) {
                neverPartOfCompilation();
                int result = 0;
                Class<?> resultClass = null;
                boolean allowed = (minimumState == GlobalFlagGuardUninitializedNode.class);
                String message = createInfo0(reason, expressionValue);
                allowed = allowed || (minimumState == GlobalFlagGuardObjectNode.class);
                if (super.globalFlagGuard()) {
                    if (resultClass == null) {
                        result = super.doSpecialized(expressionValue);
                        resultClass = GlobalFlagGuardObjectNode.class;
                    }
                    if (allowed) {
                        super.replace(new GlobalFlagGuardObjectNode(this), message);
                        return result;
                    }
                }
                if (resultClass == null) {
                    result = super.doGeneric(expressionValue);
                    resultClass = GlobalFlagGuardGenericNode.class;
                }
                super.replace(new GlobalFlagGuardGenericNode(this), message);
                return result;
            }

            @SlowPath
            protected int executeGeneric0(Object expressionValue) {
                if (super.globalFlagGuard()) {
                    return super.doSpecialized(expressionValue);
                }
                return super.doGeneric(expressionValue);
            }

            protected static String createInfo0(String message, Object expressionValue) {
                if (DetailedRewriteReasons) {
                    StringBuilder builder = new StringBuilder(message);
                    builder.append(" (");
                    builder.append("expressionValue").append(" = ").append(expressionValue);
                    if (expressionValue != null) {
                        builder.append(" (").append(expressionValue.getClass().getSimpleName()).append(")");
                    }
                    builder.append(")");
                    return builder.toString();
                } else {
                    return message;
                }
            }

        }
        @GeneratedBy(GlobalFlagGuard.class)
        @NodeInfo(kind = Kind.UNINITIALIZED)
        private static final class GlobalFlagGuardUninitializedNode extends GlobalFlagGuardBaseNode {

            GlobalFlagGuardUninitializedNode(ValueNode expression) {
                super(expression);
            }

            GlobalFlagGuardUninitializedNode(GlobalFlagGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                transferToInterpreter();
                Object expressionValue = this.expression.execute(frameValue);
                return super.executeAndSpecialize0(GlobalFlagGuardUninitializedNode.class, frameValue, expressionValue, "Uninitialized monomorphic");
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
        @GeneratedBy(GlobalFlagGuard.class)
        @NodeInfo(kind = Kind.SPECIALIZED)
        private static final class GlobalFlagGuardObjectNode extends GlobalFlagGuardBaseNode {

            GlobalFlagGuardObjectNode(ValueNode expression) {
                super(expression);
            }

            GlobalFlagGuardObjectNode(GlobalFlagGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                Object expressionValue = this.expression.execute(frameValue);
                if (super.globalFlagGuard()) {
                    return super.doSpecialized(expressionValue);
                }
                transferToInterpreter();
                return executeAndSpecialize0(GlobalFlagGuardGenericNode.class, frameValue, expressionValue, "One of guards [globalFlagGuard] failed");
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
        @GeneratedBy(GlobalFlagGuard.class)
        @NodeInfo(kind = Kind.GENERIC)
        private static final class GlobalFlagGuardGenericNode extends GlobalFlagGuardBaseNode {

            GlobalFlagGuardGenericNode(ValueNode expression) {
                super(expression);
            }

            GlobalFlagGuardGenericNode(GlobalFlagGuardBaseNode copy) {
                super(copy);
            }

            @Override
            public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                Object expressionValue = this.expression.execute(frameValue);
                return super.executeGeneric0(expressionValue);
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
    }
}
