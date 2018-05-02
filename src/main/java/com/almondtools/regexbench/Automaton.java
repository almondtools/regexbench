package com.almondtools.regexbench;

import java.io.File;
import java.io.IOException;

public interface Automaton {

	AutomatonType getType();
	
	void prepare(String pattern);
	String getPattern();

	String getId();

	int find(String text);
	int find(File file) throws IOException;

}
