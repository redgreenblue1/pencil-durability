package com.kata.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class EraserTest {
    public void whenEraserCreatedItHasPositiveDurability() {
        Eraser eraser = new Eraser();
        assertTrue(eraser.getDurability() > 0);
    }

    public void whenEraserCreateItCanAcceptDurability() {
        Eraser eraser = new Eraser(10);
        assertEquals(eraser.getDurability(), 10);
    }

    public void whenEraserProvidedWithTextItCanEraseWords() {
        Eraser eraser = new Eraser();
        String inputText = "abcd ab cd";
        String updatedText = eraser.erase(inputText, "cd");
        assertEquals(updatedText, "abcd ab   ");

    }

    public void whenEraserProvedWithTextItEraseTheLastOccurrence() {
        Eraser eraser = new Eraser();
        String inputText = "abcd ab ab";
        assertEquals(eraser.erase(inputText, "ab"), "abcd ab   ");
    }

    public void whenEraserEraseItsDurabilityReduceByOneForEachNoneWhiteSpaceCharacter() {
        Eraser eraser = new Eraser();
        String inputText = "abcd eFg";
        int oldDurability = eraser.getDurability();
        eraser.erase(inputText, " eFg");
        assertEquals(eraser.getDurability(), oldDurability - 3);
    }

    public void whenEraserErasesItSkipCharactersWhenDurabilityIsZero() {
        Eraser eraser = new Eraser(0);
        String updatedText = eraser.erase("abc", "bc");
        assertEquals(updatedText, "abc");
    }

}
