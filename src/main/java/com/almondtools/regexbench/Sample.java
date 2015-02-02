package com.almondtools.regexbench;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Thread)
public class Sample {
	
	private String sample;
	private String pattern;
	private int expected;
	
	@Setup
	public synchronized void setup(Samples samples) throws IOException {
		String sampleFile = samples.next();
		this.sample = readSample(sampleFile);
		this.pattern = readPattern(sampleFile);
		this.expected = readExpected(sampleFile);
	}

	@TearDown
	public synchronized void tearDown() {
		this.sample = null;
		this.pattern = null;
		this.expected = -1;
	}
	
	public int getExpected() {
		return expected;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public String getSample() {
		return sample;
	}

	public String readSample(String file) throws IOException {
		return readFile(file);
	}

	public String readPattern(String file) throws IOException {
		return readFile(file.replace(".sample", ".pattern"));
	}

	public int readExpected(String file) throws IOException {
		return Integer.parseInt(readFile(file.replace(".sample", ".expected")));
	}

	private String readFile(String file) throws IOException {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(Samples.class.getClassLoader().getResourceAsStream(file)));
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

}
