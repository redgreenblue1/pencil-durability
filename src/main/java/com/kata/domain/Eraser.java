package com.kata.domain;


import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Eraser {
    public static final char DEFAULT_ERASE_CHARACTER = ' ';
    private static final int DEFAULT_DURABILITY = 10;
    private int durability;
    private Integer indexOfLastErasedCharacter;
    private Page page;

    public Eraser() {
        this(DEFAULT_DURABILITY);
    }

    public Eraser(int durability) {
        this.durability = durability;
    }

    public Eraser(Page page) {
        this.page = page;
    }

    public Eraser(int durability, Page page) {
        this.durability = durability;
        this.page = page;
    }

    public int getDurability() {
        return this.durability;
    }


    public String erase(Page page, String textToErase) {
        validateArguments(page, textToErase);
        String inputText = page.getTextContents();
        Optional<Integer> optionalLastIndexOfTextToErase = getLastIndexOfTextToErase(textToErase, inputText);
        if (optionalLastIndexOfTextToErase.isPresent()) {
            inputText = eraseCharacters(textToErase, inputText, optionalLastIndexOfTextToErase.get());
        }
        return inputText;
    }

    private String eraseCharacters(String textToErase, String inputText, int lastIndexOfTextToErase) {
        char[] inputTextCharacters = inputText.toCharArray();
        int firstIndexOfTextToErase = lastIndexOfTextToErase + textToErase.length() - 1;
        for (int i = firstIndexOfTextToErase; i >= lastIndexOfTextToErase; i--) {
            updateLastIndexOfErase(inputTextCharacters[i], i);
            if (eraseCharacter(inputTextCharacters, i)) {
                updateDurability();
            }
        }
        return String.valueOf(inputTextCharacters);
    }

    private void updateLastIndexOfErase(char inputTextCharacter, int i) {
        if (canBeAssignedAsLastIndexOfErase(inputTextCharacter)) {
            this.indexOfLastErasedCharacter = i;
        }
    }

    private Optional<Integer> getLastIndexOfTextToErase(String textToErase, String inputText) {
        int lastIndexOfTextToErase = inputText.lastIndexOf(textToErase);
        if (lastIndexOfTextToErase != -1) {
            return Optional.of(lastIndexOfTextToErase);
        }
        return Optional.empty();
    }

    private void validateArguments(Page inputText, String textToErase) {
        checkArgument(inputText != null);
        checkArgument(textToErase != null);
    }

    protected boolean eraseCharacter(char[] inputTextCharacters, int i) {
        if (canErase(inputTextCharacters[i])) {
            inputTextCharacters[i] = getEraseCharacterToReplaceWith();
            return true;
        }
        return false;
    }

    protected boolean canBeAssignedAsLastIndexOfErase(char inputTextCharacter) {
        return Character.isWhitespace(inputTextCharacter) || getDurability() != 0;
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

    public Optional<Integer> getIndexOfLastErasedCharacter() {
        return Optional.ofNullable(this.indexOfLastErasedCharacter);
    }

    public Page getPage() {
        return this.page;
    }
}
