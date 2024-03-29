regexbench
==========
[![Build Status](https://api.travis-ci.org/almondtools/regexbench.svg?branch=master)](https://travis-ci.org/almondtools/regexbench)

regexbench is a jmh benchmark project comparing known java regular expression packages:
- java.util.regex
- [jregex](https://jregex.sourceforge.net/)
- [dk.brics.automaton](https://github.com/cs-au-dk/dk.brics.automaton)
- [re2j](https://github.com/google/re2j)
- [rexlex](https://github.com/almondtools/rexlex)
- [patternsearchalgorithms](http://patternsearchalgorithms.amygdalum.net/)
- [DFA-Regex](https://github.com/zhztheplayer/DFA-Regex)

regexbench does only benchmark regex libraries that support a `find()` operation (some candidates did only implement the `matches()` operation, which would not be a fair comparison).

The benchmark yet exposes:
- average time of preparing/compiling a regular expression
- average time of matching/searching for a regular expression

One could easily distinguish DFA-Matchers and NFA-Matchers
- DFA-Matchers (brics, rexlex, re2j) need much time on preparing and are faster on matching/searching
- NFA-Matchers (jregex, java.util.regex) are quick in preparing, but slower in matching/searching
