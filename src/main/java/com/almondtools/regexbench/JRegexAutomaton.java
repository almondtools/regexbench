package com.almondtools.regexbench;

import static com.almondtools.regexbench.SearchType.FIRST;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jregex.Matcher;
import jregex.Pattern;

public class JRegexAutomaton implements Automaton {

    private String id;
    private Pattern pattern;

    public JRegexAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.pattern = new Pattern(pattern);
    }

    @Override
    public String getPattern() {
        return pattern.toString();
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
    public int find(File file) throws IOException {
        int result = 0;
        Matcher matcher = pattern.matcher(Files.newBufferedReader(file.toPath()), (int) Files.size(file.toPath()));
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
    public SearchType getType() {
        return FIRST;
    }
}