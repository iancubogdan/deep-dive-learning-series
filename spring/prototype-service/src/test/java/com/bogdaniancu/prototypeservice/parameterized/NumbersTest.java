package com.bogdaniancu.prototypeservice.parameterized;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Numbers {
    public static boolean isOdd(int number) {
        return number % 2!=0;
    }
}

class Strings {
    public static boolean isBlank(String input) {
        return input == null || input.trim().isEmpty();
    }
}

class ParameterizedTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, -15})
    void returnTrueForOddNumbers(int number) {
        assertTrue(Numbers.isOdd(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void returnTrueForNullOrBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullSource
    void isBlank_ShouldReturnTrueForNullInputs(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @EmptySource
    void isBlank_ShouldReturnTrueForEmptyStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
        assertTrue(Strings.isBlank(input));
    }


//    @ParameterizedTest
//    @EnumSource(Month.class) // passing all 12 months
//    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
//        int monthNumber = month.getValue();
//        assertTrue(monthNumber >= 1 && monthNumber <= 12);
//    }

//    @ParameterizedTest
//    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
//    void someMonths_Are30DaysLong(Month month) {
//        final boolean isALeapYear = false;
//        assertEquals(30, month.length(isALeapYear));
//    }
//
//    @ParameterizedTest
//    @EnumSource(
//            value = Month.class,
//            names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
//            mode = EnumSource.Mode.EXCLUDE)
//    void exceptFourMonths_OthersAre31DaysLong(Month month) {
//        final boolean isALeapYear = false;
//        assertEquals(31, month.length(isALeapYear));
//    }
//
//    @ParameterizedTest
//    @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
//    void fourMonths_AreEndingWithBer(Month month) {
//        EnumSet<Month> months =
//                EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
//        assertTrue(months.contains(month));
//    }

    @ParameterizedTest
//    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    @CsvSource(value = {"test:TEST", "tEst:TEST", "Java:JAVA"}, delimiter = ':')
//    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void generateUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(actualValue, expected);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void returnTrueForNullOrBlankStrings(String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    //If we're going to provide just one argument per test invocation, then it's not necessary to use the
    // Arguments abstraction:
    @ParameterizedTest
    @MethodSource // hmm, no method name ...
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        assertTrue(Strings.isBlank(input));
    }

    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }

    //When we don't provide a name for the @MethodSource, JUnit will search for a source method with the same name as
    // the test method.
}