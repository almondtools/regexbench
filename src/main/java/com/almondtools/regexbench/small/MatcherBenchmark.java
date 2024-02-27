package com.almondtools.regexbench.small;

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

import com.almondtools.regexbench.Automaton;

@State(Scope.Thread)
public abstract class MatcherBenchmark {

    private Automaton automaton;
    private Automaton preparedAutomaton;
    private SmallSample sample;

    public abstract Automaton createAutomaton();

    private Automaton createAutomaton(String pattern) {
        Automaton automaton = createAutomaton();
        automaton.prepare(pattern);
        return automaton;
    }

    @Setup
    public void setup(SmallSample sample) {
        this.sample = sample;
        this.automaton = createAutomaton();
        this.preparedAutomaton = createAutomaton(sample.getPattern());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    public void benchmarkPrepare() {
        automaton.prepare(sample.getPattern());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    public void benchmarkFind() {
        preparedAutomaton.find(sample.getSample());
    }

    @TearDown
    public void tearDown() {
        int result = preparedAutomaton.find(sample.getSample());
        sample.validate(result, preparedAutomaton.getType());
        this.sample = null;
    }

}
