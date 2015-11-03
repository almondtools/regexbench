package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.rexlex.pattern.Finder;
import com.almondtools.rexlex.pattern.Pattern;
import com.almondtools.rexlex.pattern.SearchMatcherBuilder;

@State(Scope.Thread)
public class RexlexSearchMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "com.almondtools.rexlex Optimized Search (DFA)";

	private Pattern pattern;

	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, new SearchMatcherBuilder());
	}

	@Override
	public int find(String text) {
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

	@Override
	public AutomatonType getType() {
		return DFA;
	}

}