// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: VMToCompilerImpl.java
package com.oracle.graal.hotspot.bridge;

import java.util.*;
import com.oracle.graal.options.*;

public class VMToCompilerImpl_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("LogFile", String.class, "File to which compiler logging is sent", VMToCompilerImpl.class, "LogFile", field(VMToCompilerImpl.class, "LogFile")),
            new OptionDescriptor("PrintQueue", Boolean.class, "Print compilation queue activity periodically", VMToCompilerImpl.class, "PrintQueue", field(VMToCompilerImpl.class, "PrintQueue")),
            new OptionDescriptor("SlowQueueCutoff", Integer.class, "", VMToCompilerImpl.class, "SlowQueueCutoff", VMToCompilerImpl.SlowQueueCutoff),
            new OptionDescriptor("TimedBootstrap", Integer.class, "Time limit in milliseconds for bootstrap (-1 for no limit)", VMToCompilerImpl.class, "TimedBootstrap", field(VMToCompilerImpl.class, "TimedBootstrap")),
            new OptionDescriptor("Threads", Integer.class, "Number of compilation threads to use", VMToCompilerImpl.class, "Threads", field(VMToCompilerImpl.class, "Threads")),
            new OptionDescriptor("GenericDynamicCounters", Boolean.class, "", VMToCompilerImpl.class, "GenericDynamicCounters", field(VMToCompilerImpl.class, "GenericDynamicCounters")),
            new OptionDescriptor("BenchmarkDynamicCounters", String.class, "", VMToCompilerImpl.class, "BenchmarkDynamicCounters", field(VMToCompilerImpl.class, "BenchmarkDynamicCounters"))
        );
        return options.iterator();
    }
    private static OptionValue field(Class<?> declaringClass, String fieldName) {
        try {
            java.lang.reflect.Field field = declaringClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (OptionValue) field.get(null);
        } catch (Exception e) {
            throw (InternalError) new InternalError().initCause(e);
        }
    }
}
