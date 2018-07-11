package com.almondtools.regexbench.scaling;

import org.junit.Ignore;
import org.junit.Test;

public class JdkBenchmarkTest extends ScalingPatternMatcherBenchmarkTest {

	@Override
	protected ScalingPatternMatcherBenchmark getBenchmark() {
		return new JdkBenchmark();
	}

	@Test 
	@Ignore
	public void testBenchmarkFindInString_kjb_search() throws Exception {
	}

	@Test 
	@Ignore
	public void testBenchmarkFindInFile_kjb_search() throws Exception {
	}
}
