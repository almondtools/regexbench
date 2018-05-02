package com.almondtools.regexbench.small;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.regexbench.Automaton;
import com.almondtools.regexbench.RexlexAutomaton;
import com.almondtools.rexlex.pattern.DefaultMatcherBuilder;

@State(Scope.Thread)
public class RexlexDefaultBenchmark extends MatcherBenchmark {

	@Override
	public Automaton createAutomaton() {
		return new RexlexAutomaton("com.almondtools.rexlex Default (DFA)", new DefaultMatcherBuilder());
	}

}
