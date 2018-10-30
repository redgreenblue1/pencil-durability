package com.kata.domain;

import java.util.Optional;

/**
 * Erases Text form a specific page
 */
public interface Eraser {
    int getDurability();

    String erase(String textToErase);

    Optional<Integer> getIndexOfLastErasedCharacter();

    Page getPage();
}
