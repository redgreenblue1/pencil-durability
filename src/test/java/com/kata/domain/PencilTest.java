package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.kata.domain.Pencil.DEFAULT_LENGTH;
import static org.testng.Assert.*;

@Test
public class PencilTest {
    private static final int INITIAL_DURABILITY = 10;
    private Pencil defaultPencil;
    private Pencil durablePencil;

    @BeforeMethod
    public void setup() {
        this.defaultPencil = Pencil.createDefault();
        this.durablePencil = Pencil.createWithDurability(INITIAL_DURABILITY);
    }

    public void whenPencilWritesATextItReturnsTheText() {
        String writtenText = defaultPencil.write("She sells sea shells");
        assertEquals(writtenText, "She sells sea shells");
    }

    public void whenPencilWritesAgainItAppendToOldText() {
        String firstText = "fast";
        defaultPencil.write(firstText);
        String secondText = " tiger";
        String writtenText = defaultPencil.write(secondText);
        assertEquals(writtenText, firstText + secondText);

    }

    public void whenPencilIsCreatedItCanHaveDurabilityPoint() {
        assertEquals(INITIAL_DURABILITY, durablePencil.getPointDurability());
    }

    public void whenDurablePencilWritesItAppendToOldText() {
        String firstText = "blue";
        durablePencil.write(firstText);
        String secondText = " Sky";
        String writtenText = durablePencil.write(secondText);
        assertEquals(writtenText, firstText + secondText);
    }

    public void whenPencilWritesLowerCaseLetterThenDurabilityPointReducesByOne() {
        durablePencil.write("m");
        assertEquals(durablePencil.getPointDurability(), INITIAL_DURABILITY - 1);
    }

    public void whenPencilWritesMultipleLowerCaseLettersThenDurabilityPointReduceByOneForEachLetter() {
        durablePencil.write("cat");
        assertEquals(durablePencil.getPointDurability(), INITIAL_DURABILITY - 3);
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

    public void whenPencilIsCreatedItHasNonZeroDefaultDurabilityPointValue() {
        Pencil defaultPencil = Pencil.createDefault();
        assertEquals(defaultPencil.getPointDurability(), 500);
    }

    public void whenPencilDurabilityIsNotSufficientItWriteSpacesForEachNextCharacter() {
        assertEquals(durablePencil.write("aABC DEFG"), "aABC D   ");
    }

    public void whenPencilDurabilityIsZeroItWritesSpacesForEachNextCharacter() {
        assertEquals(durablePencil.write("ABCDEF"), "ABCDE ");
    }

    public void whenPencilWritesNullItShouldNotWriteAnything() {
        String oldText = durablePencil.write("blue");
        int oldDurability = durablePencil.getPointDurability();
        assertEquals(durablePencil.write(null), oldText);
        assertEquals(durablePencil.getPointDurability(), oldDurability);
    }

    public void whenPencilCreatedItShouldAcceptDurabilityAndLength() {
        Pencil pencil = Pencil.createWithDurabilityAndLength(15, 3);
        assertEquals(pencil.getPointDurability(), 15);
        assertEquals(pencil.getLength(), 3);
    }

    public void whenPencilCreatedItShouldHaveDefaultLengthIfNotSpecified() {
        assertEquals(defaultPencil.getLength(), DEFAULT_LENGTH);
    }

}
