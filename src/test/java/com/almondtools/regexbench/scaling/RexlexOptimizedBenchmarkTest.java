package com.almondtools.regexbench.scaling;

public class RexlexOptimizedBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new RexlexOptimizedBenchmark();
	}

}
