package com.almondtools.regexbench;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PSSearchBenchmarkTest extends MatcherBenchmarkTest {

	@Override
	public MatcherBenchmark getBenchmark() {
		return new PSSearchBenchmark();
	}

}
