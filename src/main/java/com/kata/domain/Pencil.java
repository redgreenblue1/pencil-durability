package com.kata.domain;

public class Pencil {


    private int durabilityPoint;
    private StringBuilder text;

    public Pencil() {
        this.durabilityPoint = 500;
        text = new StringBuilder();
    }

    public Pencil(int durabilityPoint) {
        this();
        this.durabilityPoint = durabilityPoint;

    }

    public String write(String textToWrite) {
        char[] inputTextCharacters = textToWrite.toCharArray();
        for (char inputCharacter : inputTextCharacters) {
            if (Character.isLowerCase(inputCharacter)) {
                durabilityPoint--;
            } else if (Character.isUpperCase(inputCharacter)) {
                durabilityPoint -= 2;
            }
            if (getPointDurability() >= 0) {
                text.append(inputCharacter);
            } else {
                text.append(" ");
            }
        }
        return text.toString();
    }

    public int getPointDurability() {
        return durabilityPoint;
    }
}
