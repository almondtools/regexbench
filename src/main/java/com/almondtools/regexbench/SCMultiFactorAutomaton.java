package com.almondtools.regexbench;

import static com.almondtools.regexbench.SearchType.LONGEST;
import static java.util.Arrays.asList;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import net.amygdalum.util.io.ReaderCharProvider;
import net.amygdalum.util.io.StringCharProvider;

public class SCMultiFactorAutomaton implements Automaton {

    private String id;
    private String rawPattern;
    private MultiFactorRE pattern;

    public SCMultiFactorAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.rawPattern = pattern;
        List<String> patterns = split(pattern);
        this.pattern = new MultiFactorRE(new AhoCorasick.Factory(), new GlushkovPrefixExtender.Factory(), 5, patterns);
    }

    @Override
    public String getPattern() {
        return rawPattern;
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
    public int find(File file) throws IOException {
        CharProvider provider = new ReaderCharProvider(Files.newBufferedReader(file.toPath()), 0, 8192, 32);
        StringFinder finder = pattern.createFinder(provider, LONGEST_MATCH, NON_OVERLAP);
        return finder.findAll().size();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SearchType getType() {
        return LONGEST;
    }
}