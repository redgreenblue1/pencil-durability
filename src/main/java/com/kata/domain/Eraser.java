package com.kata.domain;


import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Eraser {
    public static final char DEFAULT_ERASE_CHARACTER = ' ';
    private static final int DEFAULT_DURABILITY = 10;
    private int durability;
    private Integer indexOfLastErasedCharacter;
    private Page page;

    public Eraser(Page page) {
        this(DEFAULT_DURABILITY, page);
    }

    public Eraser(int durability, Page page) {
        checkArgument(page != null);
        this.durability = durability;
        this.page = page;
    }

    public int getDurability() {
        return this.durability;
    }

    public String erase(String textToErase) {
        checkArgument(textToErase != null);
        String pageContent = getPage().getTextContents();
        Optional<Integer> optionalLastIndexOfTextToErase = getLastIndexOfTextToErase(textToErase, pageContent);
        if (optionalLastIndexOfTextToErase.isPresent()) {
            eraseCharacters(textToErase, optionalLastIndexOfTextToErase.get());
        }
        return getPage().getTextContents();
    }

    private void eraseCharacters(String textToErase, int lastIndexOfTextToErase) {
        int firstIndexOfTextToErase = getFirstIndexOfTextToErase(textToErase, lastIndexOfTextToErase);
        for (int i = firstIndexOfTextToErase; i >= lastIndexOfTextToErase; i--) {
            updateLastIndexOfErase(getPage().getCharacterAt(i), i);
            if (eraseCharacter(i)) {
                updateDurability();
            }
        }
    }

    private int getFirstIndexOfTextToErase(String textToErase, int lastIndexOfTextToErase) {
        return lastIndexOfTextToErase + textToErase.length() - 1;
    }

    private void updateLastIndexOfErase(char inputTextCharacter, int characterIndex) {
        if (canBeAssignedAsLastIndexOfErase(inputTextCharacter)) {
            this.indexOfLastErasedCharacter = characterIndex;
            getPage().setIndexOfLastErasedCharacter(characterIndex);
        }
    }

    private Optional<Integer> getLastIndexOfTextToErase(String textToErase, String inputText) {
        int lastIndexOfTextToErase = inputText.lastIndexOf(textToErase);
        if (lastIndexOfTextToErase != -1) {
            return Optional.of(lastIndexOfTextToErase);
        }
        return Optional.empty();
    }

    protected boolean eraseCharacter(int eraseIndex) {
        if (canErase(getPage().getCharacterAt(eraseIndex))) {
            getPage().replaceCharacterAt(eraseIndex, getEraseCharacterToReplaceWith());
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
