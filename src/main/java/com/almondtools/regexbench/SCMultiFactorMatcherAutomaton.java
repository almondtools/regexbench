package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static java.util.Arrays.asList;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.util.ArrayList;
import java.util.List;

import net.amygdalum.regexparser.AlternativesNode;
import net.amygdalum.regexparser.RegexNode;
import net.amygdalum.regexparser.RegexParser;
import net.amygdalum.regexparser.RegexParserOption;
import net.amygdalum.stringsearchalgorithms.patternsearch.chars.GlushkovPrefixExtender;
import net.amygdalum.stringsearchalgorithms.patternsearch.chars.MultiFactorRE;
import net.amygdalum.stringsearchalgorithms.search.StringFinder;
import net.amygdalum.stringsearchalgorithms.search.chars.AhoCorasick;
import net.amygdalum.util.io.CharProvider;
import net.amygdalum.util.io.StringCharProvider;

public class SCMultiFactorMatcherAutomaton implements Automaton {

	private String id;
	private MultiFactorRE pattern;

	public SCMultiFactorMatcherAutomaton(String id) {
		this.id = id;
	}

	@Override
	public void prepare(String pattern) {
		List<String> patterns = split(pattern);
		this.pattern = new MultiFactorRE(new AhoCorasick.Factory(), new GlushkovPrefixExtender.Factory(), patterns);
	}

	private List<String> split(String pattern) {
		RegexParser parser = new RegexParser(pattern, RegexParserOption.DOT_ALL);
		RegexNode node = parser.parse();
		if (node instanceof AlternativesNode) {
			List<String> patterns = new ArrayList<>();
			for (RegexNode subnode : ((AlternativesNode) node).getSubNodes()) {
				patterns.add(subnode.toString());
			}
			return patterns;
		} else {
			return asList(pattern);
		}
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