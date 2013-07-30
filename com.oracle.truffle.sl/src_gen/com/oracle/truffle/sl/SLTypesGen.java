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
package com.oracle.truffle.sl;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.math.BigInteger;

@GeneratedBy(SLTypes.class)
public class SLTypesGen extends SLTypes {

    public static final SLTypesGen SLTYPES = new SLTypesGen();

    protected SLTypesGen() {
    }

    public int expectInteger(int value) throws UnexpectedResultException {
        if (isInteger(value)) {
            return asInteger(value);
        }
        throw new UnexpectedResultException(value);
    }

    public int expectInteger(Object value) throws UnexpectedResultException {
        if (isInteger(value)) {
            return asInteger(value);
        }
        throw new UnexpectedResultException(value);
    }

    public BigInteger expectBigInteger(BigInteger value) throws UnexpectedResultException {
        if (isBigInteger(value)) {
            return asBigInteger(value);
        }
        throw new UnexpectedResultException(value);
    }

    public BigInteger expectBigInteger(int value) throws UnexpectedResultException {
        if (isBigInteger(value)) {
            return asBigInteger(value);
        }
        throw new UnexpectedResultException(value);
    }

    public BigInteger expectBigInteger(Object value) throws UnexpectedResultException {
        if (isBigInteger(value)) {
            return asBigInteger(value);
        }
        throw new UnexpectedResultException(value);
    }

    public boolean isBoolean(Object value) {
        return value instanceof Boolean;
    }

    public boolean asBoolean(Object value) {
        assert isBoolean(value);
        return (boolean) value;
    }

    public boolean expectBoolean(Object value) throws UnexpectedResultException {
        if (isBoolean(value)) {
            return asBoolean(value);
        }
        throw new UnexpectedResultException(value);
    }

    public boolean isString(Object value) {
        return value instanceof String;
    }

    public String asString(Object value) {
        assert isString(value);
        return (String) value;
    }

    public String expectString(Object value) throws UnexpectedResultException {
        if (isString(value)) {
            return asString(value);
        }
        throw new UnexpectedResultException(value);
    }

}
