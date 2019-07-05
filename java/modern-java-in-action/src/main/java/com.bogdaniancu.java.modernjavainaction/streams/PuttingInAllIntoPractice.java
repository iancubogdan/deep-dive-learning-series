package com.bogdaniancu.java.modernjavainaction.streams;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Trader;
import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class PuttingInAllIntoPractice {


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

    public void exercise() {
        //1 Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> ex1 = transactions.stream()
                                            .filter(transaction -> transaction.getYear() == 2011)
                                            .sorted(Comparator.comparing(Transaction::getValue))
                                            .collect(Collectors.toList());
        System.out.println(ex1);

        //2 What are all the unique cities where the traders work?
        List<String> ex2 = transactions.stream()
                                            .map(transaction -> transaction.getTrader().getCity())
                                            .distinct()
                                            .collect(Collectors.toList());
        System.out.println(ex2);

        //alternative solution
        Set<String> ex2alt = transactions.stream()
                                            .map(transaction -> transaction.getTrader().getCity())
                                            .collect(Collectors.toSet());

        //3 Find all traders from Cambridge and sort them by name.
        List<Trader> ex3 = transactions.stream()
                                        .map(Transaction::getTrader)
                                        .distinct()
                                        .filter(trader -> trader.getCity() == "Cambridge")
                                        .sorted(Comparator.comparing(Trader::getName))
                                        .collect(Collectors.toList());
        System.out.println(ex3);

        //4 Return a string of all traders’ names sorted alphabetically.
        String ex4 = transactions.stream()
                                        .map(transaction -> transaction.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(ex4);

        //alternative solution
        String ex4Alt = transactions.stream()
                                        .map(transaction -> transaction.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .collect(Collectors.joining());
        System.out.println(ex4Alt);

        //5 Are any traders based in Milan?
        Boolean ex5 = transactions.stream()
                                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(ex5);

        //6 Print the values of all transactions from the traders living in Cambridge.
        transactions.stream()
                        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                        .forEach(System.out::println);

        //7 What’s the highest value of all the transactions?
        Optional<Integer> ex7 = transactions.stream()
                                        .map(Transaction::getValue)
                                        .reduce(Integer::max);
        System.out.println(ex7);

        //8 Find the transaction with the smallest value.
        Optional<Transaction> ex8 = transactions.stream()
                                        .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(ex8);

        //alternative solution
        Optional<Transaction> ex8Alt = transactions.stream()
                                        .min(Comparator.comparing(Transaction::getValue));
        System.out.println(ex8Alt);

    }

    public static void main(String[] args) {
        PuttingInAllIntoPractice p = new PuttingInAllIntoPractice();

        p.exercise();
    }
}
