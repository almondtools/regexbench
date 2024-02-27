package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.JdkAutomaton;

@State(Scope.Thread)
public class JdkBenchmark extends ScalingPatternMatcherBenchmark {

    @Override
    public String getId() {
        return "Java.util.regex (NFA)";
    }

    @Override
    public Automaton createAutomaton(String id) {
        return new JdkAutomaton(id);
    }

}
