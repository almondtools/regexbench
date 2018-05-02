package com.almondtools.regexbench.scaling;

public class JdkBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new JdkBenchmark();
	}

}
