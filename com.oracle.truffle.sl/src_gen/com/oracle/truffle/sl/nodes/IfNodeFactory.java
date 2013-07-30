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
import java.util.Arrays;
import java.util.List;

@GeneratedBy(IfNode.class)
public final class IfNodeFactory implements NodeFactory<IfNode> {

    private static IfNodeFactory instance;

    private IfNodeFactory() {
    }

    @Override
    public IfNode createNode(Object... arguments) {
        if (arguments.length == 3 && (arguments[0] == null || arguments[0] instanceof StatementNode) && (arguments[1] == null || arguments[1] instanceof StatementNode) && (arguments[2] == null ||
                    arguments[2] instanceof ConditionNode)) {
            return create((StatementNode) arguments[0], (StatementNode) arguments[1], (ConditionNode) arguments[2]);
        } else {
            throw new IllegalArgumentException("Invalid create signature.");
        }
    }

    @Override
    public IfNode createNodeGeneric(IfNode thisNode) {
        throw new UnsupportedOperationException("No specialized version.");
    }

    @Override
    public Class<IfNode> getNodeClass() {
        return IfNode.class;
    }

    @Override
    public List<List<Class<?>>> getNodeSignatures() {
        return asList(Arrays.<Class<?>>asList(StatementNode.class, StatementNode.class, ConditionNode.class));
    }

    @Override
    public List<Class<? extends Node>> getExecutionSignature() {
        return Arrays.<Class<? extends Node>>asList(ConditionNode.class);
    }

    public static IfNode create(StatementNode thenPart, StatementNode elsePart, ConditionNode conditionNode) {
        return new IfBooleanNode(thenPart, elsePart, conditionNode);
    }

    public static NodeFactory<IfNode> getInstance() {
        if (instance == null) {
            instance = new IfNodeFactory();
        }
        return instance;
    }

    @GeneratedBy(IfNode.class)
    private abstract static class IfBaseNode extends IfNode {

        @Child protected ConditionNode conditionNode;

        IfBaseNode(StatementNode thenPart, StatementNode elsePart, ConditionNode conditionNode) {
            super(thenPart, elsePart);
            this.conditionNode = adoptChild(conditionNode);
        }

    }
    @GeneratedBy(IfNode.class)
    @NodeInfo(kind = Kind.SPECIALIZED)
    private static final class IfBooleanNode extends IfBaseNode {

        IfBooleanNode(StatementNode thenPart, StatementNode elsePart, ConditionNode conditionNode) {
            super(thenPart, elsePart, conditionNode);
        }

        @Override
        public void executeVoid(VirtualFrame frameValue) {
            boolean conditionNodeValue = this.conditionNode.executeCondition(frameValue);
            super.doVoid(frameValue, conditionNodeValue);
            return;
        }

    }
}
