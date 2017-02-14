package com.almondtools.regexbench;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public abstract class MatcherBenchmarkTest {
	
	@Parameter
	public Sample sample;

	@Parameters(name="{0}")
	public static List<Sample> samples() {
		return Arrays.asList(
			createSample("test1"),
			createSample("test2"),
			createSample("test3"),
			createSample("test4"),
			createSample("test5"),
			createSample("test6"),
			createSample("test7"),
			createSample("test8"),
			createSample("test9"),
			createSample("test10"),
			createSample("test11"),
			createSample("test12"));
	}
	
	public abstract MatcherBenchmark getBenchmark();

	@Test
	public void testBenchmarkFind() throws Exception {
		MatcherBenchmark benchmark = getBenchmark();
		if (sample.rejects(benchmark.createAutomaton().getType())) {
			return;
		}
		benchmark.setup(sample);

		benchmark.benchmarkFind();

		benchmark.tearDown();
	}


	private static Sample createSample(String name) {
		try {
			Sample sample = new Sample();
			sample.setName(name);
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
