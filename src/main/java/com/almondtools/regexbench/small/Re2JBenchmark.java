package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.Re2JAutomaton;

@State(Scope.Thread)
public class Re2JBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new Re2JAutomaton("Re2/J (NFA)");
	}

}
