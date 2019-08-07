package com.bogdaniancu.java.modernjavainaction.streams;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * Creates the collection operation starting point
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * Accumulates the traversed item, modifying the accumulator in place
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * Modifies the first accumulator combining it with the content of the second one
     * @return the modified first accumulator
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return ((list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));

//        Collectors.toList()
    }
}
