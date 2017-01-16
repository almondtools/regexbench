package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class SCBPGlushkovMatcherPrepareBenchmark extends MatcherPrepareBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new SCBPGlushkovMatcherAutomaton("net.amygdalum.stringsearchalgorithms BNGlushkov (BPNFA)");
	}

}
