package com.almondtools.regexbench;

import static com.almondtools.regexbench.AutomatonType.DFA;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.almondtools.rexlex.pattern.Finder;
import com.almondtools.rexlex.pattern.MatcherBuilder;
import com.almondtools.rexlex.pattern.Pattern;

public class RexlexAutomaton implements Automaton {

	private String id;
	private MatcherBuilder builder;
	private Pattern pattern;

	public RexlexAutomaton(String id, MatcherBuilder builder) {
		this.id = id;
		this.builder = builder;
	}
	
	@Override
	public void prepare(String pattern) {
		this.pattern = Pattern.compile(pattern, builder);
	}

	@Override
	public String getPattern() {
		return pattern.pattern();
	}

	@Override
	public int find(String text) {
		int result = 0;
		Finder matcher = pattern.finder(text);
		while (matcher.find()) {
			result++;
		}
		return result;
	}

	@Override
	public int find(File file) throws IOException {
		String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		int result = 0;
		Finder matcher = pattern.finder(text);
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