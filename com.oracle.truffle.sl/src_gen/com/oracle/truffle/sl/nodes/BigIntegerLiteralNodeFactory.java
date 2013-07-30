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

@GeneratedBy(BigIntegerLiteralNode.class)
public final class BigIntegerLiteralNodeFactory implements NodeFactory<BigIntegerLiteralNode> {

    private static BigIntegerLiteralNodeFactory instance;

    private BigIntegerLiteralNodeFactory() {
    }

    @Override
    public BigIntegerLiteralNode createNode(Object... arguments) {
        if (arguments.length == 1 && (arguments[0] == null || arguments[0] instanceof BigInteger)) {
            return create((BigInteger) arguments[0]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public BigIntegerLiteralNode createNodeGeneric(BigIntegerLiteralNode thisNode) {
        throw new UnsupportedOperationException("No specialized version.");
    }

    @Override
    public Class<BigIntegerLiteralNode> getNodeClass() {
        return BigIntegerLiteralNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(BigInteger.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList();
    }

    public static BigIntegerLiteralNode create(BigInteger value) {
        return new BigIntegerLiteralDefaultNode(value);
    }

    public static NodeFactory<BigIntegerLiteralNode> getInstance() {
        if (instance == null) {
            instance = new BigIntegerLiteralNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(BigIntegerLiteralNode.class)
    private abstract static class BigIntegerLiteralBaseNode extends BigIntegerLiteralNode {

        BigIntegerLiteralBaseNode(BigInteger value) {
            super(value);
        }

    }
    @GeneratedBy(BigIntegerLiteralNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class BigIntegerLiteralDefaultNode extends BigIntegerLiteralBaseNode {

        BigIntegerLiteralDefaultNode(BigInteger value) {
            super(value);
        }

        @Override
        public BigInteger executeBigInteger(VirtualFrame frameValue) throws UnexpectedResultException {
            return super.doBigInteger();
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
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated) {
            return super.doBigInteger();
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated) {
            return super.doBigInteger();
        }

        @Override
        public Object executeEvaluated(VirtualFrame frameValue, Object otherValue0Evaluated, Object otherValue1Evaluated, Object otherValue2Evaluated) {
            return super.doBigInteger();
        }

    }
}
