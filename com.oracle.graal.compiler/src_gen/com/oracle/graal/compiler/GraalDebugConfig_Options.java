// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: GraalDebugConfig.java
package com.oracle.graal.compiler;

import java.util.*;
import com.oracle.graal.options.*;

public class GraalDebugConfig_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("Debug", Boolean.class, "Enable scope-based debugging", GraalDebugConfig.class, "DebugEnabled", GraalDebugConfig.DebugEnabled),
            new OptionDescriptor("Dump", String.class, "Scopes to be dumped", GraalDebugConfig.class, "Dump", GraalDebugConfig.Dump),
            new OptionDescriptor("Meter", String.class, "Scopes to be metered", GraalDebugConfig.class, "Meter", GraalDebugConfig.Meter),
            new OptionDescriptor("Time", String.class, "Scopes to be timed", GraalDebugConfig.class, "Time", GraalDebugConfig.Time),
            new OptionDescriptor("Log", String.class, "Scopes to be logged", GraalDebugConfig.class, "Log", GraalDebugConfig.Log),
            new OptionDescriptor("MethodFilter", String.class, "Filters debug scope output by method name/pattern", GraalDebugConfig.class, "MethodFilter", GraalDebugConfig.MethodFilter),
            new OptionDescriptor("PerThreadDebugValues", Boolean.class, "", GraalDebugConfig.class, "PerThreadDebugValues", GraalDebugConfig.PerThreadDebugValues),
            new OptionDescriptor("SummarizeDebugValues", Boolean.class, "", GraalDebugConfig.class, "SummarizeDebugValues", GraalDebugConfig.SummarizeDebugValues),
            new OptionDescriptor("SummarizePerPhase", Boolean.class, "", GraalDebugConfig.class, "SummarizePerPhase", GraalDebugConfig.SummarizePerPhase),
            new OptionDescriptor("DumpOnError", Boolean.class, "Send Graal IR to dump handlers on error", GraalDebugConfig.class, "DumpOnError", GraalDebugConfig.DumpOnError),
            new OptionDescriptor("DetailedAsserts", Boolean.class, "Enable expensive assertions", GraalDebugConfig.class, "DetailedAsserts", GraalDebugConfig.DetailedAsserts)
        );
        return options.iterator();
    }
}
