package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static net.amygdalum.patternsearchalgorithms.pattern.SearchMode.LONGEST_NON_OVERLAPPING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.amygdalum.patternsearchalgorithms.pattern.Matcher;
import net.amygdalum.patternsearchalgorithms.pattern.OptimizationTarget;
import net.amygdalum.patternsearchalgorithms.pattern.Pattern;
import net.amygdalum.util.io.ReaderCharProvider;

public class PSAutomaton implements Automaton {

    private String id;
    private Pattern pattern;
    private OptimizationTarget mode;

    public PSAutomaton(String id, OptimizationTarget mode) {
        this.id = id;
        this.mode = mode;
    }

    @Override
    public void prepare(String pattern) {
        this.pattern = Pattern.compile(pattern, mode, LONGEST_NON_OVERLAPPING);
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
        int result = 0;
        Matcher matcher = pattern.matcher(new ReaderCharProvider(Files.newBufferedReader(file.toPath()), 0, 4096, 4));
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