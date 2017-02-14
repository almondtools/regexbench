package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JdkBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new JdkAutomaton("Java.util.regex (NFA)");
	}

}
