package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.rexlex.pattern.DefaultMatcherBuilder;

@State(Scope.Thread)
public class RexlexDefaultMatcherPrepareBenchmark extends MatcherPrepareBenchmark {

	@Override
	public Automaton getAutomaton() {
		return new RexlexAutomaton("com.almondtools.rexlex Default (DFA)", new DefaultMatcherBuilder());
	}

}
