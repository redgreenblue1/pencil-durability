package com.kata.domain;

public class Pencil {


    public static final String ONE_SPACE = " ";
    public static final int DEFAULT_DURABILITY_POINT = 500;
    public static final int DEFAULT_LENGTH = 4;
    private int durabilityPoint;
    private StringBuilder text;
    private int length;
    private int initialDurability;
    private Eraser eraser;

    private Pencil() {
        // elect to use builders instead
    }

    public static Pencil createDefault() {
        return createWithDurability(DEFAULT_DURABILITY_POINT);
    }

    public static Pencil createWithDurability(int durabilityPoint) {
        return createWithDurabilityAndLength(durabilityPoint, DEFAULT_LENGTH);
    }

    public static Pencil createWithDurabilityAndLength(int durability, int length) {
        Pencil pencil = new Pencil();
        pencil.durabilityPoint = durability;
        pencil.initialDurability = durability;
        pencil.length = length;
        pencil.eraser = new Eraser();
        pencil.text = new StringBuilder();
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

    public int getInitialDurability() {
        return this.initialDurability;
    }

    public void sharpen() {
        if (getLength() > 0) {
            this.length--;
            this.durabilityPoint = getInitialDurability();
        }

    }

    public Eraser getEraser() {
        return this.eraser;
    }

    public void setEraser(Eraser eraser) {
        this.eraser = eraser;
    }

    public String erase(String textToErase) {
        return getEraser().erase(text.toString(), textToErase);
    }
}
