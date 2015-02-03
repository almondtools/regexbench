package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.relex.pattern.Finder;
import com.almondtools.relex.pattern.OptimizedMatcherBuilder;
import com.almondtools.relex.pattern.Pattern;

@State(Scope.Thread)
public class RelexOptimizedMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "com.almondtools.relex Optimized String Match (DFA)";

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
