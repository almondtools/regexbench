package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdkAutomaton implements Automaton {
	
	private String id;
	private Pattern pattern;
	private Matcher matcher;

	public JdkAutomaton(String id) {
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
		return NFA;
	}
}