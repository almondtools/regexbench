package com.almondtools.regexbench;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
public abstract class MatcherFindBenchmark {

	private Automaton automaton;
	private Sample sample;
	private int result;
	
	public abstract Automaton getAutomaton();

	@Setup
	public void setup(Sample sample) {
		this.sample = sample;
		this.automaton = getAutomaton();
		automaton.prepare(sample.getPattern());
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	@Warmup(iterations = 10)
	@Measurement(iterations = 10)
	@Fork(1)
	public void benchmarkFind() {
		result = automaton.find(sample.getSample());
	}

	@TearDown
	public void tearDown() {
		sample.validate(result, automaton.getType());
		this.sample = null;
	}

}
