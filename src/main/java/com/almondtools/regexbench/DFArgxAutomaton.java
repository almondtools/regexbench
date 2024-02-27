package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import top.yatt.dfargx.RegexSearcher;

public class DFArgxAutomaton implements Automaton {

    private String id;
    private String pattern;
    private RegexSearcher searcher;

    public DFArgxAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.pattern = pattern;
        this.searcher = new RegexSearcher(pattern);
    }

    @Override
    public int find(String text) {
        int result = 0;
        searcher.search(text);
        while (searcher.hasMoreElements()) {
            searcher.nextElement();
            result++;
        }
        return result;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public int find(File file) throws IOException {
        int result = 0;
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        searcher.search(text);
        while (searcher.hasMoreElements()) {
            searcher.nextElement();
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