package com.kata.domain;

import java.util.Optional;

public class BasicPage implements Page {
    private static final char COLLIDE_CHARACTER = '@';
    private StringBuilder content;
    private Integer indexOfLastErasedCharacter;

    public BasicPage() {
        this.content = new StringBuilder();
    }

    public BasicPage(String inputText) {
        this.content = new StringBuilder(inputText);
    }

    @Override
    public String getTextContents() {
        return content.toString();
    }

    @Override
    public void append(char inputCharacter) {
        resetIndexOfLastErasedCharacter();
        content.append(inputCharacter);
    }

    private void resetIndexOfLastErasedCharacter() {
        setIndexOfLastErasedCharacter(null);
    }

    @Override
    public void setContent(String updated) {
        this.content = new StringBuilder(updated);
    }

    @Override
    public Optional<Integer> getIndexOfLastErasedCharacter() {
        return Optional.ofNullable(this.indexOfLastErasedCharacter);
    }

    @Override
    public void setIndexOfLastErasedCharacter(Integer indexOfLastErasedCharacter) {
        this.indexOfLastErasedCharacter = indexOfLastErasedCharacter;
    }

    @Override
    public char editCharacterAt(char charToEdit, int indexToEdit) {
        char charToSet = charToEdit;
        if (isCharacterCollide(indexToEdit)) {
            charToSet = getCollideCharacter();
        }
        content.setCharAt(indexToEdit, charToSet);
        return charToSet;
    }

    @Override
    public void replaceCharacterAt(int eraseIndex, char replacingCharacter) {
        content.setCharAt(eraseIndex, replacingCharacter);
    }

    @Override
    public Optional<Integer> getLastIndexOfTextMatch(String textToMatch) {
        int lastIndexOfMatch = content.lastIndexOf(textToMatch);
        if (lastIndexOfMatch != -1) {
            return Optional.of(lastIndexOfMatch);
        }
        return Optional.empty();
    }

    @Override
    public boolean isCharacterCollide(int indexToEdit) {
        return !Character.isWhitespace(content.charAt(indexToEdit));
    }

    @Override
    public char getCollideCharacter() {
        return COLLIDE_CHARACTER;
    }

    @Override
    public char getCharacterAt(int index) {
        return content.charAt(index);
    }
}
