package com.kata.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.kata.domain.Pencil.DEFAULT_LENGTH;
import static org.testng.Assert.*;

@Test
public class PencilTest {
    public static final String BLUE_VALUE = "blue";
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
        String firstText = BLUE_VALUE;
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
        String oldText = durablePencil.write(BLUE_VALUE);
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
        Pencil pencil = Pencil.createWithDurabilityAndLength(50, 2);
        Sharpener sharpener = new BasicSharpener(1);
        pencil.sharpen(sharpener);
        pencil.sharpen(sharpener);
        pencil.write(BLUE_VALUE);
        pencil.sharpen(sharpener);
        assertTrue(pencil.getPointDurability() < 50);
    }

    public void whenPencilIsSharpenItWillNotSharpenIfSharperReduceLengthIsMoreThanPencilLength() {
        Pencil pencil = Pencil.createWithDurabilityAndLength(10, 5);
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
        Eraser newEraser = new Eraser(eraserNewDurability, new Page());
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
        String writtenText = defaultPencil.write(BLUE_VALUE);
        assertEquals(defaultPencil.getPage().getTextContents(), writtenText);
    }

    public void whenPencilEditItWritesCharacterStartingFromLastEraseIndex() {
        defaultPencil.write("An apple a day keeps the doctor away");
        defaultPencil.erase("apple");
        String updatedText = defaultPencil.edit("artichoke");
        assertEquals(updatedText, "An artich@k@ay keeps the doctor away");
    }

    public void whenPencilWritesCollideCharacterItReducesDurabilityByOne() {
        Pencil pencil = Pencil.createWithDurability(10);
        pencil.write("ab@");
        assertEquals(pencil.getPointDurability(), 10 - 3);
    }

    public void whenPencilEditsWithCollideCharacterItReducesDurabilityByOne() {
        Pencil pencil = Pencil.createWithDurability(10);
        pencil.write("abc");
        pencil.erase("a");
        assertEquals(pencil.edit("zx"), "z@c");
        assertEquals(pencil.getPointDurability(), 10 - 5);
    }

    public void whenPencilEditsWithZeroDurabilityItLeaveCharactersAsIs() {
        Pencil pencil = Pencil.createWithDurability(4);
        pencil.write("abc");
        pencil.erase("ab");
        assertEquals(pencil.edit("zxy"), "z c");
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
