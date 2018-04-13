package com.almondtools.regexbench.incubation;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.almondtools.regexbench.DFArgxBenchmark;
import com.almondtools.regexbench.MatcherBenchmark;
import com.almondtools.regexbench.MatcherBenchmarkTest;

@RunWith(Parameterized.class)
public class DFArgxBenchmarkTest extends MatcherBenchmarkTest {

	@Override
	public MatcherBenchmark getBenchmark() {
		return new DFArgxBenchmark();
	}

}
