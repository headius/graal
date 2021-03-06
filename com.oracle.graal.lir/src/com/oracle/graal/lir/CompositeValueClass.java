/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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
package com.oracle.graal.lir;

import java.lang.reflect.*;
import java.util.*;

import com.oracle.graal.graph.*;
import com.oracle.graal.lir.LIRInstruction.OperandFlag;
import com.oracle.graal.lir.LIRInstruction.OperandMode;
import com.oracle.graal.lir.LIRInstruction.ValueProcedure;

public class CompositeValueClass extends LIRIntrospection {

    public static final CompositeValueClass get(Class<? extends CompositeValue> c) {
        CompositeValueClass clazz = (CompositeValueClass) allClasses.get(c);
        if (clazz != null) {
            return clazz;
        }

        // We can have a race of multiple threads creating the LIRInstructionClass at the same time.
        // However, only one will be put into the map, and this is the one returned by all threads.
        clazz = new CompositeValueClass(c);
        CompositeValueClass oldClazz = (CompositeValueClass) allClasses.putIfAbsent(c, clazz);
        if (oldClazz != null) {
            return oldClazz;
        } else {
            return clazz;
        }
    }

    private final int directComponentCount;
    private final long[] componentOffsets;
    private final EnumSet<OperandFlag>[] componentFlags;

    @SuppressWarnings("unchecked")
    public CompositeValueClass(Class<? extends CompositeValue> clazz) {
        super(clazz);

        ValueFieldScanner scanner = new ValueFieldScanner(new DefaultCalcOffset());
        scanner.scan(clazz);

        OperandModeAnnotation mode = scanner.valueAnnotations.get(CompositeValue.Component.class);
        directComponentCount = mode.scalarOffsets.size();
        componentOffsets = sortedLongCopy(mode.scalarOffsets, mode.arrayOffsets);
        componentFlags = arrayUsingSortedOffsets(mode.flags, componentOffsets, new EnumSet[componentOffsets.length]);

        dataOffsets = sortedLongCopy(scanner.dataOffsets);

        fieldNames = scanner.fieldNames;
        fieldTypes = scanner.fieldTypes;
    }

    @Override
    protected void rescanFieldOffsets(CalcOffset calc) {
        ValueFieldScanner scanner = new ValueFieldScanner(calc);
        scanner.scan(clazz);

        OperandModeAnnotation mode = scanner.valueAnnotations.get(CompositeValue.Component.class);
        copyInto(componentOffsets, sortedLongCopy(mode.scalarOffsets, mode.arrayOffsets));

        copyInto(dataOffsets, sortedLongCopy(scanner.dataOffsets));

        fieldNames.clear();
        fieldNames.putAll(scanner.fieldNames);
        fieldTypes.clear();
        fieldTypes.putAll(scanner.fieldTypes);
    }

    private static class ValueFieldScanner extends FieldScanner {

        public ValueFieldScanner(CalcOffset calc) {
            super(calc);

            valueAnnotations.put(CompositeValue.Component.class, new OperandModeAnnotation());
        }

        @Override
        protected void scan(Class<?> clazz) {
            super.scan(clazz);
        }

        @Override
        protected EnumSet<OperandFlag> getFlags(Field field) {
            EnumSet<OperandFlag> result = EnumSet.noneOf(OperandFlag.class);
            if (field.isAnnotationPresent(CompositeValue.Component.class)) {
                result.addAll(Arrays.asList(field.getAnnotation(CompositeValue.Component.class).value()));
            } else {
                GraalInternalError.shouldNotReachHere();
            }
            return result;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName()).append(" ").append(clazz.getSimpleName()).append(" component[");
        for (int i = 0; i < componentOffsets.length; i++) {
            str.append(i == 0 ? "" : ", ").append(componentOffsets[i]);
        }
        str.append("] data[");
        for (int i = 0; i < dataOffsets.length; i++) {
            str.append(i == 0 ? "" : ", ").append(dataOffsets[i]);
        }
        str.append("]");
        return str.toString();
    }

    public final void forEachComponent(CompositeValue obj, OperandMode mode, ValueProcedure proc) {
        forEach(obj, directComponentCount, componentOffsets, mode, componentFlags, proc);
    }

    public String toString(CompositeValue obj) {
        StringBuilder result = new StringBuilder();

        appendValues(result, obj, "", "", "{", "}", new String[]{""}, componentOffsets);

        for (int i = 0; i < dataOffsets.length; i++) {
            result.append(" ").append(fieldNames.get(dataOffsets[i])).append(": ").append(getFieldString(obj, dataOffsets[i]));
        }

        return result.toString();
    }
}
