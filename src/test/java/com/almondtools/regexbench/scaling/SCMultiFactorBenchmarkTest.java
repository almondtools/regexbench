package com.almondtools.regexbench.scaling;

import static java.util.Arrays.asList;

import java.util.List;

public class SCMultiFactorBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new SCMultiFactorBenchmark();
	}

	@Override
	protected List<String> excluded() {
		return asList("kjb:search", "human-protein:search");
	}
}
