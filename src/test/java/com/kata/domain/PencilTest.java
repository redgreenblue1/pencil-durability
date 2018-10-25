package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class PencilTest {
    private Pencil pencil;

    @BeforeMethod
    public void setup() {
        this.pencil = new Pencil();
    }

    public void whenPencilWritesATextItReturnsTheText() {
        String writtenText = pencil.write("She sells sea shells");
        assertEquals(writtenText, "She sells sea shells");
    }

    public void whenPencilWritesAgainItAppendToOldText() {
        String firstText = "She sells sea shells";
        pencil.write(firstText);
        String secondText = " down by the sea shore";
        String writtenText = pencil.write(secondText);
        assertEquals(writtenText, firstText + secondText);

    }

    public void whenPencilIsCreatedItCanHaveDurabilityPoint() {
        Pencil durablePencil = new Pencil(50);
        assertEquals(50, durablePencil.getPointDurability());
    }

    public void whenDurablePencilWritesItAppendToOldText() {
        Pencil durablePencil = new Pencil(100);
        String firstText = "She sells sea shells";
        durablePencil.write(firstText);
        String secondText = " down by the sea shore";
        String writtenText = durablePencil.write(secondText);
        assertEquals(writtenText, firstText + secondText);
    }

    public void whenPencilWritesLowerCaseLetterThenDurabilityPointReducesByOne() {
        Pencil durablePencil = new Pencil(50);
        durablePencil.write("m");
        assertEquals(durablePencil.getPointDurability(), 49);
    }


}
