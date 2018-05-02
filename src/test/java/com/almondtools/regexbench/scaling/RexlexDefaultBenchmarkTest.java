package com.almondtools.regexbench.scaling;

public class RexlexDefaultBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new RexlexDefaultBenchmark();
	}

}
