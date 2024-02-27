package com.almondtools.regexbench.scaling;

import static java.util.Collections.emptyList;
import static org.junit.Assume.assumeFalse;

import java.io.IOException;
import java.util.List;

public abstract class ScalingPatternTest {

    protected List<String> excluded() {
        return emptyList();
    }

    protected ScalingPatternSample createSample(String patternCorpus) {
        try {
            assumeFalse(excluded().contains(patternCorpus));
            System.out.println("creating sample with corpus " + patternCorpus);
            ScalingPatternSample sample = new ScalingPatternSample();
            sample.setPatternCorpus(patternCorpus);
            ;
            sample.setup();
            return sample;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}
