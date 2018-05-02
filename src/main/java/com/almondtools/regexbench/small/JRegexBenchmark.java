package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.JRegexAutomaton;

@State(Scope.Thread)
public class JRegexBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new JRegexAutomaton("JRegex (NFA)");
	}

}
