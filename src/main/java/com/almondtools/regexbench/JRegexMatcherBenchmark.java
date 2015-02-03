package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;
import jregex.Matcher;
import jregex.Pattern;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JRegexMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "JRegex (NFA)";

	private Pattern pattern;

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
		return ID;
	}

	@Override
	public AutomatonType getType() {
		return NFA;
	}

}
