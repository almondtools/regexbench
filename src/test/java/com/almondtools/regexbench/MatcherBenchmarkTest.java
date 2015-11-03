package com.almondtools.regexbench;

import java.io.IOException;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MatcherBenchmarkTest {

	@DataPoints
	public static MatcherBenchmark[] benchmark = {
		new BriksMatcherBenchmark(),
		new JdkMatcherBenchmark(),
		new JRegexMatcherBenchmark(),
		new RexlexDefaultMatcherBenchmark(),
		new RexlexOptimizedMatcherBenchmark(),
		new RexlexSearchMatcherBenchmark()
	};

	@DataPoints
	public static Sample[] sample = {
		createSample("test1"),
		createSample("test2"),
		createSample("test3"),
		createSample("test4"),
		createSample("test5"),
		createSample("test6"),
		createSample("test7"),
		createSample("test8"),
		createSample("test9"),
		createSample("test10")
	};

	@Theory
	public void testBenchmarkFind(MatcherBenchmark benchmark, Sample sample) throws Exception {
		benchmark.setup(sample);
		benchmark.benchmarkFind();
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
