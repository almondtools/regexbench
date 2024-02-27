package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.PSAutomaton;

import net.amygdalum.patternsearchalgorithms.pattern.OptimizationTarget;

@State(Scope.Thread)
public class PSSearchBenchmark extends ScalingPatternMatcherBenchmark {

    @Override
    public String getId() {
        return "Patternsearchalgorithms (DFA)";
    }

    @Override
    public Automaton createAutomaton(String id) {
        return new PSAutomaton(id, OptimizationTarget.SEARCH);
    }

}
