package com.kata.domain;

public class Pencil {


    public static final String ONE_SPACE = " ";
    public static final int DEFAULT_DURABILITY_POINT = 500;
    public static final int DEFAULT_LENGTH = 4;
    private int durabilityPoint;
    private StringBuilder text;
    private int length;

    public Pencil() {
        this.durabilityPoint = DEFAULT_DURABILITY_POINT;
        this.length = DEFAULT_LENGTH;
        text = new StringBuilder();
    }

    public static Pencil createWithDurability(int durabilityPoint) {
        return createWithDurabilityAndLength(durabilityPoint, DEFAULT_LENGTH);
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
