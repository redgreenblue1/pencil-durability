package com.kata.domain;


import static com.google.common.base.Preconditions.checkArgument;

public class Eraser {
    public static final char DEFAULT_ERASE_CHARACTER = ' ';
    private static final int DEFAULT_DURABILITY = 10;
    private int durability;

    public Eraser() {
        this(DEFAULT_DURABILITY);
    }

    public Eraser(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }


    public String erase(String inputText, String textToErase) {
        checkArgument(inputText != null, "Input Text Can't be null");
        int lastIndexOfTextToErase = inputText.lastIndexOf(textToErase);
        char[] inputTextCharacters = inputText.toCharArray();
        int firstIndexOfTextToErase = lastIndexOfTextToErase + textToErase.length() - 1;
        for (int i = firstIndexOfTextToErase; i >= lastIndexOfTextToErase; i--) {
            if (eraseCharacter(inputTextCharacters, i)) {
                updateDurability();
            }
        }
        return String.copyValueOf(inputTextCharacters);
    }

    protected boolean eraseCharacter(char[] inputTextCharacters, int i) {
        if (canErase(inputTextCharacters[i])) {
            inputTextCharacters[i] = getEraseCharacterToReplaceWith();
            return true;
        }
        return false;
    }

    protected void updateDurability() {
        this.durability--;
    }

    protected boolean canErase(char inputTextCharacter) {
        return !Character.isWhitespace(inputTextCharacter) && getDurability() != 0;
    }

    protected char getEraseCharacterToReplaceWith() {
        return DEFAULT_ERASE_CHARACTER;
    }
}
