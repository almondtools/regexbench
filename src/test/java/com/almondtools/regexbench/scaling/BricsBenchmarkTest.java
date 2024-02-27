package com.almondtools.regexbench.scaling;

public class BricsBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

    @Override
    protected ScalingPatternMatcherBenchmark getBenchmark() {
        return new BricsBenchmark();
    }

}
