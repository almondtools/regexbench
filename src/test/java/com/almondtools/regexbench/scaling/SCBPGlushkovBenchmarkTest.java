package com.almondtools.regexbench.scaling;

public class SCBPGlushkovBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

    @Override
    protected ScalingPatternMatcherBenchmark getBenchmark() {
        return new SCBPGlushkovBenchmark();
    }

}
