package com.kata.domain;

public class Pencil {


    public static final String ONE_SPACE = " ";
    private int durabilityPoint;
    private StringBuilder text;
    private int length;

    public Pencil() {
        this.durabilityPoint = 500;
        this.length = 4;
        text = new StringBuilder();
    }

    public Pencil(int durabilityPoint) {
        this();
        this.durabilityPoint = durabilityPoint;

    }

    public static Pencil createWithDurabilityAndLength(int durability, int length) {
        Pencil pencil = new Pencil();
        pencil.durabilityPoint = durability;
        pencil.length = length;
        return pencil;
    }

    public String write(String textToWrite) {
        if (textToWrite != null) {
            char[] inputTextCharacters = textToWrite.toCharArray();
            for (char inputCharacter : inputTextCharacters) {
                adjustDurability(inputCharacter);
                appendText(inputCharacter);
            }
        }
        return text.toString();
    }

    protected void adjustDurability(char inputCharacter) {
        if (Character.isLowerCase(inputCharacter)) {
            durabilityPoint--;
        } else if (Character.isUpperCase(inputCharacter)) {
            durabilityPoint -= 2;
        }
    }

    protected void appendText(char inputCharacter) {
        if (getPointDurability() >= 0) {
            text.append(inputCharacter);
        } else {
            text.append(ONE_SPACE);
        }
    }

    public int getPointDurability() {
        return durabilityPoint;
    }

    public int getLength() {
        return this.length;
    }
}
