package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.RexlexAutomaton;
import com.almondtools.rexlex.pattern.DefaultMatcherBuilder;

@State(Scope.Thread)
public class RexlexDefaultBenchmark extends ScalingPatternMatcherBenchmark {

    @Override
    public String getId() {
        return "com.almondtools.rexlex Default (DFA)";
    }

    @Override
    public Automaton createAutomaton(String id) {
        return new RexlexAutomaton(id, new DefaultMatcherBuilder());
    }

}
