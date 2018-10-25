package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class PencilTest {
    public static final int INITIAL_DURABILITY = 50;
    private Pencil pencil;
    private Pencil durablePencil;

    @BeforeMethod
    public void setup() {
        this.pencil = new Pencil();
        this.durablePencil = new Pencil(INITIAL_DURABILITY);
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
        Pencil durablePencil = new Pencil(INITIAL_DURABILITY);
        assertEquals(INITIAL_DURABILITY, durablePencil.getPointDurability());
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
        Pencil durablePencil = new Pencil(INITIAL_DURABILITY);
        durablePencil.write("m");
        assertEquals(durablePencil.getPointDurability(), 49);
    }

    public void whenPencilWritesMultipleLowerCaseLettersThenDurabilityPointReduceByOneForEachLetter() {
        durablePencil.write("cat");
        assertEquals(durablePencil.getPointDurability(), 47);
    }

    public void whenPencilWritesMultipleUpperCaseLettersThenDurabilityPointReduceByTwoForEachLetter() {
        durablePencil.write("CAT");
        assertEquals(durablePencil.getPointDurability(), INITIAL_DURABILITY - 6);
    }

    public void whenPencilWritesSpacesThenDurabilityPointIsNotReduced() {
        //One space character
        durablePencil.write(" ");
        assertEquals(durablePencil.getPointDurability(), INITIAL_DURABILITY);
    }


}
