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
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(PrintNode.class)
public final class PrintNodeFactory implements NodeFactory<PrintNode> {

    private static PrintNodeFactory instance;

    private PrintNodeFactory() {
    }

    @Override
    public PrintNode createNode(Object... arguments) {
        if (arguments.length == 2 && (arguments[0] == null || arguments[0] instanceof PrintStream) && (arguments[1] == null || arguments[1] instanceof TypedNode)) {
            return create((PrintStream) arguments[0], (TypedNode) arguments[1]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public PrintNode createNodeGeneric(PrintNode thisNode) {
        return createGeneric(thisNode);
    }

    @Override
    public Class<PrintNode> getNodeClass() {
        return PrintNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(PrintStream.class, TypedNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(TypedNode.class);
    }

    public static PrintNode createGeneric(PrintNode thisNode) {
        return new PrintObjectNode((PrintBaseNode) thisNode);
    }

    public static PrintNode create(PrintStream output, TypedNode child0) {
        return new PrintUninitializedNode(output, child0);
    }

    public static NodeFactory<PrintNode> getInstance() {
        if (instance == null) {
            instance = new PrintNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(PrintNode.class)
    private abstract static class PrintBaseNode extends PrintNode {

        @Child protected TypedNode child0;

        PrintBaseNode(PrintStream output, TypedNode child0) {
            super(output);
            this.child0 = adoptChild(child0);
        }

        PrintBaseNode(PrintBaseNode copy) {
            super(copy);
            this.child0 = adoptChild(copy.child0);
        }

        @SuppressWarnings("unused")
        protected void executeAndSpecialize0(Class<?> minimumState, VirtualFrame frameValue, Object child0Value, String reason) {
            neverPartOfCompilation();
            Class<?> resultClass = null;
            boolean allowed = (minimumState == PrintUninitializedNode.class);
            String message = createInfo0(reason, child0Value);
            allowed = allowed || (minimumState == PrintIntNode.class);
            if (SLTYPES.isInteger(child0Value)) {
                int child0ValueCast = SLTYPES.asInteger(child0Value);
                if (resultClass == null) {
                    super.doInt(child0ValueCast);
                    resultClass = PrintIntNode.class;
                }
                if (allowed) {
                    super.replace(new PrintIntNode(this), message);
                    return;
                }
            }
            allowed = allowed || (minimumState == PrintBooleanNode.class);
            if (SLTYPES.isBoolean(child0Value)) {
                boolean child0ValueCast = SLTYPES.asBoolean(child0Value);
                if (resultClass == null) {
                    super.doBoolean(child0ValueCast);
                    resultClass = PrintBooleanNode.class;
                }
                if (allowed) {
                    super.replace(new PrintBooleanNode(this), message);
                    return;
                }
            }
            allowed = allowed || (minimumState == PrintStringNode.class);
            if (SLTYPES.isString(child0Value)) {
                String child0ValueCast = SLTYPES.asString(child0Value);
                if (resultClass == null) {
                    super.doString(child0ValueCast);
                    resultClass = PrintStringNode.class;
                }
                if (allowed) {
                    super.replace(new PrintStringNode(this), message);
                    return;
                }
            }
            allowed = allowed || (minimumState == PrintObjectNode.class);
            if (resultClass == null) {
                super.doGeneric(child0Value);
                resultClass = PrintObjectNode.class;
            }
            super.replace(new PrintObjectNode(this), message);
            return;
            // unreachable Generic
        }

        protected static String createInfo0(String message, Object child0Value) {
            if (DetailedRewriteReasons) {
                StringBuilder builder = new StringBuilder(message);
                builder.append(" (");
                builder.append("child0Value").append(" = ").append(child0Value);
                if (child0Value != null) {
                    builder.append(" (").append(child0Value.getClass().getSimpleName()).append(")");
                }
                builder.append(")");
                return builder.toString();
            } else {
                return message;
            }
        }

    }
    @GeneratedBy(PrintNode.class)
    @NodeInfo(kind = Kind.UNINITIALIZED)
    private static final class PrintUninitializedNode extends PrintBaseNode {

        PrintUninitializedNode(PrintStream output, TypedNode child0) {
            super(output, child0);
        }

        PrintUninitializedNode(PrintBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            transferToInterpreter();
            Object child0Value = this.child0.executeGeneric(frameValue);
            super.executeAndSpecialize0(PrintUninitializedNode.class, frameValue, child0Value, "Uninitialized monomorphic");
            return;
        }

    }
    @GeneratedBy(PrintNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class PrintIntNode extends PrintBaseNode {

        PrintIntNode(PrintBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            int child0Value;
            try {
                child0Value = this.child0.executeInteger(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                executeAndSpecialize0(PrintBooleanNode.class, frameValue, ex.getResult(), "Expected child0Value instanceof int");
                return;
            }
            super.doInt(child0Value);
            return;
        }

    }
    @GeneratedBy(PrintNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class PrintBooleanNode extends PrintBaseNode {

        PrintBooleanNode(PrintBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            boolean child0Value;
            try {
                child0Value = this.child0.executeBoolean(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                executeAndSpecialize0(PrintStringNode.class, frameValue, ex.getResult(), "Expected child0Value instanceof boolean");
                return;
            }
            super.doBoolean(child0Value);
            return;
        }

    }
    @GeneratedBy(PrintNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class PrintStringNode extends PrintBaseNode {

        PrintStringNode(PrintBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            String child0Value;
            try {
                child0Value = this.child0.executeString(frameValue);
            } catch (UnexpectedResultException ex) {
                transferToInterpreter();
                executeAndSpecialize0(PrintObjectNode.class, frameValue, ex.getResult(), "Expected child0Value instanceof String");
                return;
            }
            super.doString(child0Value);
            return;
        }

    }
    @GeneratedBy(PrintNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class PrintObjectNode extends PrintBaseNode {

        PrintObjectNode(PrintBaseNode copy) {
            super(copy);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            Object child0Value = this.child0.executeGeneric(frameValue);
            super.doGeneric(child0Value);
            return;
        }

    }
}
