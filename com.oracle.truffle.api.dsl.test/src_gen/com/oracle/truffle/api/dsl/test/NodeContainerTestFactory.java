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
import com.oracle.truffle.api.dsl.test.NodeContainerTest.BuiltinNode;
import com.oracle.truffle.api.dsl.test.NodeContainerTest.Context;
import com.oracle.truffle.api.dsl.test.NodeContainerTest.Str;
import com.oracle.truffle.api.dsl.test.TypeSystemTest.ValueNode;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(NodeContainerTest.class)
public final class NodeContainerTestFactory {

    private NodeContainerTestFactory() {
    }

    public static List<NodeFactory<BuiltinNode>> getFactories() {
        return asList(StrFactory.StrAccessContextFactory.getInstance(), StrFactory.StrConcatFactory.getInstance(), StrFactory.StrLengthFactory.getInstance(), StrFactory.StrSubstrFactory.getInstance());
    }

    @GeneratedBy(Str.class)
    static final class StrFactory {

        private StrFactory() {
        }

        @GeneratedBy(methodName = "accessContext", value = Str.class)
        static final class StrAccessContextFactory implements NodeFactory<BuiltinNode> {

            private static StrAccessContextFactory strAccessContextFactoryInstance;

            private StrAccessContextFactory() {
            }

            @Override
            public BuiltinNode createNode(Object... arguments) {
                if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof Context) && (arguments[1] == null || arguments[1] instanceof ValueNode[])) {
                    return create((Context) arguments[0], (ValueNode[]) arguments[1]);
                } else {
                    throw new IllegalArgumentException("Invalid create signature.");
                }
            }

            @Override
            public BuiltinNode createNodeGeneric(BuiltinNode thisNode) {
                throw new UnsupportedOperationException("No specialized version.");
            }

            @Override
            public Class<BuiltinNode> getNodeClass() {
                return BuiltinNode.class;
            }

            @Override
            public List<List<Class<?>>> getNodeSignatures() {
                return asList(Arrays.<Class<?>>asList(Context.class, ValueNode[].class));
            }

            @Override
            public List<Class<? extends Node>> getExecutionSignature() {
                return Arrays.<Class<? extends Node>>asList();
            }

            static BuiltinNode create(Context context, ValueNode[] children) {
                return new StrAccessContextDefaultNode(context, children);
            }

            static NodeFactory<BuiltinNode> getInstance() {
                if (strAccessContextFactoryInstance == null) {
                    strAccessContextFactoryInstance = new StrAccessContextFactory();
                }
                return strAccessContextFactoryInstance;
            }

            @GeneratedBy(methodName = "accessContext", value = Str.class)
            private abstract static class StrAccessContextBaseNode extends BuiltinNode {

                @Children protected final ValueNode[] children;

                StrAccessContextBaseNode(Context context, ValueNode[] children) {
                    super(context);
                    this.children = adoptChildren(children);
                }

            }
            @GeneratedBy(methodName = "accessContext", value = Str.class)
            @NodeInfo(kind = Kind.SPECIALIZED)
            private static final class StrAccessContextDefaultNode extends StrAccessContextBaseNode {

