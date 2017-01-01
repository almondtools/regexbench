package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static java.util.Arrays.asList;
import static net.amygdalum.stringsearchalgorithms.regex.RegexParserOption.DOT_ALL;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import net.amygdalum.stringsearchalgorithms.io.CharProvider;
import net.amygdalum.stringsearchalgorithms.io.StringCharProvider;
import net.amygdalum.stringsearchalgorithms.patternsearch.chars.GlushkovPrefixExtender;
import net.amygdalum.stringsearchalgorithms.patternsearch.chars.MultiFactorRE;
import net.amygdalum.stringsearchalgorithms.regex.AlternativesNode;
import net.amygdalum.stringsearchalgorithms.regex.RegexNode;
import net.amygdalum.stringsearchalgorithms.regex.RegexParser;
import net.amygdalum.stringsearchalgorithms.search.StringFinder;
import net.amygdalum.stringsearchalgorithms.search.chars.AhoCorasick;

@State(Scope.Thread)
public class SCMultiFactorMatcherBenchmark extends MatcherBenchmark {

	private static final String ID = "com.almondtools.stringsandchars MultiFactorRE (DFA)";

	private MultiFactorRE pattern;

	@Override
	public void prepare(String pattern) {
		List<String> patterns = split(pattern);
		this.pattern = new MultiFactorRE(new AhoCorasick.Factory(), new GlushkovPrefixExtender.Factory(), patterns);
	}

	private List<String> split(String pattern) {
		RegexParser parser = new RegexParser(pattern, DOT_ALL);
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
		return ID;
	}

	@Override
	public AutomatonType getType() {
		return DFA;
	}

}
