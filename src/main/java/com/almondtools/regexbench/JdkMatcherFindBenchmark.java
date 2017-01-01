package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JdkMatcherFindBenchmark extends MatcherFindBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new JdkAutomaton("Java.util.regex (NFA)");
	}

}
