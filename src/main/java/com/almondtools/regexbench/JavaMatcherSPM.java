package com.almondtools.regexbench;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JavaMatcherSPM extends SearchPerformanceModule {

	private static final String ID = "JM";

	private Pattern pattern;

	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	@Override
	public int match(String text) {
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

}
