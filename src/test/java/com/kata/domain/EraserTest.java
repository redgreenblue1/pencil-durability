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
        defaultEraser = new Eraser(new Page());
    }

    public void whenEraserCreatedItHasPositiveDurability() {
        assertTrue(defaultEraser.getDurability() > 0);
    }

    public void whenEraserCreateItCanAcceptDurability() {
        Eraser eraser = new Eraser(10, new Page());
        assertEquals(eraser.getDurability(), 10);
    }

    public static Eraser createEraserWithPage(String pageText) {
        return new Eraser(new Page(pageText));
    }

    public void whenEraserProvidedWithTextItCanEraseWords() {
        String inputText = "abcd ab cd";
        Eraser eraser = createEraserWithPage(inputText);
        String updatedText = eraser.erase("cd");
        assertEquals(updatedText, "abcd ab   ");

    }

    public void whenEraserProvedWithTextItEraseTheLastOccurrence() {
        String inputText = "abcd ab ab";
        Eraser eraser = createEraserWithPage(inputText);
        assertEquals(eraser.erase("ab"), "abcd ab   ");
    }

    public void whenEraserEraseItsDurabilityReduceByOneForEachNoneWhiteSpaceCharacter() {
        String inputText = "abcd eFg";
        Eraser eraser = createEraserWithPage(inputText);
        int oldDurability = eraser.getDurability();
        eraser.erase(" eFg");
        assertEquals(eraser.getDurability(), oldDurability - 3);
    }

    public void whenEraserErasesItSkipCharactersWhenDurabilityIsZero() {
        Eraser eraser = new Eraser(0, new Page("abc"));
        String updatedText = eraser.erase("bc");
        assertEquals(updatedText, "abc");
    }

    public void whenEraserErasesItEraseFromRightToLeft() {
        Eraser eraser = new Eraser(2, new Page("abcde"));
        String updatedText = eraser.erase("cde");
        assertEquals(updatedText, "abc  ");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenEraserEraseNullTextItReturnsException() {
        defaultEraser.erase(null);
    }

    public void whenEraserErasesItCanReturnTheIndexOfLastDeletedCharacter() {
        Eraser eraser = createEraserWithPage("abc de f");
        eraser.erase("de");
        Optional<Integer> lastIndex = eraser.getIndexOfLastErasedCharacter();
        assertEquals(lastIndex.get().intValue(), 4);
    }

    public void whenEraserErasesItMayNotEraseAnyCharacterIfNoMatchFound() {
        String originalText = "abc de f";
        Eraser eraser = createEraserWithPage(originalText);
        String updatedText = eraser.erase("XZ");
        assertEquals(updatedText, originalText);
    }

    public void whenEraserErasesNoCharactersItsLastEraseLocationIsNull() {
        Eraser eraser = createEraserWithPage("abc de f");
        eraser.erase("XY");
        Optional<Integer> lastIndex = eraser.getIndexOfLastErasedCharacter();
        assertFalse(lastIndex.isPresent());
    }

    public void whenEraserCreatedItCanBeProvidedWithPage() {
        Page page = new Page();
        Eraser eraser = new Eraser(page);
        assertEquals(eraser.getPage(), page);
    }

    public void whenEraserCreatedItCanBeProvidedWithPageAndDurability() {
        Page page = new Page();
        Eraser eraser = new Eraser(10, page);
        assertEquals(eraser.getPage(), page);
        assertEquals(eraser.getDurability(), 10);
    }

    public void whenEraserErasesAndLastCharacterIsWhiteSpaceItReturnsTheIndexOfWhiteSpace() {
        Eraser eraser = createEraserWithPage("ab f");
        eraser.erase(" f");
        Optional<Integer> lastIndex = eraser.getIndexOfLastErasedCharacter();
        assertEquals(lastIndex.get().intValue(), 2);
    }

}
