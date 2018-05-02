package com.almondtools.regexbench.scaling;

public class RexlexSearchBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new RexlexSearchBenchmark();
	}

}
