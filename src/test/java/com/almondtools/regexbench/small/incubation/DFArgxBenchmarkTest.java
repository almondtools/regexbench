package com.almondtools.regexbench.small.incubation;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.almondtools.regexbench.small.DFArgxBenchmark;
import com.almondtools.regexbench.small.MatcherBenchmark;
import com.almondtools.regexbench.small.MatcherBenchmarkTest;

@RunWith(Parameterized.class)
public class DFArgxBenchmarkTest extends MatcherBenchmarkTest {

	@Override
	public MatcherBenchmark getBenchmark() {
		return new DFArgxBenchmark();
	}

}
