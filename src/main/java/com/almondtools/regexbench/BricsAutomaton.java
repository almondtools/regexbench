package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class BricsAutomaton implements Automaton {

    private String id;
    private String pattern;
    private RunAutomaton ra;

    public BricsAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.pattern = pattern;
        RegExp r = new RegExp(pattern);
        this.ra = new RunAutomaton(r.toAutomaton());
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public int find(String text) {
        int result = 0;
        AutomatonMatcher matcher = ra.newMatcher(text);
        while (matcher.find()) {
            result++;
        }
        return result;
    }

    @Override
    public int find(File file) throws IOException {
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        int result = 0;
        AutomatonMatcher matcher = ra.newMatcher(text);
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