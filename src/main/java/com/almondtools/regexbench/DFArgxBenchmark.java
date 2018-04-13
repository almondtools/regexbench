package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class DFArgxBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new DFArgxAutomaton("top.yatt.dfargx (DFA)");
	}
	
}
