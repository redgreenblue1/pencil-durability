package com.kata.domain;

public class Pencil {


    static final int DEFAULT_LENGTH = 4;
    private static final char ONE_SPACE = ' ';
    private static final int DEFAULT_DURABILITY_POINT = 500;
    private int durabilityPoint;
    private Page page;
    private int length;
    private int initialDurability;
    private Eraser eraser;

    private Pencil() {
        // elect to use builders instead
    }

    public static Pencil createDefault() {
        return createWithDurability(DEFAULT_DURABILITY_POINT);
    }

    public static Pencil createWithDurability(int durabilityPoint) {
        return createWithDurabilityAndLength(durabilityPoint, DEFAULT_LENGTH);
    }

    public static Pencil createWithDurabilityAndLength(int durability, int length) {
        Pencil pencil = new Pencil();
        pencil.page = new BasicPage();
        pencil.durabilityPoint = durability;
        pencil.initialDurability = durability;
        pencil.length = length;
        pencil.eraser = new Eraser(pencil.getPage());
        return pencil;
    }

    public void write(String textToWrite) {
        if (textToWrite != null) {
            for (char inputCharacter : textToWrite.toCharArray()) {
                adjustDurability(inputCharacter);
                appendCharacter(inputCharacter);
            }
        }
    }

    public void edit(String textToEdit) {
        int editStartIndex = getEditStartIndex(textToEdit);
        for (char characterToEdit : textToEdit.toCharArray()) {
            if (canEditText()) {
                char updatedCharacter = getPage().editCharacterAt(characterToEdit, editStartIndex++);
                adjustDurability(updatedCharacter);
            }
        }
    }

    private int getEditStartIndex(String textToEdit) {
        int editLocation = getPage().getIndexOfLastErasedCharacter()
                .orElseThrow(() -> new IllegalStateException("Can't Edit unless text was erased"));
        if (isEditTextOverflowPageText(textToEdit, editLocation)) {
            throw new IllegalArgumentException("The text to edit length exceeds the total length of the page text");
        }
        return editLocation;
    }

    private boolean isEditTextOverflowPageText(String textToEdit, int editLocation) {
        return editLocation + textToEdit.length() > getPage().getTextContents().length();
    }

    public void sharpen(Sharpener sharpener) {
        if (getLength() >= sharpener.getReduceLengthBy()) {
            this.length -= sharpener.getReduceLengthBy();
            this.durabilityPoint = getInitialDurability();
        }
    }

    private boolean canEditText() {
        return getPointDurability() > 0;
    }

    protected void adjustDurability(char inputCharacter) {
        if (isReduceDurabilityByOne(inputCharacter)) {
            durabilityPoint--;
        } else if (isReduceDurabilityByTwo(inputCharacter)) {
            durabilityPoint -= 2;
        }
    }

    private boolean isReduceDurabilityByTwo(char inputCharacter) {
        return Character.isUpperCase(inputCharacter);
    }

    protected boolean isReduceDurabilityByOne(char inputCharacter) {
        return Character.isLowerCase(inputCharacter) || inputCharacter == getPage().getCollideCharacter();
    }

    protected void appendCharacter(char inputCharacter) {
        if (canWriteText()) {
            getPage().append(inputCharacter);
        } else {
            getPage().append(ONE_SPACE);
        }
    }

    private boolean canWriteText() {
        return getPointDurability() >= 0;
    }

    public int getPointDurability() {
        return durabilityPoint;
    }

    public int getLength() {
        return this.length;
    }

    public int getInitialDurability() {
        return this.initialDurability;
    }

    public Eraser getEraser() {
        return this.eraser;
    }

    public void setEraser(Eraser eraser) {
        this.eraser = eraser;
    }

    public String erase(String textToErase) {
        return getEraser().erase(textToErase);
    }

    public Page getPage() {
        return this.page;
    }

    public String getPageTextContent() {
        return getPage().getTextContents();
    }

}
