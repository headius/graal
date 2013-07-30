// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: GraalCompiler.java
package com.oracle.graal.compiler;

import java.util.*;
import com.oracle.graal.options.*;

public class GraalCompiler_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("VerifyUsageWithEquals", Boolean.class, "", GraalCompiler.class, "VerifyUsageWithEquals", GraalCompiler.VerifyUsageWithEquals),
            new OptionDescriptor("Inline", Boolean.class, "Enable inlining", GraalCompiler.class, "Inline", GraalCompiler.Inline)
        );
        return options.iterator();
    }
}
