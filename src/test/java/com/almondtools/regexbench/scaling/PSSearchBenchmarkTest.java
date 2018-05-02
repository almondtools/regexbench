package com.almondtools.regexbench.scaling;

public class PSSearchBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new PSSearchBenchmark();
	}

}
