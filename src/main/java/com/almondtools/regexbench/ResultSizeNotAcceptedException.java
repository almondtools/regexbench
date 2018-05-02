package com.almondtools.regexbench;

import java.util.Arrays;

public class ResultSizeNotAcceptedException extends RuntimeException {

	private String pattern;
	private int[] expected;
	private int[] actual;

	public ResultSizeNotAcceptedException(String pattern, int[] expected, int[] actual) {
		this.pattern = pattern;
		this.expected = expected;
		this.actual = actual;
	}

	public int[] getExpected() {
		return expected;
	}
	
	public int[] getActual() {
		return actual;
	}
	
	@Override
	public String getMessage() {
		return "for pattern \"" + pattern + "\" expected " + Arrays.toString(expected) + ", but was " + Arrays.toString(actual);
	}
}
