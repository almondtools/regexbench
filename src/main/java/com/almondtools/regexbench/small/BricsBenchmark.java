package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.BricsAutomaton;

@State(Scope.Thread)
public class BricsBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new BricsAutomaton("dk.brics.automaton (DFA)");
	}
	
}
