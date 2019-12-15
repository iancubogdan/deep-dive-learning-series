package com.bogdaniancu.java.modernjavainaction.streams;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Streams101 {

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


    public void useIt() {
        List<String> names = menu.stream()
                            .filter(dish -> {
                                System.out.println("filtering: " + dish.getName());
                                return dish.getCalories() > 300;
                            })
                            .map(dish -> {
                                System.out.println("mapping: " + dish.getName());
                                return dish.getName();
                            })
                            .limit(3)
                            .collect(toList());
        System.out.println(names);

    }


    public void filteringOperations() {

        //Filtering with a predicate
        List<Dish> vegetarianMenu = menu.stream()
                                            .filter(Dish::isVegetarian)
                                            .collect(toList());

        //Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


    }

    public void slicingOperations() {

        //using takeWhile
        /**
         * It lets you slice any stream (even an infinite stream as you will learn later) using a predicate.
         * But thankfully, it stops once it has found an element that fails to match. Here’s how you can use it:
         */

        List<Dish> slicedMenu1 = specialMenu.stream()
                                            .takeWhile(dish -> dish.getCalories() < 320)
                                            .collect(toList());

        //using dropWhile
        /**
         * The dropWhile operation is the complement of takeWhile. It throws away the ele- ments at the start where
         * the predicate is false. Once the predicate evaluates to true it stops and returns all the remaining
         * elements, and it even works if there are an infinite number of remaining elements!
         */

        List<Dish> slicedMenu2 = specialMenu.stream()
                                            .dropWhile(dish -> dish.getCalories() < 320)
                                            .collect(toList());


        //Truncating a stream
        List<Dish> dishes = specialMenu.stream()
                                        .filter(dish -> dish.getCalories() > 300)
                                        .limit(3)
                                        .collect(toList());

        //skipping elements
        /**
         * Streams support the skip(n) method to return a stream that discards the first n elements. If the stream
         * has fewer than n elements, an empty stream is returned. Note that limit(n) and skip(n) are complementary!
         * For example, the following code skips the first two dishes that have more than 300 calories and returns
         * the rest.
         */

        List<Dish> dishes2 = menu.stream()
                                        .filter(d -> d.getCalories() > 300)
                                        .skip(2)
                                        .collect(toList());
    }

    public void mappingOperations() {
        /**
         * Streams support the map method, which takes a function as argument. The function is applied to each element,
         * mapping it into a new element (the word mapping is used because it has a meaning similar to transforming but
         * with the nuance of “creating a new version of” rather than “modifying”).
         */
        //Applying a function to each element of a stream
        List<String> dishNames = menu.stream()
                                        .map(Dish::getName)
                                        .collect(toList());

        List<Integer> dishNameLengths = menu.stream()
                                                .map(Dish::getName)
                                                .map(String::length)
                                                .collect(toList());

        //Flattening streams
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");

        //this does not work
        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        //this does
        List<String> uniqueCharacters = words.stream()
                                                .map(word -> word.split(""))
                                                .flatMap(Arrays::stream)
                                                .distinct()
                                                .collect(toList());


        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int []> pairs = numbers1.stream()
                                        .flatMap(i -> numbers2.stream()
                                                                .map(j -> new int[]{i,j}))
                                        .collect(toList());
        System.out.println(pairs);
    }


    public void findingAndMathingOperations() {
        //Checking to see if a predicate matches at least one element
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly");
        }

        //Checking to see if a predicate matches all elements
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);

        //noneMatch = opposite of allMatch
        isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        /**
         * These three operations—anyMatch, allMatch, and noneMatch—make use of what we call short-circuiting
         * Some operations don’t need to process the whole stream to produce a result. For example, say you need
         * \to evaluate a large boolean expression chained with and operators. You need only find out that one
         * expression is false to deduce that the whole expression will return false, no matter how long the
         * expression is; there’s no need to evaluate the entire expression. This is what short-circuiting refers to.
         */

        //finding an element
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(odish -> System.out.println(odish.getName()));


        //finding the first element
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                                                                    .map(n -> n * n)
                                                                    .filter(n -> n % 3 == 0)
                                                                    .findFirst();

        /**
         * When to use findFirst and findAny
         * You may wonder why we have both findFirst and findAny. The answer is parallel- ism. Finding the first
         * element is more constraining in parallel. If you don’t care about which element is returned, use findAny
         * because it’s less constraining when using parallel streams.
         */
    }

    public void reductionOperations() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum1 = numbers.stream().reduce(0, Integer::sum);

        /**
         * There’s also an overloaded variant of reduce that doesn’t take an initial value, but it returns an
         * Optional object. Consider the case when the stream contains no elements. The reduce operation can’t
         * return a sum because it doesn’t have an initial value. This is why the result is wrapped in an Optional
         * object to indicate that the sum may be absent.
         */
        Optional<Integer> sum2 = numbers.stream().reduce((a, b) -> a + b);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);

        //max and min
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);

    }

    public void numericStreams() {
        int calories = menu.stream()
                                .mapToInt(Dish::getCalories)
                                .sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();


        OptionalInt maxCalories = menu.stream()
                                        .mapToInt(Dish::getCalories)
                                        .max();
        int max = maxCalories.orElse(1);

        //numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                            .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        //generating Pythagorean triples
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                                                            .flatMap(a ->
                                                                    IntStream.rangeClosed(a, 100)
                                                                                .filter(b -> Math.sqrt(a * a + b * b) %1 == 0)
                                                                                .mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b)})
                                                                                );

        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double []> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                                                                .flatMap(a -> IntStream.rangeClosed(a, 100)
                                                                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                                                        .filter(t -> t[2] % 1 == 0));

    }

    public void buildingStreams() {
        //Streams from values
        Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        //Streams from nullable
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        //Better ->
        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));

        //Streams from arrays
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //Streams from files
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                                .distinct()
                                .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //infinite streams

        //iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);


        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        /**
         * In Java 9, the iterate method was enhanced with support for a predicate. For exam- ple, you can generate
         * numbers starting at 0 but stop the iteration once the number is greater than 100:
         */

        IntStream.iterate(0, n -> n <100, n -> n + 4)
                    .forEach(System.out::println);

        /**
         * The iterate method takes a predicate as its second argument that tells you when to continue iterating
         * up until. Note that you may think that you can use the filter oper- ation to achieve the same result:
         */
        IntStream.iterate(0, n -> n + 4)
                .filter(n -> n < 100)
                .forEach(System.out::println);

        /**
         * Unfortunately that isn’t the case. In fact, this code wouldn’t terminate! The reason is that there’s
         * no way to know in the filter that the numbers continue to increase, so it keeps on filtering them infinitely!
         * You could solve the problem by using takeWhile, which would short-circuit the stream:
         */
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        //generate

        /**
         * Similarly to the method iterate, the method generate lets you produce an infinite stream of values computed
         * on demand. But generate doesn’t apply successively a function on each new produced value. It takes a lambda
         * of type Supplier<T> to pro- vide new values. Let’s look at an example of how to use it:
         */
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }



    public static void main(String[] args) {
        Streams101 streams101 = new Streams101();

//        streams101.useIt();
//
//        streams101.numericStreams();
//
//        streams101.buildingStreams();

        streams101.mappingOperations();
    }

}
