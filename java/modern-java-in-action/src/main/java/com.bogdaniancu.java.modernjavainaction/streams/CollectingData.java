package com.bogdaniancu.java.modernjavainaction.streams;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Dish;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Trader;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Transaction;

import java.util.*;

import static java.util.stream.Collectors.*;

public class CollectingData {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

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

    private List<Dish> specialMenu = Arrays.asList(
            new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER));

    public void reducingAndSummarizing() {
        long howManyDishes = menu.stream().collect(counting());

        //or
        long howManyDishes2 = menu.stream().count();

        //max and min
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloricDish = menu.stream().collect(maxBy(dishCaloriesComparator));

        //summarization
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));


        //joining strings

        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
//        /**
//         * if Dish class had a toString method returning the dish’s name, you’d obtain the same result without needing
//         * to map over the original stream with a function extracting the name from each dish:
//         */
//        String shortMenu2 = menu.stream().collect(joining());

        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));

        //Generalized summarization with reduction


    }

    public void generalizedSummarizationWithReduction() {
        /**
         *
         All the collectors we’ve discussed so far are, in reality, only convenient specializations of a reduction
         process that can be defined using the reducing factory method. The Collectors.reducing factory method is a
         generalization of all of them
         */
        int totalCalories = menu.stream()
                            .collect(reducing(0, Dish::getCalories, (i, j) -> i +j));
        int totalCalories2 = menu.stream()
                            .collect(reducing(0, Dish::getCalories, Integer::sum));

        Optional<Dish> mostCaloricDish = menu.stream()
                            .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    public void grouping() {

    }
}
