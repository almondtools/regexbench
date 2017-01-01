package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.rexlex.pattern.SearchMatcherBuilder;

@State(Scope.Thread)
public class RexlexSearchMatcherPrepareBenchmark extends MatcherPrepareBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new RexlexAutomaton("com.almondtools.rexlex Optimized Search (DFA)", new SearchMatcherBuilder());
	}
}
