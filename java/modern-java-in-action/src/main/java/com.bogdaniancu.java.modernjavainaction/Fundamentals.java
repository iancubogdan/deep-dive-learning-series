package com.java.learningSeries.modernjavainaction;

import com.java.learningSeries.modernjavainaction.auxiliaryclasses.Apple;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class Fundamentals {

    public void methodReferences() {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        File[] hiddenFilesRefference = new File(".").listFiles(File::isHidden);
    }

    //-----------------------------------------------------------------------------------------

    public static boolean isGreenApple(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    static void useMethodRefference(List<Apple> inventory) {
        filterApples(inventory, Fundamentals::isGreenApple);
        filterApples(inventory, Fundamentals::isHeavyApple);
    }

    //-----------------------------------------------------------------------------------------

    static void useLambda(List<Apple> inventory) {
        filterApples(inventory, (Apple a) -> Color.GREEN.equals(a.getColor()));
        filterApples(inventory, (Apple a) -> a.getWeight() > 150);
    }
    /**
     * But if such a lambda exceeds a few lines in length (so that its behavior isn’t instantly clear),
     * you should instead use a method reference to a method with a descriptive name instead of using
     * an anonymous lambda. Code clarity should be your guide.
     */

    //-----------------------------------------------------------------------------------------

    static void useStreams(List<Apple> inventory) {
        //sequential processing
        List<Apple> heavyApples =
                inventory.stream().filter((Apple a) -> a.getWeight() > 150) .collect(toList());
        //parallel processing
        List<Apple> heavyApples2 =
                inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150) .collect(toList());
    }

}
