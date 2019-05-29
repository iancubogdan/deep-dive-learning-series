package com.bogdaniancu.java.modernjavainaction.fundamentals.behaviourparameterization;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Color;

import java.util.ArrayList;
import java.util.List;

public class BehaviourParameterization {

    //First attempt

    public List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //Second attempt: parameterizing the color

    public List<Apple> filterGreenApples2(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    //Third attempt: filtering with every attribute you can think of
    public List<Apple> filterApplesByEverything(List<Apple> inventory, Color color,
                                                       int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ( (flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    public void useIt() {
        List<Apple> inventory = new ArrayList<>();
        List<Apple> greenApples = filterApplesByEverything(inventory, Color.GREEN, 0, true);
        List<Apple> heavyApples = filterApplesByEverything(inventory, null, 150, false);
    }
    //^^ that's bad

    //Behaviour parameterization

    /**
     * predicate (a function that returns a boolean)
     */
    public interface ApplePredicate {
        boolean test (Apple apple);
    }

    public class AppleHeavyWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }
    public class AppleGreenColorPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.GREEN.equals(apple.getColor());
        }
    }

    //Fourth attempt: filtering by abstract criteria

    public List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public class AppleRedAndHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor())
                    && apple.getWeight() > 150;
        }
    }

    //Fifth attempt: using an anonymous class
    public void annonymousClasses() {
        List<Apple> inventory = new ArrayList<>();

        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });
    }

    //Sixth attempt: using a lambda expression
    public void useLambdas() {
        List<Apple> inventory = new ArrayList<>();

        List<Apple> redApples = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
    }

    //Seventh attempt: abstracting over List type

    public interface MyPredicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, MyPredicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public void useIt2() {
        List<Apple> inventory = new ArrayList<>();
        List<Apple> redApples = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        List<Integer> numbers = new ArrayList<>();
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
    }

}
