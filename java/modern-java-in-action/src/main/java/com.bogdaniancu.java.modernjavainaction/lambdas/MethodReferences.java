package com.bogdaniancu.java.modernjavainaction.lambdas;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Fruit;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Orange;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class MethodReferences {

    /**
     * There are three main kinds of method references:
     * 1 A method reference to a static method (for example, the method parseInt of Integer, written Integer::parseInt)
     * 2 A method reference to an instance method of an arbitrary type (for example, the method length of a String, written String::length)
     * 3 A method reference to an instance method of an existing object or expression (for example, suppose you have a local variable
     * expensiveTransaction that holds an object of type Transaction, which supports an instance method getValue; you can write
     * expensiveTransaction::getValue)
     */

    public void useIt() {
        /**
         * This lambda expression forwards its argument to the static method parseInt of Integer.
         * This method takes a String to parse and returns an int. As a result, the lambda can be rewritten using recipe
         * b from figure 3.5 (lambda expressions calling a static method) as follows:
         */
        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        ToIntFunction<String> stringToIntRef = Integer::parseInt;


        /**
         * This lambda uses its first argument to call the method contains on it. Because the first argument is of type List, you can use
         * recipe c from fig- ure 3.5 as follows:
         * This is because the target type describes a function descriptor (List<String>, String) -> boolean, and List::contains can be
         * unpacked to that func- tion descriptor.
         */

        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> containsRef = List::contains;


        /**
         * This expression-style lambda invokes a private helper method. You can use recipe d from figure 3.5 as follows:
         */
        Predicate<String> startsWithNumber = (String s) -> this.startsWithNumber(s);
        Predicate<String> startsWithNumberRef  = this::startsWithNumber;

    }

    private boolean startsWithNumber(String s) {
        //we don t care about this
        return false;
    }

    public void useIt2() {
        List<String> strings = Arrays.asList("a", "b", "A", "B");
        strings.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        //using method reference
        strings.sort(String::compareToIgnoreCase);
    }


    //Constructor references
    public void useIt3() {
        Supplier<Apple> c0 = () -> new Apple();
        Supplier<Apple> c1 = Apple::new;

        Apple a1 = c1.get();

        Function<Integer, Apple> c2 = weight -> new Apple(weight);
        Function<Integer, Apple> c3 = Apple::new;

        Apple a2 = c3.apply(110);
    }

    public void useIt4() {
        Map<String, Function<Integer, Fruit>> map = new HashMap<>();
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    private Fruit giveMeFruit(Map<String, Function<Integer, Fruit>> map, String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }


}
