package com.bogdaniancu.java.modernjavainaction.lambdas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lambda101 {

    public void process(Runnable runnable) {
        runnable.run();
    }

    public void useIt() {
        process(() -> System.out.println("Random message!"));
    }

    //Putting lambdas into practice: the execute-around pattern

    public String processFile1() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    //Step 1: Remember behavior parameterization

    //Step 2: Use a functional interface to pass behaviors

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    //Step 3: Execute a behavior!
    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    //Step 4: Pass Lambda
    public void useIt2() throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());

        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }
}
