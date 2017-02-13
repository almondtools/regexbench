package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import net.amygdalum.patternsearchalgorithms.pattern.Matcher;
import net.amygdalum.patternsearchalgorithms.pattern.OptimizationTarget;
import net.amygdalum.patternsearchalgorithms.pattern.Pattern;



public class PSAutomaton implements Automaton {

	private String id;
	private Pattern pattern;

	public PSAutomaton(String id) {
		this.id = id;
	}
	
	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, OptimizationTarget.MATCH);
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
		return DFA;
	}
}