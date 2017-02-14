package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class SCBPGlushkovBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new SCBPGlushkovAutomaton("net.amygdalum.stringsearchalgorithms Glushkov (BPNFA)");
	}

}
