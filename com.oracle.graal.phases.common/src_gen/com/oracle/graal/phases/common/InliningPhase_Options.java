// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: InliningPhase.java
package com.oracle.graal.phases.common;

import java.util.*;
import com.oracle.graal.options.*;

public class InliningPhase_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("AlwaysInlineIntrinsics", Boolean.class, "Unconditionally inline intrinsics", InliningPhase.Options.class, "AlwaysInlineIntrinsics", InliningPhase.Options.AlwaysInlineIntrinsics)
        );
        return options.iterator();
    }
}
