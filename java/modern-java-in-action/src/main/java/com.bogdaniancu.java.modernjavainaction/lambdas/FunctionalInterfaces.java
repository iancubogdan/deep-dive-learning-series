package com.bogdaniancu.java.modernjavainaction.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaces {

    //Predicate

    /**
     * use this interface when you need to represent a boolean expression that uses an object of type T
     * @param <T>
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t: list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public void usePredicate() {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        ArrayList<String> listOfStrings = new ArrayList<>();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
    }

    //Consumer

    /**
     * use this interface when you need to access an object of type T and perform some oper- ations on it.
     * @param <T>
     */
    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public void useConsumer() {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
    }

    //Function

    /**
     * use this interface when you need to define a lambda that maps information from an input object to an output
     * @param <T>
     * @param <R>
     */
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public void useFunction() {
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
    }


}
