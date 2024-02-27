package com.almondtools.regexbench.scaling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.SampleNotQualifiedException;

@State(Scope.Thread)
public abstract class ScalingPatternMatcherBenchmark {

    private ScalingPatternSample sample;
    private List<Automaton> automatons;
    private int[] result;

    public abstract Automaton createAutomaton(String id);

    public abstract String getId();

    public void preparePatterns(List<String> patterns) {
        automatons = new ArrayList<>(patterns.size());
        for (String pattern : patterns) {
            Automaton automaton = createAutomaton(getId());
            automaton.prepare(pattern);
            automatons.add(automaton);
        }
    }

    public int[] find(String text) {
        int[] result = new int[automatons.size()];
        int i = 0;
        for (Automaton automaton : automatons) {
            result[i] = automaton.find(text);
            i++;
        }
        return result;
    }

    public int[] find(File file) throws IOException {
        int[] result = new int[automatons.size()];
        int i = 0;
        for (Automaton automaton : automatons) {
            result[i] = automaton.find(file);
            i++;
        }
        return result;
    }

    public void free() {
        automatons = null;
    };

    @Setup
    public void setup(ScalingPatternSample sample) {
        if (!sample.isValid()) {
            throw new SampleNotQualifiedException();
        }
        this.sample = sample;
        preparePatterns(sample.getPattern());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Fork(1)
    public void benchmarkFindInString() {
        result = find(sample.getSample());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Fork(1)
    public void benchmarkFindInFile() throws IOException {
        result = find(sample.getFile());
    }

    @TearDown(Level.Iteration)
    public void validate() {
        sample.validate(sample.getPattern(), result);
    }

    @TearDown
    public void tearDown() {
        sample = null;
        result = null;
        free();
    }

}
