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

    public void whenPageEditCharacterItWritesCharactersOverSpecifiedLocation() {
        Page page = new Page("ab de");
        String updatedText = page.editCharacter('X', 2);
        assertEquals(updatedText, "abXde");
    }

    public void whenPageEditCharacterItWritesSpecialCharacterOverSpecifiedLocationIfLocationWasNotEmpty() {
        Page page = new Page("abcde");
        String updatedText = page.editCharacter('X', 2);
        assertEquals(updatedText, "ab" + page.getCollideCharacter() + "de");
    }

    public void whenPageEditItWritesCharacterOverIndexOfLastErase() {
        Page page = new Page("ab de");
        page.setIndexOfLastErasedCharacter(2);
        String updatedText = page.edit("XZ");
        assertEquals(updatedText, String.format("abX%se", page.getCollideCharacter()));
    }

}
