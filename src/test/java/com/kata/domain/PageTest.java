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

    public void whenPageEditItWritesCharactersOverSpecifiedLocation() {
        Page page = new Page("ab de");
        String updatedText = page.editCharacter('X', 2);
        assertEquals(updatedText, "abXde");
    }

    public void whenPageEditItWritesSpecialCharacterOverSpecifiedLocationIfLocationWasNotEmpty() {
        Page page = new Page("abcde");
        String updatedText = page.editCharacter('X', 2);
        assertEquals(updatedText, "ab" + page.getCollideCharacter() + "de");
    }
}
