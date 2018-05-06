package com.almondtools.regexbench.scaling;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import com.almondtools.regexbench.CompareResultNotAccepted;

public abstract class ScalingPatternMatcherBenchmarkTest extends ScalingPatternTest {

	@Rule
	public Stopwatch watch = new Stopwatch() {
		protected void succeeded(long nanos, Description description) {
			System.out.println("time: " + (nanos / 1_000_000) + " milliseconds.");
		};
	};

	@Rule
	public CompareResultNotAccepted compare = CompareResultNotAccepted.compare();

	private ScalingPatternMatcherBenchmark benchmark;

	protected abstract ScalingPatternMatcherBenchmark getBenchmark();

	@Before
	public void before() throws Exception {
		benchmark = getBenchmark();
	}

	@After
	public void after() throws Exception {
		benchmark.tearDown();
		benchmark = null;
	}

	public void findInStringSample(String corpus) {
		ScalingPatternSample sample = createSample(corpus);
		long start = System.currentTimeMillis();
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		long prepare = System.currentTimeMillis();
		System.out.println("preparing in " + (prepare - start) + " milliseconds");
		benchmark.benchmarkFindInString();
		long find = System.currentTimeMillis();
		System.out.println("finding in " + (find - prepare) + " milliseconds");
		benchmark.validate();
		long finish = System.currentTimeMillis();
		System.out.println("overall " + (finish - start) + " milliseconds");
	}

	public void findInFileSample(String corpus) throws Exception {
		ScalingPatternSample sample = createSample(corpus);
		long start = System.currentTimeMillis();
		System.out.println("[Search in String] " + benchmark.getId() + " for " + sample.toString());
		benchmark.setup(sample);
		long prepare = System.currentTimeMillis();
		System.out.println("preparing in " + (prepare - start) + " milliseconds");
		benchmark.benchmarkFindInFile();
		long find = System.currentTimeMillis();
		System.out.println("finding in " + (find - prepare) + " milliseconds");
		benchmark.validate();
		long finish = System.currentTimeMillis();
		System.out.println("overall " + (finish - start) + " milliseconds");
	}

	@Test
	public void testBenchmarkFindInString_ecoli() throws Exception {
		findInStringSample("ecoli");
	}

	@Test
	public void testBenchmarkFindInString_ecoli_search() throws Exception {
		findInStringSample("ecoli:search");
	}

	@Test
	public void testBenchmarkFindInString_humanprotein() throws Exception {
		findInStringSample("human-protein");
	}

	@Test
	public void testBenchmarkFindInString_humanprotein_search() throws Exception {
		findInStringSample("human-protein:search");
	}

	@Test
	public void testBenchmarkFindInString_kjb() throws Exception {
		findInStringSample("kjb");
	}

	@Test
	public void testBenchmarkFindInString_kjb_search() throws Exception {
		findInStringSample("kjb:search");
	}

	@Test
	public void testBenchmarkFindInFile_ecoli() throws Exception {
		findInFileSample("ecoli");
	}

	@Test
	public void testBenchmarkFindInFile_ecoli_search() throws Exception {
		findInFileSample("ecoli:search");
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein() throws Exception {
		findInFileSample("human-protein");
	}

	@Test
	public void testBenchmarkFindInFile_humanprotein_search() throws Exception {
		findInFileSample("human-protein:search");
	}

	@Test
	public void testBenchmarkFindInFile_kjb() throws Exception {
		findInFileSample("kjb");
	}

	@Test
	public void testBenchmarkFindInFile_kjb_search() throws Exception {
		findInFileSample("kjb:search");
	}
}
