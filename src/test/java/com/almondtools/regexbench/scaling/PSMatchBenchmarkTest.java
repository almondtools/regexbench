package com.almondtools.regexbench.scaling;

public class PSMatchBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new PSMatchBenchmark();
	}

}
