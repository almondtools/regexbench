package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.PSAutomaton;

import net.amygdalum.patternsearchalgorithms.pattern.OptimizationTarget;

@State(Scope.Thread)
public class PSMatchBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new PSAutomaton("Patternsearchalgorithms (DFA)", OptimizationTarget.MATCH);
	}

}
