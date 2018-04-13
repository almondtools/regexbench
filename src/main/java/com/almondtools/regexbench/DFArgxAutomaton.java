package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import top.yatt.dfargx.RegexSearcher;

public class DFArgxAutomaton implements Automaton {
	
	private String id;
	private RegexSearcher searcher;

	public DFArgxAutomaton(String id) {
		this.id = id;
	}

	@Override
	public void prepare(String pattern) {
		searcher = new RegexSearcher(pattern);
	}

	@Override
	public int find(String text) {
		int result = 0;
		searcher.search(text);
		while (searcher.hasMoreElements()) {
			System.out.println(searcher.nextElement());
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