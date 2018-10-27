package com.kata.domain;

public class Page {
    private StringBuilder content;

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
}
