package com.almondtools.regexbench;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SCBPGlushkovBenchmarkTest extends MatcherBenchmarkTest {

	@Override
	public MatcherBenchmark getBenchmark() {
		return new SCBPGlushkovBenchmark();
	}

}
