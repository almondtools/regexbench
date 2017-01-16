package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class SCBPGlushkovMatcherFindBenchmark extends MatcherFindBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new SCBPGlushkovMatcherAutomaton("net.amygdalum.stringsearchalgorithms Glushkov (BPNFA)");
	}

}
