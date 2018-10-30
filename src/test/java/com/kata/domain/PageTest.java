package com.kata.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class PageTest {
    public void whenPageIsCreatedItCanHaveText() {
        Page page = new BasicPage("blue");
        assertEquals(page.getTextContents(), "blue");

    }

    public void whenPageUpdateItsContentItReturnsUpdatedContents() {
        Page page = new BasicPage("initial");
        page.setContent("updated");
        assertEquals(page.getTextContents(), "updated");
    }

    public void whenPageEditCharacterItWritesCharactersOverSpecifiedLocation() {
        Page page = new BasicPage("ab de");
        page.editCharacterAt('X', 2);
        assertEquals(page.getTextContents(), "abXde");
    }

    public void whenPageEditCharacterItWritesSpecialCharacterOverSpecifiedLocationIfLocationWasNotEmpty() {
        BasicPage page = new BasicPage("abcde");
        page.editCharacterAt('X', 2);
        assertEquals(page.getTextContents(), "ab" + page.getCollideCharacter() + "de");
    }

}
