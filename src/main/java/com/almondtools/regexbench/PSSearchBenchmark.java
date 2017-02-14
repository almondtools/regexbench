package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.patternsearchalgorithms.pattern.OptimizationTarget;

@State(Scope.Thread)
public class PSSearchBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new PSAutomaton("Patternsearchalgorithms (DFA)", OptimizationTarget.SEARCH);
	}

}
