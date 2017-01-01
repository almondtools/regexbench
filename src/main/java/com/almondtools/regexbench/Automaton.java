package com.almondtools.regexbench;

public interface Automaton {

	AutomatonType getType();
	void prepare(String pattern);

	int find(String text);

	String getId();
}
