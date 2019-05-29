package com.bogdaniancu.java.modernjavainaction.fundamentals.behaviourparameterization;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RealWorldExamples {

    //Sorting with a Comparator
    public interface Comparator<T> {
        int compare(T o1, T o2);
    }

    void useIt() {
        List<Apple> inventory = new ArrayList<>();

        //Using anonymous class
        inventory.sort(new java.util.Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        //Using lambda
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    }


    //Executing a block of code with Runnable
    public interface Runnable {
        void run();
    }

    void useIt2() {
        //Using anonymous class
        Thread t = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });

        //Using lambda
        Thread t2 = new Thread(() -> System.out.println("Hello World"));
    }


    //Returning a result using Callable
    public interface Callable<V> {
        V call();
    }

    void useIt3() {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(new java.util.concurrent.Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        Future<String> threadName2 = executorService.submit(() -> Thread.currentThread().getName());

    }
}
