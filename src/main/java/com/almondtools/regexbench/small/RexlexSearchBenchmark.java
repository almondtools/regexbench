package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.RexlexAutomaton;
import com.almondtools.rexlex.pattern.SearchMatcherBuilder;

@State(Scope.Thread)
public class RexlexSearchBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new RexlexAutomaton("com.almondtools.rexlex Optimized Search (DFA)", new SearchMatcherBuilder());
	}
}
