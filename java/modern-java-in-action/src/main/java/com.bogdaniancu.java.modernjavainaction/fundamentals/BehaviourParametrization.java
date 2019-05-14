package com.bogdaniancu.java.modernjavainaction.fundamentals;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Apple;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Color;

import java.util.ArrayList;
import java.util.List;

public class BehaviourParametrization {

    //First attempt

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //Second attempt: parameterizing the color

    public static List<Apple> filterGreenApples2(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    //Third attempt: filtering with every attribute you can think of


}
