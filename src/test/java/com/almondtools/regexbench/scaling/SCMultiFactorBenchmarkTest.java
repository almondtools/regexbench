package com.almondtools.regexbench.scaling;

public class SCMultiFactorBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new SCMultiFactorBenchmark();
	}

}
