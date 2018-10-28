package com.kata.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class PageTest {
    public void whenPageIsCreatedItCanHaveText() {
        Page page = new Page("blue");
        assertEquals(page.getTextContents(), "blue");

    }

    public void whenPageUpdateItsContentItReturnsUpdatedContents() {
        Page page = new Page("initial");
        page.setContent("updated");
        assertEquals(page.getTextContents(), "updated");
    }

    public void whenPageEditItWritesCharactersOverLastErasedText() {
        Page page = new Page("ab de");
        page.setIndexOfLastErasedCharacter(2);
        String updatedText = page.editCharacter('X');
        assertEquals(updatedText, "abXde");
    }

    public void whenPageEditItWritesSpecialCharacterOverLastErasedTextIfLocationWasNotEmpty() {
        Page page = new Page("abcde");
        page.setIndexOfLastErasedCharacter(2);
        String updatedText = page.editCharacter('X');
        assertEquals(updatedText, "ab" + "@" + "de");
    }
}
