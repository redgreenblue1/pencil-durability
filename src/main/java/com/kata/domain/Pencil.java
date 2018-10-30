package com.kata.domain;

/**
 * To write, erase, and edit text. It uses a sharper to re-initialize it's durability
 */
public interface Pencil {
    void write(String textToWrite);

    void edit(String textToEdit);

    void sharpen(Sharpener sharpener);

    int getPointDurability();

    int getLength();

    int getInitialDurability();

    Eraser getEraser();

    void setEraser(Eraser eraser);

    String erase(String textToErase);

    Page getPage();

    String getPageTextContent();
}
