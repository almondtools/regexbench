package com.almondtools.regexbench;

import java.io.IOException;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MatcherFindBenchmarkTest {

	@DataPoints
	public static MatcherFindBenchmark[] find = {
		new BricsMatcherFindBenchmark(),
		new JdkMatcherFindBenchmark(),
		new JRegexMatcherFindBenchmark(),
		new RexlexDefaultMatcherFindBenchmark(),
		new RexlexOptimizedMatcherFindBenchmark(),
		new RexlexSearchMatcherFindBenchmark(),
		new SCBPGlushkovMatcherFindBenchmark(),
		new SCMultiFactorMatcherFindBenchmark()
	};

	@DataPoints
	public static MatcherPrepareBenchmark[] prepare = {
		new BricsMatcherPrepareBenchmark(),
		new JdkMatcherPrepareBenchmark(),
		new JRegexMatcherPrepareBenchmark(),
		new RexlexDefaultMatcherPrepareBenchmark(),
		new RexlexOptimizedMatcherPrepareBenchmark(),
		new RexlexSearchMatcherPrepareBenchmark(),
		new SCBPGlushkovMatcherPrepareBenchmark(),
		new SCMultiFactorMatcherPrepareBenchmark()
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
		createSample("test10"),
		createSample("test11"),
		createSample("test12")
	};

	@Theory
	public void testBenchmarkFind(MatcherFindBenchmark benchmark, Sample sample) throws Exception {
		if (sample.rejects(benchmark.getAutomaton().getType())) {
			return;
		}
		benchmark.setup(sample);

		benchmark.benchmarkFind();

		benchmark.tearDown();
	}

	@Theory
	public void testBenchmarkPrepare(MatcherPrepareBenchmark benchmark, Sample sample) throws Exception {
		if (sample.rejects(benchmark.getAutomaton().getType())) {
			return;
		}
		benchmark.setup(sample);

		benchmark.benchmarkPrepare();

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
