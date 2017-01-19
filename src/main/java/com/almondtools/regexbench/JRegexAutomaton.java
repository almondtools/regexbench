package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import jregex.Matcher;
import jregex.Pattern;

public class JRegexAutomaton implements Automaton {

	private String id;
	private Pattern pattern;
	private Matcher matcher;

	public JRegexAutomaton(String id) {
		this.id = id;
	}

	@Override
	public void preparePattern(String pattern) {
		this.pattern = new Pattern(pattern);
	}
	
	@Override
	public void prepareText(String text) {
		matcher = pattern.matcher(text);
	}

	@Override
	public int find() {
		int result = 0;
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