package com.almondtools.regexbench;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public abstract class SearchPerformanceModule {

	private Sample sample;
	private int result;

	public abstract void prepare(String pattern);

	public abstract int match(String text);

	public abstract String getId();

	@Setup
	public void setup(Sample sample) {
		this.sample = sample;
		prepare(sample.getPattern());
	}

	@Benchmark
	@Warmup(iterations = 10)
	@Measurement(iterations = 10, timeUnit=MICROSECONDS)
	@Fork(9)
	public void benchmark() {
		result = match(sample.getSample());
	}

	@TearDown
	public void tearDown() {
		if (result != sample.getExpected()) {
			throw new RuntimeException("Matcher " + getId() + " produces wrong results. Expected: " + sample.getExpected() + ", Actual: " + result);
		}
	}

}
