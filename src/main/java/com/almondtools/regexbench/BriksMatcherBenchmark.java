package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

@State(Scope.Thread)
public class BriksMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "dk.brics.automaton (DFA)";

	private RunAutomaton ra;

	@Override
	public void prepare(String pattern) {
		RegExp r = new RegExp(pattern);
		ra = new RunAutomaton(r.toAutomaton());
	}

	@Override
	public int find(String text) {
		int result = 0;
		AutomatonMatcher matcher = ra.newMatcher(text);
		while (matcher.find()) {
			result++;
		}
		return result;
	}

	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public AutomatonType getType() {
		return DFA;
	}

}
