package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.BricsAutomaton;

@State(Scope.Thread)
public class BricsBenchmark extends ScalingPatternMatcherBenchmark {

	@Override
	public String getId() {
		return "dk.brics.automaton (DFA)";
	}
	
	@Override
	public Automaton createAutomaton(String id) {
		return new BricsAutomaton(id);
	}

}
