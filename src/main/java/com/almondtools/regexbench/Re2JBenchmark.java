package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Re2JBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new Re2JAutomaton("Re2/J (NFA)");
	}

}
