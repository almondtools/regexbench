package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class BricsAutomaton implements Automaton {
	
	private String id;
	private RunAutomaton ra;

	public BricsAutomaton(String id) {
		this.id = id;
	}

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
		return id;
	}

	@Override
	public AutomatonType getType() {
		return DFA;
	}
}