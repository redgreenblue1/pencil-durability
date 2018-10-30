package com.kata.domain;

import java.util.Optional;

public interface Eraser {
    int getDurability();

    String erase(String textToErase);

    Optional<Integer> getIndexOfLastErasedCharacter();

    Page getPage();
}
