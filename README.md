regexbench
==========
[![Build Status](https://api.travis-ci.org/almondtools/regexbench.svg)](https://travis-ci.org/almondtools/regexbench)

regexbench is a jmh benchmark project comparing known java regular expression packages:
- java.util.regex
- jregex
- dk.brics.automaton
- rexlex
- patternsearchalgorithms

The benchmark yet exposes:
- average time of preparing/compiling a regular expression
- average time of matching/searching for a regular expression

One could easily distinguish DFA-Matchers and NFA-Matchers
- DFA-Matchers (brics, rexlex) need much time on preparing and are faster on matching/searching
- NFA-Matchers (jregex, java.util.regex) are quick in preparing, but slower in matching/searching