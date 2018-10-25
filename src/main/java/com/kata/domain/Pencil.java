package com.kata.domain;

public class Pencil {


    private StringBuilder text;

    public Pencil() {
        text = new StringBuilder();
    }

    public String write(String textToWrite) {
        text.append(textToWrite);
        return text.toString();
    }
}
