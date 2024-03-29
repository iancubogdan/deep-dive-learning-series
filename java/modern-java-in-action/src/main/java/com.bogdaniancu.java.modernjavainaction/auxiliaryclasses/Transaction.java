package com.bogdaniancu.java.modernjavainaction.auxiliaryclasses;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private String referenceCode;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;

    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
