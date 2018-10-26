package com.kata.domain;


public class Eraser {
    private int durability;

    public Eraser() {
        this(10);
    }

    public Eraser(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }


    public String erase(String inputText, String textToErase) {
        int lastIndexOfTextToEars = inputText.lastIndexOf(textToErase);
        char[] inputTextCharacters = inputText.toCharArray();
        for (int i = lastIndexOfTextToEars; i < lastIndexOfTextToEars + textToErase.length(); i++) {
            inputTextCharacters[i] = ' ';
        }
        return String.copyValueOf(inputTextCharacters);
    }
}
