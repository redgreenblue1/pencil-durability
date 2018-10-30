package com.kata.domain;


import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class BasicEraser implements Eraser {
    public static final char DEFAULT_ERASE_CHARACTER = ' ';
    private static final int DEFAULT_DURABILITY = 10;
    private int durability;
    private Integer indexOfLastErasedCharacter;
    private Page page;

    public BasicEraser(Page page) {
        this(DEFAULT_DURABILITY, page);
    }

    public BasicEraser(int durability, Page page) {
        checkArgument(page != null);
        this.durability = durability;
        this.page = page;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public String erase(String textToErase) {
        checkArgument(textToErase != null);
        Optional<Integer> lastIndexOfTextErase = getPage().getLastIndexOfTextMatch(textToErase);
        lastIndexOfTextErase.ifPresent(integer -> eraseCharacters(textToErase, integer));
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

    @Override
    public Optional<Integer> getIndexOfLastErasedCharacter() {
        return Optional.ofNullable(this.indexOfLastErasedCharacter);
    }

    @Override
    public Page getPage() {
        return this.page;
    }
}
