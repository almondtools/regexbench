package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.relex.pattern.DefaultMatcherBuilder;
import com.almondtools.relex.pattern.Finder;
import com.almondtools.relex.pattern.Pattern;

@State(Scope.Thread)
public class DefaultMatcherSPM extends SearchPerformanceModule {

	private static final String ID = "DM";

	private Pattern pattern;

	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, new DefaultMatcherBuilder());
	}

	@Override
	public int match(String text) {
		int result = 0;
		Finder matcher = pattern.finder(text);
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
