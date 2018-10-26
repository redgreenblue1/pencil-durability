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
        int lastIndexOfTextToErase = inputText.lastIndexOf(textToErase);
        char[] inputTextCharacters = inputText.toCharArray();
        for (int i = lastIndexOfTextToErase + textToErase.length() - 1; i >= lastIndexOfTextToErase; i--) {
            if (canErase(inputTextCharacters[i])) {
                handleErase(inputTextCharacters, i);
            }
        }
        return String.copyValueOf(inputTextCharacters);
    }

    protected boolean canErase(char inputTextCharacter) {
        return !Character.isWhitespace(inputTextCharacter) && getDurability() != 0;
    }

    protected void handleErase(char[] inputTextCharacters, int i) {
        inputTextCharacters[i] = ' ';
        this.durability--;
    }
}
