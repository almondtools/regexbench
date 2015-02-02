package com.almondtools.regexbench;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.relex.pattern.Finder;
import com.almondtools.relex.pattern.Pattern;
import com.almondtools.relex.pattern.SearchMatcherBuilder;

@State(Scope.Thread)
public class SearchMatcherSPM extends SearchPerformanceModule {

	private static final String ID = "SM";

	private Pattern pattern;

	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, new SearchMatcherBuilder());
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
