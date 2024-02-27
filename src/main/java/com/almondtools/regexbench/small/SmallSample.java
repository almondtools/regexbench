package com.almondtools.regexbench.small;

import static com.almondtools.regexbench.AutomatonType.DFA;
import static com.almondtools.regexbench.AutomatonType.NFA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.regexbench.AutomatonType;

@State(Scope.Benchmark)
public class SmallSample {

    @Param({"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10", "test11", "test12", "test12"})
    private String name;
    private String sample;
    private String pattern;
    private Map<AutomatonType, Integer> expected;

    @Setup
    public void setup() throws IOException {
        this.sample = readFile("smallsamples/" + name + ".sample");
        this.pattern = readFile("smallsamples/" + name + ".pattern");
        this.expected = readExpected("smallsamples/" + name + ".expected");
    }

    @TearDown
    public synchronized void tearDown() {
        this.sample = null;
        this.pattern = null;
        this.expected = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpected(AutomatonType type) {
        return expected.get(type);
    }

    public String getPattern() {
        return pattern;
    }

    public String getSample() {
        return sample;
    }

    public void validate(int result, AutomatonType type) {
        if (result != getExpected(type)) {
            throw new IllegalStateException("expected " + getExpected(type) + " matches, but found " + result);
        }
    }

    private EnumMap<AutomatonType, Integer> readExpected(String file) throws IOException {
        String[] expectedByType = readFile(file).split("/");
        EnumMap<AutomatonType, Integer> enumMap = new EnumMap<AutomatonType, Integer>(AutomatonType.class);
        try {
            if (expectedByType.length == 1) {
                enumMap.put(NFA, Integer.parseInt(expectedByType[0]));
                enumMap.put(DFA, Integer.parseInt(expectedByType[0]));
            } else {
                enumMap.put(NFA, Integer.parseInt(expectedByType[0]));
                enumMap.put(DFA, Integer.parseInt(expectedByType[1]));
            }
        } catch (NullPointerException e) {
            enumMap.put(NFA, 0);
            enumMap.put(DFA, 0);
        } catch (NumberFormatException e) {
            enumMap.put(NFA, 0);
            enumMap.put(DFA, 0);
        }
        return enumMap;
    }

    public boolean rejects(AutomatonType type) {
        return getExpected(type) < 0;
    }

    private String readFile(String file) throws IOException {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(SmallSample.class.getClassLoader().getResourceAsStream(file)));
            StringBuilder buffer = new StringBuilder();
            char[] chars = new char[4096];
            while (true) {
                int count = r.read(chars);
                if (count < 0) {
                    break;
                } else {
                    buffer.append(chars, 0, count);
                }
            }
            return buffer.toString();
        } catch (RuntimeException e) {
            System.out.println("file:" + file);
            throw e;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
