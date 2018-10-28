package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

@Test
public class EraserTest {
    private Eraser defaultEraser;

    @BeforeMethod
    public void setup() {
        defaultEraser = new Eraser();
    }

    public void whenEraserCreatedItHasPositiveDurability() {
        assertTrue(defaultEraser.getDurability() > 0);
    }

    public void whenEraserCreateItCanAcceptDurability() {
        Eraser eraser = new Eraser(10);
        assertEquals(eraser.getDurability(), 10);
    }

    public void whenEraserProvidedWithTextItCanEraseWords() {
        String inputText = "abcd ab cd";
        String updatedText = defaultEraser.erase(new Page(inputText), "cd");
        assertEquals(updatedText, "abcd ab   ");

    }

    public void whenEraserProvedWithTextItEraseTheLastOccurrence() {
        String inputText = "abcd ab ab";
        assertEquals(defaultEraser.erase(new Page(inputText), "ab"), "abcd ab   ");
    }

    public void whenEraserEraseItsDurabilityReduceByOneForEachNoneWhiteSpaceCharacter() {
        String inputText = "abcd eFg";
        int oldDurability = defaultEraser.getDurability();
        defaultEraser.erase(new Page(inputText), " eFg");
        assertEquals(defaultEraser.getDurability(), oldDurability - 3);
    }

    public void whenEraserErasesItSkipCharactersWhenDurabilityIsZero() {
        Eraser eraser = new Eraser(0);
        String updatedText = eraser.erase(new Page("abc"), "bc");
        assertEquals(updatedText, "abc");
    }

    public void whenEraserErasesItEraseFromRightToLeft() {
        Eraser eraser = new Eraser(2);
        String updatedText = eraser.erase(new Page("abcde"), "cde");
        assertEquals(updatedText, "abc  ");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEraserEraseFromNullTextItReturnsException() {
        defaultEraser.erase(null, "22");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEraserEraseNullTextItReturnsException() {
        defaultEraser.erase(new Page("abc"), null);
    }

    public void whenEraserErasesItCanReturnTheIndexOfLastDeletedCharacter() {
        defaultEraser.erase(new Page("abc de f"), "de");
        Optional<Integer> lastIndex = defaultEraser.getIndexOfLastErasedCharacter();
        assertEquals(lastIndex.get().intValue(), 4);
    }

    public void whenEraserErasesItMayNotEraseAnyCharacterIfNoMatchFound() {
        String originalText = "abc de f";
        String updatedText = defaultEraser.erase(new Page(originalText), "XZ");
        assertEquals(updatedText, originalText);
    }

    public void whenEraserErasesNoCharactersItsLastEraseLocationIsNull() {
        defaultEraser.erase(new Page("abc de f"), "XY");
        Optional<Integer> lastIndex = defaultEraser.getIndexOfLastErasedCharacter();
        assertFalse(lastIndex.isPresent());
    }

    public void whenEraserErasesAndLastCharacterIsWhiteSpaceItReturnsTheIndexOfWhiteSpace() {
        defaultEraser.erase(new Page("ab f"), " f");
        Optional<Integer> lastIndex = defaultEraser.getIndexOfLastErasedCharacter();
        assertEquals(lastIndex.get().intValue(), 2);
    }

}
