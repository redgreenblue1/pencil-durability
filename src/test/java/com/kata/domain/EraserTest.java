package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class EraserTest {
    private Eraser defalutEraser;

    @BeforeMethod
    public void setup() {
        defalutEraser = new Eraser();
    }

    public void whenEraserCreatedItHasPositiveDurability() {
        assertTrue(defalutEraser.getDurability() > 0);
    }

    public void whenEraserCreateItCanAcceptDurability() {
        Eraser eraser = new Eraser(10);
        assertEquals(eraser.getDurability(), 10);
    }

    public void whenEraserProvidedWithTextItCanEraseWords() {
        String inputText = "abcd ab cd";
        String updatedText = defalutEraser.erase(inputText, "cd");
        assertEquals(updatedText, "abcd ab   ");

    }

    public void whenEraserProvedWithTextItEraseTheLastOccurrence() {
        String inputText = "abcd ab ab";
        assertEquals(defalutEraser.erase(inputText, "ab"), "abcd ab   ");
    }

    public void whenEraserEraseItsDurabilityReduceByOneForEachNoneWhiteSpaceCharacter() {
        String inputText = "abcd eFg";
        int oldDurability = defalutEraser.getDurability();
        defalutEraser.erase(inputText, " eFg");
        assertEquals(defalutEraser.getDurability(), oldDurability - 3);
    }

    public void whenEraserErasesItSkipCharactersWhenDurabilityIsZero() {
        Eraser eraser = new Eraser(0);
        String updatedText = eraser.erase("abc", "bc");
        assertEquals(updatedText, "abc");
    }

    public void whenEraserErasesItEraseFromRightToLeft() {
        Eraser eraser = new Eraser(2);
        String updatedText = eraser.erase("abcde", "cde");
        assertEquals(updatedText, "abc  ");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEraserEraseFromNullTextItReturnsException() {
        defalutEraser.erase(null, "22");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEraserEraseNullTextItReturnsException() {
        defalutEraser.erase("abc", null);
    }
}