                StrAccessContextDefaultNode(Context context, ValueNode[] children) {
                    super(context, children);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    return Str.accessContext(this.context);
                }

            }
        }
        @GeneratedBy(methodName = "concat", value = Str.class)
        static final class StrConcatFactory implements NodeFactory<BuiltinNode> {

            private static StrConcatFactory strConcatFactoryInstance;

            private StrConcatFactory() {
            }

            @Override
            public BuiltinNode createNode(Object... arguments) {
                if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof Context) && (arguments[1] == null || arguments[1] instanceof ValueNode[])) {
                    return create((Context) arguments[0], (ValueNode[]) arguments[1]);
                } else {
                    throw new IllegalArgumentException("Invalid create signature.");
                }
            }

            @Override
            public BuiltinNode createNodeGeneric(BuiltinNode thisNode) {
                return createGeneric(thisNode);
            }

            @Override
            public Class<BuiltinNode> getNodeClass() {
                return BuiltinNode.class;
            }

            @Override
            public List<List<Class<?>>> getNodeSignatures() {
                return asList(Arrays.<Class<?>>asList(Context.class, ValueNode[].class));
            }

            @Override
            public List<Class<? extends Node>> getExecutionSignature() {
                return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class);
            }

            static BuiltinNode createGeneric(BuiltinNode thisNode) {
                return new StrConcatGenericNode((StrConcatBaseNode) thisNode);
            }

            static BuiltinNode create(Context context, ValueNode[] children) {
                return new StrConcatUninitializedNode(context, children);
            }

            static NodeFactory<BuiltinNode> getInstance() {
                if (strConcatFactoryInstance == null) {
                    strConcatFactoryInstance = new StrConcatFactory();
                }
                return strConcatFactoryInstance;
            }

            @GeneratedBy(methodName = "concat", value = Str.class)
            private abstract static class StrConcatBaseNode extends BuiltinNode {

                @Children protected final ValueNode[] children;

                StrConcatBaseNode(Context context, ValueNode[] children) {
                    super(context);
                    this.children = adoptChildren(children);
                }

                StrConcatBaseNode(StrConcatBaseNode copy) {
                    super(copy);
                    this.children = adoptChildren(new ValueNode[] {copy.children[0], copy.children[1]});
                }

                @SuppressWarnings("unused")
                protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object childrenValue0, Object childrenValue1, String reason) {
                    neverPartOfCompilation();
                    Object result = null;
                    Class<?> resultClass = null;
                    boolean allowed = (minimumState == StrConcatUninitializedNode.class);
                    String message = createInfo0(reason, childrenValue0, childrenValue1);
                    allowed = allowed || (minimumState == StrConcatStrNode.class);
                    if (SIMPLETYPES.isStr(childrenValue0) && SIMPLETYPES.isStr(childrenValue1)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        Str childrenValue1Cast = SIMPLETYPES.asStr(childrenValue1);
                        if (resultClass == null) {
                            result = childrenValue0Cast.concat(childrenValue1Cast);
                            resultClass = StrConcatStrNode.class;
                        }
                        if (allowed) {
                            super.replace(new StrConcatStrNode(this), message);
                            return result;
                        }
                    }
                    if (resultClass == null) {
                        throw new UnsupportedOperationException(createInfo0("Unsupported values", childrenValue0, childrenValue1));
                    }
                    super.replace(new StrConcatGenericNode(this), message);
                    return result;
                }

                @SlowPath
                protected Object executeGeneric0(Object childrenValue0, Object childrenValue1) {
                    if (SIMPLETYPES.isStr(childrenValue0) && SIMPLETYPES.isStr(childrenValue1)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        Str childrenValue1Cast = SIMPLETYPES.asStr(childrenValue1);
                        return childrenValue0Cast.concat(childrenValue1Cast);
                    }
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", childrenValue0, childrenValue1));
                }

                protected static String createInfo0(String message, Object childrenValue0, Object childrenValue1) {
                    if (DetailedRewriteReasons) {
                        StringBuilder builder = new StringBuilder(message);
                        builder.append(" (");
                        builder.append("childrenValue0").append(" = ").append(childrenValue0);
                        if (childrenValue0 != null) {
                            builder.append(" (").append(childrenValue0.getClass().getSimpleName()).append(")");
                        }
                        builder.append(", ").append("childrenValue1").append(" = ").append(childrenValue1);
                        if (childrenValue1 != null) {
                            builder.append(" (").append(childrenValue1.getClass().getSimpleName()).append(")");
                        }
                        builder.append(")");
                        return builder.toString();
                    } else {
                        return message;
                    }
                }

            }
            @GeneratedBy(methodName = "concat", value = Str.class)
            @NodeInfo(kind = Kind.UNINITIALIZED)
            private static final class StrConcatUninitializedNode extends StrConcatBaseNode {

                StrConcatUninitializedNode(Context context, ValueNode[] children) {
                    super(context, children);
                }

                StrConcatUninitializedNode(StrConcatBaseNode copy) {
                    super(copy);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    transferToInterpreter();
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    Object childrenValue1 = this.children[1].execute(frameValue);
                    return super.executeAndSpecialize0(StrConcatUninitializedNode.class, frameValue, childrenValue0, childrenValue1, "Uninitialized monomorphic");
                }

            }
            @GeneratedBy(methodName = "concat", value = Str.class)
            @NodeInfo(kind = Kind.SPECIALIZED)
            private static final class StrConcatStrNode extends StrConcatBaseNode {

                StrConcatStrNode(StrConcatBaseNode copy) {
                    super(copy);
                }

                @Override
                public Str executeStr(VirtualFrame frameValue) throws UnexpectedResultException {
                    Str childrenValue0;
                    try {
                        childrenValue0 = this.children[0].executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        Object childrenValue1 = this.children[1].execute(frameValue);
                        return SIMPLETYPES.expectStr(executeAndSpecialize0(StrConcatGenericNode.class, frameValue, ex.getResult(), childrenValue1, "Expected childrenValue0 instanceof Str"));
                    }
                    Str childrenValue1;
                    try {
                        childrenValue1 = this.children[1].executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        return SIMPLETYPES.expectStr(executeAndSpecialize0(StrConcatGenericNode.class, frameValue, childrenValue0, ex.getResult(), "Expected childrenValue1 instanceof Str"));
                    }
                    return childrenValue0.concat(childrenValue1);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Str value;
                    try {
                        value = this.executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        return ex.getResult();
                    }
                    return value;
                }

            }
            @GeneratedBy(methodName = "concat", value = Str.class)
            @NodeInfo(kind = Kind.GENERIC)
            private static final class StrConcatGenericNode extends StrConcatBaseNode {

                StrConcatGenericNode(StrConcatBaseNode copy) {
                    super(copy);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    Object childrenValue1 = this.children[1].execute(frameValue);
                    return super.executeGeneric0(childrenValue0, childrenValue1);
                }

            }
        }
        @GeneratedBy(methodName = "length", value = Str.class)
        static final class StrLengthFactory implements NodeFactory<BuiltinNode> {

            private static StrLengthFactory strLengthFactoryInstance;

            private StrLengthFactory() {
            }

            @Override
            public BuiltinNode createNode(Object... arguments) {
                if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof Context) && (arguments[1] == null || arguments[1] instanceof ValueNode[])) {
                    return create((Context) arguments[0], (ValueNode[]) arguments[1]);
                } else {
                    throw new IllegalArgumentException("Invalid create signature.");
                }
            }

            @Override
            public BuiltinNode createNodeGeneric(BuiltinNode thisNode) {
                return createGeneric(thisNode);
            }

            @Override
            public Class<BuiltinNode> getNodeClass() {
                return BuiltinNode.class;
            }

            @Override
            public List<List<Class<?>>> getNodeSignatures() {
                return asList(Arrays.<Class<?>>asList(Context.class, ValueNode[].class));
            }

            @Override
            public List<Class<? extends Node>> getExecutionSignature() {
                return Arrays.<Class<? extends Node>>asList(ValueNode.class);
            }

            static BuiltinNode createGeneric(BuiltinNode thisNode) {
                return new StrLengthGenericNode((StrLengthBaseNode) thisNode);
            }

            static BuiltinNode create(Context context, ValueNode[] children) {
                return new StrLengthUninitializedNode(context, children);
            }

            static NodeFactory<BuiltinNode> getInstance() {
                if (strLengthFactoryInstance == null) {
                    strLengthFactoryInstance = new StrLengthFactory();
                }
                return strLengthFactoryInstance;
            }

            @GeneratedBy(methodName = "length", value = Str.class)
            private abstract static class StrLengthBaseNode extends BuiltinNode {

                @Children protected final ValueNode[] children;

                StrLengthBaseNode(Context context, ValueNode[] children) {
                    super(context);
                    this.children = adoptChildren(children);
                }

                StrLengthBaseNode(StrLengthBaseNode copy) {
                    super(copy);
                    this.children = adoptChildren(new ValueNode[] {copy.children[0]});
                }

                @SuppressWarnings("unused")
                protected Object executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object childrenValue0, String reason) {
                    neverPartOfCompilation();
                    Object result = null;
                    Class<?> resultClass = null;
                    boolean allowed = (minimumState == StrLengthUninitializedNode.class);
                    String message = createInfo0(reason, childrenValue0);
                    allowed = allowed || (minimumState == StrLengthStrNode.class);
                    if (SIMPLETYPES.isStr(childrenValue0)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        if (resultClass == null) {
                            result = childrenValue0Cast.length();
                            resultClass = StrLengthStrNode.class;
                        }
                        if (allowed) {
                            super.replace(new StrLengthStrNode(this), message);
                            return result;
                        }
                    }
                    if (resultClass == null) {
                        throw new UnsupportedOperationException(createInfo0("Unsupported values", childrenValue0));
                    }
                    super.replace(new StrLengthGenericNode(this), message);
                    return result;
                }

                @SlowPath
                protected Object executeGeneric0(Object childrenValue0) {
                    if (SIMPLETYPES.isStr(childrenValue0)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        return childrenValue0Cast.length();
                    }
                    throw new UnsupportedOperationException(createInfo0("Unsupported values", childrenValue0));
                }

                protected static String createInfo0(String message, Object childrenValue0) {
                    if (DetailedRewriteReasons) {
                        StringBuilder builder = new StringBuilder(message);
                        builder.append(" (");
                        builder.append("childrenValue0").append(" = ").append(childrenValue0);
                        if (childrenValue0 != null) {
                            builder.append(" (").append(childrenValue0.getClass().getSimpleName()).append(")");
                        }
                        builder.append(")");
                        return builder.toString();
                    } else {
                        return message;
                    }
                }

            }
            @GeneratedBy(methodName = "length", value = Str.class)
            @NodeInfo(kind = Kind.UNINITIALIZED)
            private static final class StrLengthUninitializedNode extends StrLengthBaseNode {

                StrLengthUninitializedNode(Context context, ValueNode[] children) {
                    super(context, children);
                }

                StrLengthUninitializedNode(StrLengthBaseNode copy) {
                    super(copy);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    transferToInterpreter();
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    return super.executeAndSpecialize0(StrLengthUninitializedNode.class, frameValue, childrenValue0, "Uninitialized monomorphic");
                }

            }
            @GeneratedBy(methodName = "length", value = Str.class)
            @NodeInfo(kind = Kind.SPECIALIZED)
            private static final class StrLengthStrNode extends StrLengthBaseNode {

                StrLengthStrNode(StrLengthBaseNode copy) {
                    super(copy);
                }

                @Override
                public int executeInt(VirtualFrame frameValue) throws UnexpectedResultException {
                    Str childrenValue0;
                    try {
                        childrenValue0 = this.children[0].executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        return SIMPLETYPES.expectInteger(executeAndSpecialize0(StrLengthGenericNode.class, frameValue, ex.getResult(), "Expected childrenValue0 instanceof Str"));
                    }
                    return childrenValue0.length();
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
            @GeneratedBy(methodName = "length", value = Str.class)
            @NodeInfo(kind = Kind.GENERIC)
            private static final class StrLengthGenericNode extends StrLengthBaseNode {

                StrLengthGenericNode(StrLengthBaseNode copy) {
                    super(copy);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    return super.executeGeneric0(childrenValue0);
                }

            }
        }
        @GeneratedBy(methodName = "substr", value = Str.class)
        static final class StrSubstrFactory implements NodeFactory<BuiltinNode> {

            private static StrSubstrFactory strSubstrFactoryInstance;

            private StrSubstrFactory() {
            }

            @Override
            public BuiltinNode createNode(Object... arguments) {
                if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof Context) && (arguments[1] == null || arguments[1] instanceof ValueNode[])) {
                    return create((Context) arguments[0], (ValueNode[]) arguments[1]);
                } else {
                    throw new IllegalArgumentException("Invalid create signature.");
                }
            }

            @Override
            public BuiltinNode createNodeGeneric(BuiltinNode thisNode) {
                return createGeneric(thisNode);
            }

            @Override
            public Class<BuiltinNode> getNodeClass() {
                return BuiltinNode.class;
            }

            @Override
            public List<List<Class<?>>> getNodeSignatures() {
                return asList(Arrays.<Class<?>>asList(Context.class, ValueNode[].class));
            }

            @Override
            public List<Class<? extends Node>> getExecutionSignature() {
                return Arrays.<Class<? extends Node>>asList(ValueNode.class, ValueNode.class, ValueNode.class);
            }

            static BuiltinNode createGeneric(BuiltinNode thisNode) {
                return new StrSubstrGenericNode((StrSubstrBaseNode) thisNode);
            }

            static BuiltinNode create(Context context, ValueNode[] children) {
                return new StrSubstrUninitializedNode(context, children);
            }

            static NodeFactory<BuiltinNode> getInstance() {
                if (strSubstrFactoryInstance == null) {
                    strSubstrFactoryInstance = new StrSubstrFactory();
                }
                return strSubstrFactoryInstance;
            }

            @GeneratedBy(methodName = "substr", value = Str.class)
            private abstract static class StrSubstrBaseNode extends BuiltinNode {

                @Children protected final ValueNode[] children;

                StrSubstrBaseNode(Context context, ValueNode[] children) {
                    super(context);
                    this.children = adoptChildren(children);
                }

                StrSubstrBaseNode(StrSubstrBaseNode copy) {
                    super(copy);
                    this.children = adoptChildren(new ValueNode[] {copy.children[0], copy.children[1], copy.children[2]});
                }

                @SuppressWarnings("unused")
                protected Str executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object childrenValue0, Object childrenValue1, Object childrenValue2, String reason) {
                    neverPartOfCompilation();
                    Str result = null;
                    Class<?> resultClass = null;
                    boolean allowed = (minimumState == StrSubstrUninitializedNode.class);
                    String message = createInfo0(reason, childrenValue0, childrenValue1, childrenValue2);
                    allowed = allowed || (minimumState == StrSubstrStrIntNode.class);
                    if (SIMPLETYPES.isStr(childrenValue0) && SIMPLETYPES.isInteger(childrenValue1) && SIMPLETYPES.isInteger(childrenValue2)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        int childrenValue1Cast = SIMPLETYPES.asInteger(childrenValue1);
                        int childrenValue2Cast = SIMPLETYPES.asInteger(childrenValue2);
                        if (resultClass == null) {
                            result = childrenValue0Cast.substr(childrenValue1Cast, childrenValue2Cast);
                            resultClass = StrSubstrStrIntNode.class;
                        }
                        if (allowed) {
                            super.replace(new StrSubstrStrIntNode(this), message);
                            return result;
                        }
                    }
                    if (resultClass == null) {
                        result = Str.substr(childrenValue0, childrenValue1, childrenValue2);
                        resultClass = StrSubstrGenericNode.class;
                    }
                    super.replace(new StrSubstrGenericNode(this), message);
                    return result;
                }

                @SlowPath
                protected Str executeGeneric0(Object childrenValue0, Object childrenValue1, Object childrenValue2) {
                    if (SIMPLETYPES.isStr(childrenValue0) && SIMPLETYPES.isInteger(childrenValue1) && SIMPLETYPES.isInteger(childrenValue2)) {
                        Str childrenValue0Cast = SIMPLETYPES.asStr(childrenValue0);
                        int childrenValue1Cast = SIMPLETYPES.asInteger(childrenValue1);
                        int childrenValue2Cast = SIMPLETYPES.asInteger(childrenValue2);
                        return childrenValue0Cast.substr(childrenValue1Cast, childrenValue2Cast);
                    }
                    return Str.substr(childrenValue0, childrenValue1, childrenValue2);
                }

                protected static String createInfo0(String message, Object childrenValue0, Object childrenValue1, Object childrenValue2) {
                    if (DetailedRewriteReasons) {
                        StringBuilder builder = new StringBuilder(message);
                        builder.append(" (");
                        builder.append("childrenValue0").append(" = ").append(childrenValue0);
                        if (childrenValue0 != null) {
                            builder.append(" (").append(childrenValue0.getClass().getSimpleName()).append(")");
                        }
                        builder.append(", ").append("childrenValue1").append(" = ").append(childrenValue1);
                        if (childrenValue1 != null) {
                            builder.append(" (").append(childrenValue1.getClass().getSimpleName()).append(")");
                        }
                        builder.append(", ").append("childrenValue2").append(" = ").append(childrenValue2);
                        if (childrenValue2 != null) {
                            builder.append(" (").append(childrenValue2.getClass().getSimpleName()).append(")");
                        }
                        builder.append(")");
                        return builder.toString();
                    } else {
                        return message;
                    }
                }

            }
            @GeneratedBy(methodName = "substr", value = Str.class)
            @NodeInfo(kind = Kind.UNINITIALIZED)
            private static final class StrSubstrUninitializedNode extends StrSubstrBaseNode {

                StrSubstrUninitializedNode(Context context, ValueNode[] children) {
                    super(context, children);
                }

                StrSubstrUninitializedNode(StrSubstrBaseNode copy) {
                    super(copy);
                }

                @Override
                public Str executeStr(VirtualFrame frameValue) throws UnexpectedResultException {
                    transferToInterpreter();
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    Object childrenValue1 = this.children[1].execute(frameValue);
                    Object childrenValue2 = this.children[2].execute(frameValue);
                    return super.executeAndSpecialize0(StrSubstrUninitializedNode.class, frameValue, childrenValue0, childrenValue1, childrenValue2, "Uninitialized monomorphic");
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Str value;
                    try {
                        value = this.executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        return ex.getResult();
                    }
                    return value;
                }

            }
            @GeneratedBy(methodName = "substr", value = Str.class)
            @NodeInfo(kind = Kind.SPECIALIZED)
            private static final class StrSubstrStrIntNode extends StrSubstrBaseNode {

                StrSubstrStrIntNode(StrSubstrBaseNode copy) {
                    super(copy);
                }

                @Override
                public Str executeStr(VirtualFrame frameValue) throws UnexpectedResultException {
                    Str childrenValue0;
                    try {
                        childrenValue0 = this.children[0].executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        Object childrenValue1 = this.children[1].execute(frameValue);
                        Object childrenValue2 = this.children[2].execute(frameValue);
                        return executeAndSpecialize0(StrSubstrGenericNode.class, frameValue, ex.getResult(), childrenValue1, childrenValue2, "Expected childrenValue0 instanceof Str");
                    }
                    int childrenValue1;
                    try {
                        childrenValue1 = this.children[1].executeInt(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        Object childrenValue2 = this.children[2].execute(frameValue);
                        return executeAndSpecialize0(StrSubstrGenericNode.class, frameValue, childrenValue0, ex.getResult(), childrenValue2, "Expected childrenValue1 instanceof int");
                    }
                    int childrenValue2;
                    try {
                        childrenValue2 = this.children[2].executeInt(frameValue);
                    } catch (UnexpectedResultException ex) {
                        transferToInterpreter();
                        return executeAndSpecialize0(StrSubstrGenericNode.class, frameValue, childrenValue0, childrenValue1, ex.getResult(), "Expected childrenValue2 instanceof int");
                    }
                    return childrenValue0.substr(childrenValue1, childrenValue2);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Str value;
                    try {
                        value = this.executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        return ex.getResult();
                    }
                    return value;
                }

            }
            @GeneratedBy(methodName = "substr", value = Str.class)
            @NodeInfo(kind = Kind.GENERIC)
            private static final class StrSubstrGenericNode extends StrSubstrBaseNode {

                StrSubstrGenericNode(StrSubstrBaseNode copy) {
                    super(copy);
                }

                @Override
                public Str executeStr(VirtualFrame frameValue) throws UnexpectedResultException {
                    Object childrenValue0 = this.children[0].execute(frameValue);
                    Object childrenValue1 = this.children[1].execute(frameValue);
                    Object childrenValue2 = this.children[2].execute(frameValue);
                    return super.executeGeneric0(childrenValue0, childrenValue1, childrenValue2);
                }

                @Override
                public Object execute(VirtualFrame frameValue) {
                    Str value;
                    try {
                        value = this.executeStr(frameValue);
                    } catch (UnexpectedResultException ex) {
                        return ex.getResult();
                    }
                    return value;
                }

            }
        }
    }
}
