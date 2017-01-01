package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JdkMatcherPrepareBenchmark extends MatcherPrepareBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new JdkAutomaton("Java.util.regex (NFA)");
	}

}
