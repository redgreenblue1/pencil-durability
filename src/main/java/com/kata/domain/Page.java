package com.kata.domain;

import java.util.Optional;

/**
 * Text container to perform operation on it's contents
 */
public interface Page {
    String getTextContents();

    void append(char inputCharacter);

    void setContent(String updated);

    Optional<Integer> getIndexOfLastErasedCharacter();

    void setIndexOfLastErasedCharacter(Integer indexOfLastErasedCharacter);

    char editCharacterAt(char charToEdit, int indexToEdit);

    void replaceCharacterAt(int eraseIndex, char replacingCharacter);

    Optional<Integer> getLastIndexOfTextMatch(String textToMatch);

    boolean isCharacterCollide(int indexToEdit);

    char getCollideCharacter();

    char getCharacterAt(int index);
}
