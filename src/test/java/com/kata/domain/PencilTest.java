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
}
