// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: HotSpotGraalRuntime.java
package com.oracle.graal.hotspot;

import java.util.*;
import com.oracle.graal.options.*;

public class HotSpotGraalRuntime_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("GraalRuntime", String.class, "The runtime configuration to use", HotSpotGraalRuntime.class, "GraalRuntime", field(HotSpotGraalRuntime.class, "GraalRuntime"))
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
