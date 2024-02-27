package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.NFA;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.google.re2j.Matcher;
import com.google.re2j.Pattern;

public class Re2JAutomaton implements Automaton {

    private String id;
    private Pattern pattern;

    public Re2JAutomaton(String id) {
        this.id = id;
    }

    @Override
    public void prepare(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public String getPattern() {
        return pattern.pattern();
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
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        int result = 0;
        Matcher matcher = pattern.matcher(text);
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
        return NFA;
    }
}