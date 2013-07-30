// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: Suites.java
package com.oracle.graal.phases.tiers;

import java.util.*;
import com.oracle.graal.options.*;

public class Suites_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("CompilerConfiguration", String.class, "The compiler configuration to use", Suites.Options.class, "CompilerConfiguration", Suites.Options.CompilerConfiguration)
        );
        return options.iterator();
    }
}
