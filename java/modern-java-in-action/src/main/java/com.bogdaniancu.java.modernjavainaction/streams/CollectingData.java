package com.bogdaniancu.java.modernjavainaction.streams;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Dish;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Trader;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Transaction;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class CollectingData {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    private List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    private List<Dish> specialMenu = asList(
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

    public enum CaloricLevel {
        DIET,
        NORMAL,
        FAT
    }

    public void grouping() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                                    groupingBy(dish -> {
                                        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                        else return CaloricLevel.FAT;
                                    }));

        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                filtering(dish -> dish.getCalories() > 500, toList())));


        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                mapping(Dish::getName, toList())));

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
                                        toSet())));


        //Multilevel grouping

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));

        //collecting data in subgroups

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));


        //ADAPTING THE COLLECTOR RESULT TO A DIFFERENT TYPE
        /**
         * this is safe because the reducing collector will never return an Optional.empty()
         */
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(
                                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                                        Optional::get)
                ));
    }


    /**
     * Partitioning is a special case of grouping: having a predicate called a partitioning func- tion as a
     * classification function. The fact that the partitioning function returns a bool- ean means the resulting
     * grouping Map will have a Boolean as a key type, and therefore, there can be at most two different groups—one
     * for true and one for false
     */
    public void partitioning() {
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));

        List<Dish> vegetarianDishes =
                menu.stream().filter(Dish::isVegetarian).collect(toList());
    }

    //Partitioning numbers into prime and nonprime

    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    public boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                                        .collect(partitioningBy(candidate -> isPrime2(candidate)));
    }

    public Map<Boolean, List<Integer>> partitionPrimes2(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumberCollector());
    }
}
