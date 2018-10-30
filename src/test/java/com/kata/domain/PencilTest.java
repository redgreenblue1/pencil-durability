package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class PencilTest {
    public static final String BLUE_VALUE = "blue";
    private static final int INITIAL_DURABILITY = 10;
    private Pencil defaultPencil;
    private Pencil durablePencil;

    @BeforeMethod
    public void setup() {
        this.defaultPencil = BasicPencil.createDefault();
        this.durablePencil = BasicPencil.createWithDurability(INITIAL_DURABILITY);
    }

    public void whenPencilWritesATextItReturnsTheText() {
        defaultPencil.write("She sells sea shells");
        assertEquals(defaultPencil.getPageTextContent(), "She sells sea shells");
    }

    public void whenPencilWritesAgainItAppendToOldText() {
        String firstText = "fast";
        defaultPencil.write(firstText);
        String secondText = " tiger";
        defaultPencil.write(secondText);
        assertEquals(defaultPencil.getPageTextContent(), firstText + secondText);

    }

    public void whenPencilIsCreatedItCanHaveDurabilityPoint() {
        assertEquals(INITIAL_DURABILITY, durablePencil.getPointDurability());
    }

    public void whenDurablePencilWritesItAppendToOldText() {
        String firstText = BLUE_VALUE;
        durablePencil.write(firstText);
        String secondText = " Sky";
        durablePencil.write(secondText);
        assertEquals(durablePencil.getPageTextContent(), firstText + secondText);
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
        Pencil defaultPencil = BasicPencil.createDefault();
        assertEquals(defaultPencil.getPointDurability(), 500);
    }

    public void whenPencilDurabilityIsNotSufficientItWriteSpacesForEachNextCharacter() {
        durablePencil.write("aABC DEFG");
        assertEquals(durablePencil.getPageTextContent(), "aABC D   ");
    }

    public void whenPencilDurabilityIsZeroItWritesSpacesForEachNextCharacter() {
        durablePencil.write("ABCDEF");
        assertEquals(durablePencil.getPageTextContent(), "ABCDE ");
    }

    public void whenPencilWritesNullItShouldNotWriteAnything() {
        durablePencil.write(BLUE_VALUE);
        String oldText = durablePencil.getPageTextContent();
        int oldDurability = durablePencil.getPointDurability();
        durablePencil.write(null);
        assertEquals(durablePencil.getPageTextContent(), oldText);
        assertEquals(durablePencil.getPointDurability(), oldDurability);
    }

    public void whenPencilCreatedItShouldAcceptDurabilityAndLength() {
        Pencil pencil = BasicPencil.createWithDurabilityAndLength(15, 3);
        assertEquals(pencil.getPointDurability(), 15);
        assertEquals(pencil.getLength(), 3);
    }

    public void whenPencilCreatedItShouldHaveDefaultLengthIfNotSpecified() {
        assertTrue(defaultPencil.getLength() > 0);
    }

    public void whenPencilAskedToReturnInitialDurabilityItShouldReturnIt() {
        assertEquals(durablePencil.getInitialDurability(), INITIAL_DURABILITY);
    }

    public void whenPencilDurabilityChangeItShouldNotChangeItsInitialDurability() {
        assertEquals(durablePencil.getInitialDurability(), INITIAL_DURABILITY);
        durablePencil.write(BLUE_VALUE);
        assertEquals(durablePencil.getInitialDurability(), INITIAL_DURABILITY);
    }

    public void whenPencilIsSharpenedItsLengthReduceByOne() {
        int oldLength = durablePencil.getLength();
        durablePencil.sharpen(new BasicSharpener(1));
        assertEquals(durablePencil.getLength(), oldLength - 1);
    }

    public void whenPencilIsSharpenedWithSharpenerItsLengthReduceBySharpenerValue() {
        int oldLength = durablePencil.getLength();
        Sharpener sharpener = new BasicSharpener(2);
        durablePencil.sharpen(sharpener);
        assertEquals(durablePencil.getLength(), oldLength - 2);
    }

    public void whenPencilIsSharpenedItRegainsInitialDurability() {
        int oldDurability = durablePencil.getInitialDurability();
        durablePencil.write(BLUE_VALUE);
        durablePencil.sharpen(new BasicSharpener(1));
        assertEquals(durablePencil.getPointDurability(), oldDurability);
    }

    public void whenPencilIsSharpenedAndLengthIsZeroItWillNotRegainInitialDurability() {
        Pencil pencil = BasicPencil.createWithDurabilityAndLength(50, 2);
        Sharpener sharpener = new BasicSharpener(1);
        pencil.sharpen(sharpener);
        pencil.sharpen(sharpener);
        pencil.write(BLUE_VALUE);
        pencil.sharpen(sharpener);
        assertTrue(pencil.getPointDurability() < 50);
    }

    public void whenPencilIsSharpenItWillNotSharpenIfSharperReduceLengthIsMoreThanPencilLength() {
        Pencil pencil = BasicPencil.createWithDurabilityAndLength(10, 5);
        int reduceLengthBy = 2;
        Sharpener sharpener = new BasicSharpener(reduceLengthBy);
        pencil.sharpen(sharpener);
        assertEquals(pencil.getLength(), 5 - reduceLengthBy);
        pencil.sharpen(sharpener);
        assertEquals(pencil.getLength(), 5 - reduceLengthBy - reduceLengthBy);
        pencil.sharpen(sharpener);
        assertEquals(pencil.getLength(), 5 - reduceLengthBy - reduceLengthBy);
    }

    public void whenPencilCreatedItShouldHaveEraser() {
        Eraser eraser = defaultPencil.getEraser();
        assertNotNull(eraser);
    }

    public void whenPencilAskedToSetEraserItCanReturnThatEraser() {
        int eraserDefaultDurability = defaultPencil.getEraser().getDurability();
        int eraserNewDurability = eraserDefaultDurability + 10;
        Eraser newEraser = new BasicEraser(eraserNewDurability, new BasicPage());
        defaultPencil.setEraser(newEraser);
        assertEquals(defaultPencil.getEraser().getDurability(), eraserNewDurability);
    }

    public void whenPencilIsRequestedToEraseTextItCanEraseIt() {
        defaultPencil.write("abcd ed");
        assertEquals(defaultPencil.erase("ed"), "abcd   ");
    }

    public void whenPencilCreatedItHasDefaultPage() {
        assertNotNull(defaultPencil.getPage());
    }

    public void whenPencilWritersItWriteToItsPage() {
        defaultPencil.write(BLUE_VALUE);
        String writtenText = defaultPencil.getPageTextContent();
        assertEquals(defaultPencil.getPage().getTextContents(), writtenText);
    }

    public void whenPencilEditItWritesCharacterStartingFromLastEraseIndex() {
        defaultPencil.write("An apple a day keeps the doctor away");
        defaultPencil.erase("apple");
        defaultPencil.edit("artichoke");
        assertEquals(defaultPencil.getPageTextContent(), "An artich@k@ay keeps the doctor away");
    }

    public void whenPencilWritesCollideCharacterItReducesDurabilityByOne() {
        Pencil pencil = BasicPencil.createWithDurability(10);
        pencil.write("ab@");
        assertEquals(pencil.getPointDurability(), 10 - 3);
    }

    public void whenPencilEditsWithCollideCharacterItReducesDurabilityByOne() {
        Pencil pencil = BasicPencil.createWithDurability(10);
        pencil.write("abc");
        pencil.erase("a");
        pencil.edit("zx");
        assertEquals(pencil.getPageTextContent(), "z@c");
        assertEquals(pencil.getPointDurability(), 10 - 5);
    }

    public void whenPencilEditsWithZeroDurabilityItLeaveCharactersAsIs() {
        Pencil pencil = BasicPencil.createWithDurability(4);
        pencil.write("abc");
        pencil.erase("ab");
        pencil.edit("zxy");
        assertEquals(pencil.getPageTextContent(), "z c");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void whenPencilEditsItThrowErrorIfNoCharactersWereErasedBefore() {
        defaultPencil.write("abcd");
        defaultPencil.erase("m");
        defaultPencil.edit("x");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void whenPencilEditsItThrowErrorIfItCalledAfterWriteWithoutErase() {
        defaultPencil.write("abcd");
        defaultPencil.erase("a");
        defaultPencil.write(" xy");
        defaultPencil.edit("a");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenPencilEditTextWithCharactersNumberExceedTheTotalLengthOfTextItThrowsError() {
        defaultPencil.write("abcd");
        defaultPencil.erase("c");
        defaultPencil.edit("This is a too long text");
    }
}
