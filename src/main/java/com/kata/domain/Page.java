package com.kata.domain;

import java.util.Optional;

public class Page {
    private static final char COLLIDE_CHARACTER = '@';
    private StringBuilder content;
    private Integer indexOfLastErasedCharacter;

    public Page() {
        this.content = new StringBuilder();
    }

    public Page(String inputText) {
        this.content = new StringBuilder(inputText);
    }

    public String getTextContents() {
        return content.toString();
    }

    public void append(char inputCharacter) {
        resetIndexOfLastErasedCharacter();
        content.append(inputCharacter);
    }

    private void resetIndexOfLastErasedCharacter() {
        setIndexOfLastErasedCharacter(null);
    }

    public void setContent(String updated) {
        this.content = new StringBuilder(updated);
    }

    public Optional<Integer> getIndexOfLastErasedCharacter() {
        return Optional.ofNullable(this.indexOfLastErasedCharacter);
    }

    public void setIndexOfLastErasedCharacter(Integer indexOfLastErasedCharacter) {
        this.indexOfLastErasedCharacter = indexOfLastErasedCharacter;
    }

    public char editCharacterAt(char charToEdit, int indexToEdit) {
        char charToSet = charToEdit;
        if (isCharacterCollide(indexToEdit)) {
            charToSet = getCollideCharacter();
        }
        content.setCharAt(indexToEdit, charToSet);
        return charToSet;
    }

    public void replaceCharacterAt(int eraseIndex, char replacingCharacter) {
        content.setCharAt(eraseIndex, replacingCharacter);
    }

    protected boolean isCharacterCollide(int indexToEdit) {
        return !Character.isWhitespace(content.charAt(indexToEdit));
    }

    protected char getCollideCharacter() {
        return COLLIDE_CHARACTER;
    }

    public char getCharacterAt(int index) {
        return content.charAt(index);
    }
}
