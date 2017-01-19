package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdkAutomaton implements Automaton {
	
	private String id;
	private Pattern pattern;

	public JdkAutomaton(String id) {
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