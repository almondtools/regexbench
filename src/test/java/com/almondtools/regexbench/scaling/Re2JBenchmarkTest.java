package com.almondtools.regexbench.scaling;

public class Re2JBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new Re2JBenchmark();
	}

}
