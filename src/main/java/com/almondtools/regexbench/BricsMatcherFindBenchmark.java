package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BricsMatcherFindBenchmark extends MatcherFindBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new BricsAutomaton("dk.brics.automaton (DFA)");
	}
	
}
