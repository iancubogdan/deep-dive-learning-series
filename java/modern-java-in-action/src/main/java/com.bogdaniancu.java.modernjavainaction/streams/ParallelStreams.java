package com.bogdaniancu.java.modernjavainaction.streams;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {


    public long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                        .limit(n)
                        .reduce(0L, Long::sum);
    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public long forkJoinSum(long n) {
        long [] numbers = LongStream.range(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
