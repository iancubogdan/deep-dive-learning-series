package com.bogdaniancu.java.modernjavainaction.lambdas;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UseLambdas {

    private List<Apple> inventory = new ArrayList<>();


    //Step 1: Pass code

    public class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public void useIt1() {
        inventory.sort(new AppleComparator());
    }


    //Step 2: Use an anonymous class

    public void useIt2() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }


    //Step 3: Use lambda expression

    public void useIt3() {
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        Comparator<Apple> c = Comparator.comparing(a -> a.getWeight());
        inventory.sort(Comparator.comparing(a -> a.getWeight()));

    }


    //Step 4: Use method references

    public void useIt4() {
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

}
