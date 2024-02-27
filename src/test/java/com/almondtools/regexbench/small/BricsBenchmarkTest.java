package com.almondtools.regexbench.small;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BricsBenchmarkTest extends MatcherBenchmarkTest {

    @Override
    public MatcherBenchmark getBenchmark() {
        return new BricsBenchmark();
    }

}
