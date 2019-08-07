package com.bogdaniancu.java.modernjavainaction.streams;

import java.util.concurrent.RecursiveTask;

/**
 * The fork/join framework was designed to recursively split a parallelizable task into smaller
 * tasks and then combine the results of each subtask to produce the overall result. It’s an
 * implementation of the ExecutorService interface, which distributes those subtasks to worker
 * threads in a thread pool, called ForkJoinPool. Let’s start by exploring how to define a task
 * and subtasks.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    //The size threshold for splitting into subtasks
    public static final long THRESHOLD = 10_000;

    //Public constructor to create the main task
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }




}
