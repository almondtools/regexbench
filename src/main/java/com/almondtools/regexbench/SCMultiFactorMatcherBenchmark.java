package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static com.almondtools.stringsandchars.regex.RegexParserOption.DOT_ALL;
import static com.almondtools.stringsandchars.search.MatchOption.LONGEST_MATCH;
import static com.almondtools.stringsandchars.search.MatchOption.NON_OVERLAP;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.almondtools.stringsandchars.io.CharProvider;
import com.almondtools.stringsandchars.io.StringCharProvider;
import com.almondtools.stringsandchars.patternsearch.GlushkovPrefixExtender;
import com.almondtools.stringsandchars.patternsearch.MultiFactorRE;
import com.almondtools.stringsandchars.regex.AlternativesNode;
import com.almondtools.stringsandchars.regex.RegexNode;
import com.almondtools.stringsandchars.regex.RegexParser;
import com.almondtools.stringsandchars.search.AhoCorasick;
import com.almondtools.stringsandchars.search.StringFinder;

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
