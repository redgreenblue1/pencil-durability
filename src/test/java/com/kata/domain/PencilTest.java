package com.kata.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class PencilTest {
    public void whenPencilWritesATextItReturnsTheText() {
        Pencil pencil = new Pencil();
        String writtenText = pencil.write("She sells sea shells");
        assertEquals(writtenText, "She sells sea shells");
    }

    public void whenPencilWritesAgainItAppendToOldText() {
        Pencil pencil = new Pencil();
        String firstText = "She sells sea shells";
        pencil.write(firstText);
        String secondText = " down by the sea shore";
        String writtenText = pencil.write(secondText);
        assertEquals(writtenText, firstText + secondText);

    }
}
