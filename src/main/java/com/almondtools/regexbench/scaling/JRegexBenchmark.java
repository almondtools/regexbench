package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.JRegexAutomaton;

@State(Scope.Thread)
public class JRegexBenchmark extends ScalingPatternMatcherBenchmark {

	@Override
	public String getId() {
		return "JRegex (NFA)";
	}

	@Override
	public Automaton createAutomaton(String id) {
		return new JRegexAutomaton(id);
	}

}
