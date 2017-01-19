package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;


public class Re2JAutomaton implements Automaton {

	private String id;
	private Pattern pattern;
	private Matcher matcher;

	public Re2JAutomaton(String id) {
		this.id = id;
	}
	
	@Override
	public void preparePattern(String pattern) {
		this.pattern = Pattern.compile(pattern);
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
		return DFA;
	}
}