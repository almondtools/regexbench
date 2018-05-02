package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.SCMultiFactorAutomaton;

@State(Scope.Thread)
public class SCMultiFactorBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new SCMultiFactorAutomaton("net.amygdalum.stringsearchalgorithms MultiFactorRE (DFA)");
	}

}
