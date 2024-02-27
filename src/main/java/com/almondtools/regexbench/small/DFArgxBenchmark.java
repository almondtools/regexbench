package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.DFArgxAutomaton;

@State(Scope.Thread)
public class DFArgxBenchmark extends MatcherBenchmark {

    @Override
    public Automaton createAutomaton() {
        return new DFArgxAutomaton("top.yatt.dfargx (DFA)");
    }

}
