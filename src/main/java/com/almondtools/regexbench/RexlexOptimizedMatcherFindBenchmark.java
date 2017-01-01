package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.rexlex.pattern.OptimizedMatcherBuilder;

@State(Scope.Thread)
public class RexlexOptimizedMatcherFindBenchmark extends MatcherFindBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new RexlexAutomaton("com.almondtools.rexlex Optimized String Match (DFA)", new OptimizedMatcherBuilder());
	}

}
