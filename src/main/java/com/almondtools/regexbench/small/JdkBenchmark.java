package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.JdkAutomaton;

@State(Scope.Thread)
public class JdkBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new JdkAutomaton("Java.util.regex (NFA)");
	}

}
