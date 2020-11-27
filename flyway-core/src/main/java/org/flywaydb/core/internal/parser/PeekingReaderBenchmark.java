package org.flywaydb.core.internal.parser;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

@CompilerControl(CompilerControl.Mode.DONT_INLINE)
public class PeekingReaderBenchmark {

    public static final String STRING = "Thestringfortestandforfun:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\nThe\nstring\nfor\ntest\nand\nfor\nfun\n:)\n";
    public static final int NUM_CHARS = STRING.length() - 2;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(PeekingReaderBenchmark.class.getSimpleName())
                .addProfiler(GCProfiler.class)
                .warmupIterations(10)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(10)
                .measurementTime(TimeValue.seconds(10))
                .forks(5)
                .shouldFailOnError(true)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public String old_peek_with_support_multiple_lines() throws IOException {
        PeekingReader reader = new PeekingReader(new StringReader(STRING), true);
        return reader.peek(NUM_CHARS, true);
    }

    @Benchmark
    public String new_peek_with_support_multiple_lines() throws IOException {
        PeekingReader reader = new PeekingReader(new StringReader(STRING), true);
        return reader.peekWithChars(NUM_CHARS, true);
    }

    @Benchmark
    public String old_peek_without_support_multiple_lines() throws IOException {
        PeekingReader reader = new PeekingReader(new StringReader(STRING), false);
        return reader.peek(NUM_CHARS, true);
    }

    @Benchmark
    public String new_peek_without_support_multiple_lines() throws IOException {
        PeekingReader reader = new PeekingReader(new StringReader(STRING), false);
        return reader.peekWithChars(NUM_CHARS, true);
    }
}
