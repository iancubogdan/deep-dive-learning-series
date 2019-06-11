package com.bogdaniancu.java.modernjavainaction.lambdas;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ComposingLambdas {
    private List<Apple> inventory = new ArrayList<>();

    //Composing Comparators

    public void useIt1() {
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
    }


    //Composing Predicates

    public void useIt2() {
        Predicate<Apple> redApple = a -> a.getColor().equals(Color.RED);

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);

        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                                                            .or(apple -> apple.getColor().equals(Color.GREEN));
    }


    //Composing Functions

    public void useIt3() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 1;

        Function<Integer, Integer> h = f.andThen(g); // g(f(x))
        int result = h.apply(1); // returns 4

        Function<Integer, Integer> t = f.compose(g); // f(g(x))
        int result2 = h.apply(1); // returns 3

    }
}
