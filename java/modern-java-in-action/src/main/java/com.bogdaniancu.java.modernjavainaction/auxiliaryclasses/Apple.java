package com.bogdaniancu.java.modernjavainaction.auxiliaryclasses;


public class Apple implements Fruit{

    private Color color;
    private Integer weight;

    public Apple() {
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return null;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
