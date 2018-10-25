package com.kata.domain;

public class Pencil {


    private int durabilityPoint;
    private StringBuilder text;

    public Pencil() {
        text = new StringBuilder();
    }

    public Pencil(int durabilityPoint) {
        this();
        this.durabilityPoint = durabilityPoint;

    }

    public String write(String textToWrite) {
        if ("m".equals(textToWrite)) {
            durabilityPoint--;
        }
        text.append(textToWrite);
        return text.toString();
    }

    public int getPointDurability() {
        return durabilityPoint;
    }
}
