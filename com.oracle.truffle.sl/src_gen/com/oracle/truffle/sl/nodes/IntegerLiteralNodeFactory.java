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

import static com.oracle.truffle.sl.SLTypesGen.*;
import static java.util.Arrays.*;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.NodeInfo.Kind;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.util.Arrays;
import java.util.List;

@GeneratedBy(IntegerLiteralNode.class)
public final class IntegerLiteralNodeFactory implements NodeFactory<IntegerLiteralNode> {

    private static IntegerLiteralNodeFactory instance;

    private IntegerLiteralNodeFactory() {
    }

    @Override
    public IntegerLiteralNode createNode(Object... arguments) {
        if (arguments.length == 1 && arguments[0] instanceof Integer) {
            return create((int) arguments[0]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public IntegerLiteralNode createNodeGeneric(IntegerLiteralNode thisNode) {
        throw new UnsupportedOperationException("No specialized version.");
    }

    @Override
    public Class<IntegerLiteralNode> getNodeClass() {
        return IntegerLiteralNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(int.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList();
    }

    public static IntegerLiteralNode create(int value) {
        return new IntegerLiteralDefaultNode(value);
    }

    public static NodeFactory<IntegerLiteralNode> getInstance() {
        if (instance == null) {
            instance = new IntegerLiteralNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(IntegerLiteralNode.class)
    private abstract static class IntegerLiteralBaseNode extends IntegerLiteralNode {

        IntegerLiteralBaseNode(int value) {
            super(value);
        }

    }
    @GeneratedBy(IntegerLiteralNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class IntegerLiteralDefaultNode extends IntegerLiteralBaseNode {

        IntegerLiteralDefaultNode(int value) {
            super(value);
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
            return super.doInteger();
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
            return super.doInteger();
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            return super.doInteger();
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            return super.doInteger();
        }

    }
}
