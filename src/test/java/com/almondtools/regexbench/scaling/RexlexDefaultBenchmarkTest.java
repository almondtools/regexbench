package com.almondtools.regexbench.scaling;

import static java.util.Arrays.asList;

import java.util.List;

public class RexlexDefaultBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new RexlexDefaultBenchmark();
	}

	@Override
	protected List<String> excluded() {
		return asList("human-protein:search", "kjb:search");
	}
}
