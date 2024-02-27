package com.almondtools.regexbench.scaling;

import static java.util.Arrays.asList;

import java.util.List;

public class PSMatchBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

    @Override
    protected ScalingPatternMatcherBenchmark getBenchmark() {
        return new PSMatchBenchmark();
    }

    @Override
    protected List<String> excluded() {
        return asList("human-protein:search", "kjb:search");
    }
}
