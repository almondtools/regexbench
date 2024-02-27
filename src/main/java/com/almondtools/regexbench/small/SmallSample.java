package com.almondtools.regexbench.small;

import static com.almondtools.regexbench.SearchType.LONGEST;
import static com.almondtools.regexbench.SearchType.FIRST;

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

import com.almondtools.regexbench.SearchType;

@State(Scope.Benchmark)
public class SmallSample {

    @Param({"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10", "test11", "test12", "test12"})
    private String name;
    private String sample;
    private String pattern;
    private Map<SearchType, Integer> expected;

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

    public int getExpected(SearchType type) {
        return expected.get(type);
    }

    public String getPattern() {
        return pattern;
    }

    public String getSample() {
        return sample;
    }

    public void validate(int result, SearchType type) {
        if (result != getExpected(type)) {
            throw new IllegalStateException("expected " + getExpected(type) + " matches, but found " + result);
        }
    }

    private EnumMap<SearchType, Integer> readExpected(String file) throws IOException {
        String[] expectedByType = readFile(file).split("/");
        EnumMap<SearchType, Integer> enumMap = new EnumMap<SearchType, Integer>(SearchType.class);
        try {
            if (expectedByType.length == 1) {
                enumMap.put(FIRST, Integer.parseInt(expectedByType[0]));
                enumMap.put(LONGEST, Integer.parseInt(expectedByType[0]));
            } else {
                enumMap.put(FIRST, Integer.parseInt(expectedByType[0]));
                enumMap.put(LONGEST, Integer.parseInt(expectedByType[1]));
            }
        } catch (NullPointerException e) {
            enumMap.put(FIRST, 0);
            enumMap.put(LONGEST, 0);
        } catch (NumberFormatException e) {
            enumMap.put(FIRST, 0);
            enumMap.put(LONGEST, 0);
        }
        return enumMap;
    }

    public boolean rejects(SearchType type) {
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
