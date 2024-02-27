package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.Re2JAutomaton;

@State(Scope.Thread)
public class Re2JBenchmark extends ScalingPatternMatcherBenchmark {

    @Override
    public String getId() {
        return "Re2/J (NFA)";
    }

    @Override
    public Automaton createAutomaton(String id) {
        return new Re2JAutomaton(id);
    }

}
