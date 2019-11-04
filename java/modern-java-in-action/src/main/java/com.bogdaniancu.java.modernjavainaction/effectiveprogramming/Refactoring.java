package com.bogdaniancu.java.modernjavainaction.effectiveprogramming;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.summingInt;

public class Refactoring {

    private List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    /**
     * converting anonymous classes to lambda expressions can be a difficult process in certain situations.1 First,
     * \the meanings of this and super are different for anonymous classes and lambda expressions. Inside an
     * anonymous class, this refers to the anonymous class itself, but inside a lambda, it refers to the enclosing
     * class. Second, anony- mous classes are allowed to shadow variables from the enclosing class. Lambda expressions
     * can’t (they’ll cause a compile error), as shown in the following code:
     */

    public static void demo() {
        int a = 10;
        Runnable r1 = () -> {
           // int a = 2;  // Compile error!
            System.out.println(a);
        };
        Runnable r2 = new Runnable(){
            public void run(){
                int a = 2;
                System.out.println(a);
            }
        };
    }

    /**
     * converting an anonymous class to a lambda expression can make the resulting code ambiguous in the context of
     * overloading. Indeed, the type of anonymous class is explicit at instantiation, but the type of the lambda
     * depends on its context. Here’s an example of how this situation can be problematic. Suppose that you’ve declared
     * a functional interface with the same signature as Runnable, here called Task (as might occur when you need
     * more-meaningful interface names in your domain model):
     */


    interface Task {
        public void execute();
    }

    public static void doSomething(Runnable r){
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }

    public static void demo2() {
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });



        // =. Ambiguoue method call
//        doSomething(() -> System.out.println("Danger danger!!"));

        doSomething((Task)() -> System.out.println("Danger danger!!"));
    }

    //From lambda expressions to method references

    public void demo3() {


        List<Apple> inventory = List.of(new Apple(32), new Apple(43));

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing(Apple::getWeight)); //Reads like the problem statement

        int totalCalories = menu.stream().map(Dish::getCalories)
                                            .reduce(0, (c1, c2) -> c1 + c2);

        int totalCalories2 = menu.stream().collect(summingInt(Dish::getCalories));
    }

    //From imperative data processing to Streams
    public void demo4() {
        List<String> dishNames = new ArrayList();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        // =>
        dishNames = menu.parallelStream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .collect(Collectors.toList());
    }


    //Improving code flexibility TODO page 254
}
