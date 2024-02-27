package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.SCMultiFactorAutomaton;

@State(Scope.Thread)
public class SCMultiFactorBenchmark extends ScalingPatternMatcherBenchmark {

    @Override
    public String getId() {
        return "net.amygdalum.stringsearchalgorithms MultiFactorRE (DFA)";
    }

    @Override
    public Automaton createAutomaton(String id) {
        return new SCMultiFactorAutomaton(id);
    }

}
