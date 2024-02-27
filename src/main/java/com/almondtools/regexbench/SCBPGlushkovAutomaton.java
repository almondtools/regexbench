package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.LONGEST_MATCH;
import static net.amygdalum.stringsearchalgorithms.search.MatchOption.NON_OVERLAP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.amygdalum.stringsearchalgorithms.patternsearch.chars.BPGlushkov;
import net.amygdalum.stringsearchalgorithms.search.StringFinder;
import net.amygdalum.util.io.CharProvider;
import net.amygdalum.util.io.ReaderCharProvider;
import net.amygdalum.util.io.StringCharProvider;

public class SCBPGlushkovAutomaton implements Automaton {

    private String id;
    private String rawPattern;
    private BPGlushkov pattern;

    public SCBPGlushkovAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.rawPattern = pattern;
        this.pattern = new BPGlushkov(pattern);
    }

    @Override
    public String getPattern() {
        return rawPattern;
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
    public AutomatonType getType() {
        return DFA;
    }
}