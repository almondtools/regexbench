package com.almondtools.regexbench;

public interface Automaton {

	AutomatonType getType();
	void preparePattern(String pattern);
	void prepareText(String text);

	int find();

	String getId();
}
