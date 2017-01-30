package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import net.amygdalum.util.io.CharProvider;
import net.amygdalum.util.io.StringCharProvider;
import net.amygdalum.stringsearchalgorithms.patternsearch.chars.BPGlushkov;
import net.amygdalum.stringsearchalgorithms.search.StringFinder;

public class SCBPGlushkovMatcherAutomaton implements Automaton {

	private String id;
	private BPGlushkov pattern;

	public SCBPGlushkovMatcherAutomaton(String id) {
		this.id = id;
	}

	@Override
	public void prepare(String pattern) {
		this.pattern = new BPGlushkov(pattern);
	}

	@Override
	public int find(String text) {
		CharProvider provider = new StringCharProvider(text, 0);
		StringFinder finder = pattern.createFinder(provider, LONGEST_MATCH, NON_OVERLAP);
		return finder.findAll().size();
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