package com.almondtools.regexbench.scaling;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.SCBPGlushkovAutomaton;

@State(Scope.Thread)
public class SCBPGlushkovBenchmark extends ScalingPatternMatcherBenchmark {

	@Override
	public String getId() {
		return "net.amygdalum.stringsearchalgorithms Glushkov (BPNFA)";
	}

	@Override
	public Automaton createAutomaton(String id) {
		return new SCBPGlushkovAutomaton(id);
	}

}
