package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;


public class Re2JAutomaton implements Automaton {

	private String id;
	private Pattern pattern;

	public Re2JAutomaton(String id) {
		this.id = id;
	}
	
	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern);
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