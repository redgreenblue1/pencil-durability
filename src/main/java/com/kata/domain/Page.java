package com.kata.domain;

import java.util.Optional;

public class Page {
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
        content.append(inputCharacter);
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

    public String editCharacter(char charToEdit, int locationToEdit) {
        if (!Character.isWhitespace(content.charAt(locationToEdit))) {
            content.setCharAt(locationToEdit, '@');
        } else {
            content.setCharAt(locationToEdit, charToEdit);
        }
        return getTextContents();
    }
}
