package com.almondtools.regexbench.scaling;

import static java.util.Arrays.asList;

import java.util.List;

public class JdkBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

    @Override
    protected ScalingPatternMatcherBenchmark getBenchmark() {
        return new JdkBenchmark();
    }

    @Override
    protected List<String> excluded() {
        return asList("kjb:search");
    }
}
