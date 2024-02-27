package com.almondtools.regexbench;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateSamples {
    private static final Pattern PATTERN_LINE = Pattern.compile(":(\\d+)$");

    private static Map<String, File> files = new LinkedHashMap<>();

    private GenerateSamples() {
    }

    public static File locateFile(String key) {
        File file = files.computeIfAbsent(key, k -> createTempFile(k));
        file.deleteOnExit();
        return file;
    }

    private static File createTempFile(String key) {
        try {
            String file = sampleFile(key);
            InputStream resourceAsStream = GenerateSamples.class.getClassLoader().getResourceAsStream(file);
            Path tempFile = Files.createTempFile(Paths.get(file).getFileName().toString(), "");
            Files.copy(resourceAsStream, tempFile, REPLACE_EXISTING);
            return tempFile.toFile();
        } catch (IOException e) {
            return null;
        }
    }

    public static String readSample(String key) throws IOException {
        try (BufferedReader reader = open(sampleFile(key))) {
            StringBuilder buffer = new StringBuilder();
            char[] chars = new char[8192];
            int n = 0;
            while ((n = reader.read(chars)) > -1) {
                buffer.append(chars, 0, n);
            }
            return buffer.toString();
        }
    }

    public static Map<String, Integer> readPatterns(String key) throws IOException {
        try (BufferedReader reader = open(resultFile(key))) {
            return reader.lines()
                .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                .map(line -> unescape(line))
                .map(line -> splitPattern(line))
                .collect(toMap(value -> value[0], value -> Integer.parseInt(value[1]), (v1, v2) -> Math.max(v1, v2), LinkedHashMap::new));
        }

    }

    public static BufferedReader open(String fileName) throws IOException {
        System.out.println(fileName);
        return new BufferedReader(new InputStreamReader(GenerateSamples.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
    }

    private static String sampleFile(String key) {
        int profile = key.indexOf(':');
        if (profile > 0) {
            key = key.substring(0, profile);
        }
        return key + ".sample";
    }

    private static String resultFile(String key) {
        return key.replace(':', '-') + ".result";
    }

    private static String[] splitPattern(String line) {
        Matcher m = PATTERN_LINE.matcher(line);
        if (m.find()) {
            int pos = m.start();
            return new String[] {line.substring(0, pos), m.group(1)};
        } else {
            return new String[] {line, "0"};
        }
    }

    private static String unescape(String str) {
        StringBuilder buffer = new StringBuilder();
        boolean escaped = false;
        for (char c : str.toCharArray()) {
            if (escaped) {
                if (c == 'r') {
                    buffer.append('\r');
                } else if (c == 'n') {
                    buffer.append('\n');
                } else {
                    buffer.append(c);
                }
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else {
                buffer.append(c);
            }

        }
        return buffer.toString();
    }

}
