package com.bogdaniancu.java.modernjavainaction.streams;

import com.bogdaniancu.java.modernjavainaction.auxiliaryclasses.WordCounter;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorTest {

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter ++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                                WordCounter::accumulate,
                                                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE =
                " Nel   mezzo del cammin  di nostra  vita " +
                        "mi  ritrovai in una  selva oscura" +
                        " ché la  dritta via era   smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

        System.out.println("Found " + countWords(stream) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);

        System.out.println("Found " + countWords(stream1) + " words");

    }
}
