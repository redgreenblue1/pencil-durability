package com.kata.domain;

public class Pencil {


    private String text = "";

    public String write(String textToWrite) {
        this.text += textToWrite;
        return this.text;
    }
}
