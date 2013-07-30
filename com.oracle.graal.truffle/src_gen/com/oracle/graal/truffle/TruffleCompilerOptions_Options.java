// CheckStyle: stop header check
// GENERATED CONTENT - DO NOT EDIT
// Source: TruffleCompilerOptions.java
package com.oracle.graal.truffle;

import java.util.*;
import com.oracle.graal.options.*;

public class TruffleCompilerOptions_Options implements Options {
    @Override
    public Iterator<OptionDescriptor> iterator() {
        List<OptionDescriptor> options = Arrays.asList(
            new OptionDescriptor("TruffleCompileOnly", String.class, "", TruffleCompilerOptions.class, "TruffleCompileOnly", TruffleCompilerOptions.TruffleCompileOnly),
            new OptionDescriptor("TruffleCompilationThreshold", Integer.class, "", TruffleCompilerOptions.class, "TruffleCompilationThreshold", TruffleCompilerOptions.TruffleCompilationThreshold),
            new OptionDescriptor("TruffleInvalidationReprofileCount", Integer.class, "", TruffleCompilerOptions.class, "TruffleInvalidationReprofileCount", TruffleCompilerOptions.TruffleInvalidationReprofileCount),
            new OptionDescriptor("TruffleReplaceReprofileCount", Integer.class, "", TruffleCompilerOptions.class, "TruffleReplaceReprofileCount", TruffleCompilerOptions.TruffleReplaceReprofileCount),
            new OptionDescriptor("TruffleInliningReprofileCount", Integer.class, "", TruffleCompilerOptions.class, "TruffleInliningReprofileCount", TruffleCompilerOptions.TruffleInliningReprofileCount),
            new OptionDescriptor("TruffleFunctionInlining", Boolean.class, "", TruffleCompilerOptions.class, "TruffleFunctionInlining", TruffleCompilerOptions.TruffleFunctionInlining),
            new OptionDescriptor("TruffleConstantUnrollLimit", Integer.class, "", TruffleCompilerOptions.class, "TruffleConstantUnrollLimit", TruffleCompilerOptions.TruffleConstantUnrollLimit),
            new OptionDescriptor("TruffleOperationCacheMaxNodes", Integer.class, "", TruffleCompilerOptions.class, "TruffleOperationCacheMaxNodes", TruffleCompilerOptions.TruffleOperationCacheMaxNodes),
            new OptionDescriptor("TruffleGraphMaxNodes", Integer.class, "", TruffleCompilerOptions.class, "TruffleGraphMaxNodes", TruffleCompilerOptions.TruffleGraphMaxNodes),
            new OptionDescriptor("TruffleInliningMaxRecursiveDepth", Integer.class, "", TruffleCompilerOptions.class, "TruffleInliningMaxRecursiveDepth", TruffleCompilerOptions.TruffleInliningMaxRecursiveDepth),
            new OptionDescriptor("TruffleInliningMaxCallerSize", Integer.class, "", TruffleCompilerOptions.class, "TruffleInliningMaxCallerSize", TruffleCompilerOptions.TruffleInliningMaxCallerSize),
            new OptionDescriptor("TruffleInliningMaxCalleeSize", Integer.class, "", TruffleCompilerOptions.class, "TruffleInliningMaxCalleeSize", TruffleCompilerOptions.TruffleInliningMaxCalleeSize),
            new OptionDescriptor("TruffleInliningTrivialSize", Integer.class, "", TruffleCompilerOptions.class, "TruffleInliningTrivialSize", TruffleCompilerOptions.TruffleInliningTrivialSize),
            new OptionDescriptor("TruffleInliningMinFrequency", Double.class, "", TruffleCompilerOptions.class, "TruffleInliningMinFrequency", TruffleCompilerOptions.TruffleInliningMinFrequency),
            new OptionDescriptor("TraceTruffleCompilation", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleCompilation", TruffleCompilerOptions.TraceTruffleCompilation),
            new OptionDescriptor("TraceTruffleCompilationDetails", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleCompilationDetails", TruffleCompilerOptions.TraceTruffleCompilationDetails),
            new OptionDescriptor("TraceTruffleCacheDetails", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleCacheDetails", TruffleCompilerOptions.TraceTruffleCacheDetails),
            new OptionDescriptor("TraceTrufflePerformanceWarnings", Boolean.class, "", TruffleCompilerOptions.class, "TraceTrufflePerformanceWarnings", TruffleCompilerOptions.TraceTrufflePerformanceWarnings),
            new OptionDescriptor("TruffleInlinePrinter", Boolean.class, "", TruffleCompilerOptions.class, "TruffleInlinePrinter", TruffleCompilerOptions.TruffleInlinePrinter),
            new OptionDescriptor("TraceTruffleCompilationExceptions", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleCompilationExceptions", TruffleCompilerOptions.TraceTruffleCompilationExceptions),
            new OptionDescriptor("TruffleCompilationExceptionsAreFatal", Boolean.class, "", TruffleCompilerOptions.class, "TruffleCompilationExceptionsAreFatal", TruffleCompilerOptions.TruffleCompilationExceptionsAreFatal),
            new OptionDescriptor("TraceTruffleInlining", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleInlining", TruffleCompilerOptions.TraceTruffleInlining),
            new OptionDescriptor("TraceTruffleInliningDetails", Boolean.class, "", TruffleCompilerOptions.class, "TraceTruffleInliningDetails", TruffleCompilerOptions.TraceTruffleInliningDetails),
            new OptionDescriptor("TruffleCallTargetProfiling", Boolean.class, "", TruffleCompilerOptions.class, "TruffleCallTargetProfiling", TruffleCompilerOptions.TruffleCallTargetProfiling)
        );
        return options.iterator();
    }
}
