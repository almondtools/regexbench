package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BricsBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new BricsAutomaton("dk.brics.automaton (DFA)");
	}
	
}
