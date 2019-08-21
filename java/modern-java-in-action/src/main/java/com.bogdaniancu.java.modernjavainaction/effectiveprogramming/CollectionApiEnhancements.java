package com.bogdaniancu.java.modernjavainaction.effectiveprogramming;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;


public class CollectionApiEnhancements {

    //Collection factories
    public void collectionFactories() {
        List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);
        //Produces an immutable list

        Set<String> friendsSet = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friendsSet);
        //Produces an immutable set

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends);

        Map<String, Integer> ageOfFriends2 = Map.ofEntries(entry("Raphael", 30),
                                                        entry("Olivia", 25),
                                                        entry("Thibaut", 26));
        System.out.println(ageOfFriends2);

    }

    //workingWithListAndSet

    public void removeIf(List<Transaction> transactions) {

        for (Transaction transaction : transactions) {
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                transactions.remove(transaction);
            }
        }
        // this code translates to this :
        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                transactions.remove(transaction);
            }
        }
        //Problem we are iterating and modifying the collection through two separate objects

        //Solution:
        for (Iterator<Transaction> iterator = transactions.iterator();
             iterator.hasNext(); ) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                iterator.remove();
            }
        }

        //this is equal to:
        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
    }

    public void replaceAll(List<String> referenceCodes) {
        referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //this results in a new list, if you want to update the existing collection:
        for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }

        //=>
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
    }

    //working with Map

    public void forEach() {
        Map<String, Integer> ageOfFriends = Map.ofEntries(entry("Raphael", 30),
                entry("Olivia", 25),
                entry("Thibaut", 26));

        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        //=>

        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));
    }

    public static void sorting() {

        Map<String, String> favouriteMovies
                = Map.ofEntries(entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"),
                entry("Olivia", "James Bond"));

        favouriteMovies
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }

    public static void getOrDefault() {
        Map<String, String> favouriteMovies
                = Map.ofEntries(entry("Raphael", "Star Wars"),
                entry("Olivia", "James Bond"));
        System.out.println(favouriteMovies.getOrDefault("Olivia", "Matrix"));
        System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix"));
    }

    public static void computePatterns() {
        /**
         *  computeIfAbsent—If there’s no specified value for the given key (it’s absent or its value is null), calculate a new value by using the key and add it to the Map.
         *  computeIfPresent—If the specified key is present, calculate a new value for it
         * and add it to the Map.
         *  compute—This operation calculates a new value for a given key and stores it in
         * the Map.
         */

        //friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>())
        //              .add("Star Wars");

    }

    public static boolean removePattern() {

        Map<String, String> favouriteMovies = new HashMap<>();

        String key = "Raphael";
        String value = "Jack Reacher 2";
        if (favouriteMovies.containsKey(key) &&
                Objects.equals(favouriteMovies.get(key), value)) {
            favouriteMovies.remove(key);
            return true;
        } else {
            return false;
        }

        // all above is equal to :
        // favouriteMovies.remove(key, value);
    }

    public static void replacePatterns() {
        /**
         * replaceAll—Replaces each entry’s value with the result of applying a BiFunction.
         *          This method works similarly to replaceAll on a List, which you saw earlier.
         *  Replace—Lets you replace a value in the Map if a key is present.
         *          An additional overload replaces the value only if it the key is mapped to a certain value.
         */

        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Star Wars");
        favouriteMovies.put("Olivia", "james bond");
        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies);

    }

    public static void mergePatterns() {

        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"), entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star Wars"));
        Map<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);


        /**
         * This code works as expected as long as you don’t have duplicate keys. If you require more flexibility
         * in how values are combined, you can use the new merge method. This method takes a BiFunction to merge
         * values that have a duplicate key. Suppose that Cristina is in both the family and friends maps but with
         * different associated movies:
         */

        Map<String, String> friends2 = Map.ofEntries(
                entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));
        Map<String, String> everyone2 = new HashMap<>(family);
        friends2.forEach((k, v) -> everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone2);

        /**
         * You can also use merge to implement initialization checks. Suppose that you have a Map for recording how
         * many times a movie is watched. You need to check that the key representing the movie is in the map before
         * you can increment its value:
         */

        Map<String, Long> moviesToCount = new HashMap<>();
        String movieName = "James Bond";
        Long count = moviesToCount.get(movieName);
        if (count == null) {
            moviesToCount.put(movieName, 1L);
        } else {
            moviesToCount.put(movieName, count + 1);
        }

        //this can be rewritten as:
        moviesToCount.merge(movieName, 1L, (key, count2) -> count2 +1L);

        /**
         * The second argument to merge in this case is 1L. The Javadoc specifies that this argu- ment is “the
         * non-null value to be merged with the existing value associated with the key or, if no existing value
         * or a null value is associated with the key, to be associated with the key.” Because the value returned
         * for that key is null, the value 1 is provided the first time around. The next time around, because
         * the value for the key was initial- ized to the value of 1, the BiFunction is applied to increment
         * the count.
         */


    }

    public static void main(String[] args) {
        sorting();
    }
}
