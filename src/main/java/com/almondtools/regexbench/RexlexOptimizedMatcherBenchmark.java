package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.rexlex.pattern.Finder;
import com.almondtools.rexlex.pattern.OptimizedMatcherBuilder;
import com.almondtools.rexlex.pattern.Pattern;

@State(Scope.Thread)
public class RexlexOptimizedMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "com.almondtools.rexlex Optimized String Match (DFA)";

	private Pattern pattern;

	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, new OptimizedMatcherBuilder());
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
