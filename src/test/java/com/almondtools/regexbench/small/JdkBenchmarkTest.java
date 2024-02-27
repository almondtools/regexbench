package com.almondtools.regexbench.small;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class JdkBenchmarkTest extends MatcherBenchmarkTest {

    @Override
    public MatcherBenchmark getBenchmark() {
        return new JdkBenchmark();
    }

}
