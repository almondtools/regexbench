package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import jregex.Matcher;
import jregex.Pattern;

public class JRegexAutomaton implements Automaton {

	private String id;
	private Pattern pattern;

	public JRegexAutomaton(String id) {
		this.id = id;
	}

	@Override
	public void prepare(String pattern) {
		this.pattern = new Pattern(pattern);
	}

	@Override
	public int find(String text) {
		int result = 0;
		Matcher matcher = pattern.matcher(text);
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
		return NFA;
	}
}