package com.almondtools.regexbench.scaling;

import java.io.IOException;

public abstract class ScalingPatternTest {

	protected static ScalingPatternSample createSample(String patternCorpus) {
		try {
			System.out.println("creating sample with corpus " + patternCorpus);
			ScalingPatternSample sample = new ScalingPatternSample();
			sample.setPatternCorpus(patternCorpus);;
			sample.setup();
			return sample;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
