package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.SCBPGlushkovAutomaton;

@State(Scope.Thread)
public class SCBPGlushkovBenchmark extends MatcherBenchmark {

    @Override
    public Automaton createAutomaton() {
        return new SCBPGlushkovAutomaton("net.amygdalum.stringsearchalgorithms Glushkov (BPNFA)");
    }

}
