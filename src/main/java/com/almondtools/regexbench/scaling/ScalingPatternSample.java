package com.almondtools.regexbench.scaling;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.almondtools.regexbench.GenerateSamples;
import com.almondtools.regexbench.ResultSizeNotAcceptedException;

import net.amygdalum.util.text.CharUtils;

@State(Scope.Benchmark)
public class ScalingPatternSample {

	@Param({ "ecoli", "human-protein", "kjb" })
	private String patternCorpus;

	private String sample;
	private File file;
	private Map<String, Integer> patterns;
	private int[] all;

	public void setPatternCorpus(String patternCorpus) {
		this.patternCorpus = patternCorpus;
	}

	public boolean isValid() {
		return sample != null && patterns != null && !patterns.isEmpty();
	}

	@Override
	public String toString() {
		return "corpus : " + patternCorpus;
	}

	@Setup
	public void setup() throws IOException {
		String sampleKey = "sample-" + patternCorpus;
		String patternKey = "pattern-" + patternCorpus;
		this.sample = GenerateSamples.readSample(sampleKey);
		this.file = GenerateSamples.locateFile(sampleKey);
		this.patterns = GenerateSamples.readPatterns(patternKey);
		this.all = patterns.values().stream()
			.mapToInt(value -> value.intValue())
			.toArray();
	}

	public String getSample() {
		return sample;
	}

	public File getFile() {
		return file;
	}

	public List<String> getPattern() {
		return patterns.keySet().stream()
			.distinct()
			.collect(toList());
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.patterns = null;
		this.all = null;
	}

	public void validate(List<String> pattern, int[] result) {
		if (result == null) {
			throw new ResultSizeNotAcceptedException(pattern.stream()
				.map(str -> toReadableString(str))
				.collect(joining(",", "{", "}")), all, result);
		}
		if (!Arrays.equals(result ,all)) {
			throw new ResultSizeNotAcceptedException(pattern.stream()
				.map(str -> toReadableString(str))
				.collect(joining(",", "{", "}")), all, result);
		}
	}

	private String toReadableString(String str) {
		return str.chars()
			.mapToObj(c -> CharUtils.charToString((char) c))
			.collect(joining());
	}

}
