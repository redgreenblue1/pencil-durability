package com.kata.domain;

public class Pencil {


    public static final char ONE_SPACE = ' ';
    public static final int DEFAULT_DURABILITY_POINT = 500;
    public static final int DEFAULT_LENGTH = 4;
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
        pencil.page = new Page();
        pencil.durabilityPoint = durability;
        pencil.initialDurability = durability;
        pencil.length = length;
        pencil.eraser = new Eraser(pencil.getPage());
        return pencil;
    }

    public String write(String textToWrite) {
        if (textToWrite != null) {
            char[] inputTextCharacters = textToWrite.toCharArray();
            for (char inputCharacter : inputTextCharacters) {
                adjustDurability(inputCharacter);
                appendText(inputCharacter);
            }
        }
        return page.getTextContents();
    }

    public String edit(String textToEdit) {
        int editLocation = page.getIndexOfLastErasedCharacter()
                .orElseThrow(() -> new IllegalStateException("Can't Edit unless text was erased"));
        if (editLocation + textToEdit.length() > page.getTextContents().length()) {
            throw new IllegalArgumentException("The text to edit length exceeds the total length of the page text");
        }
        for (char characterToEdit : textToEdit.toCharArray()) {
            if (canEditText()) {
                char updatedCharacter = page.editCharacter(characterToEdit, editLocation++);
                adjustDurability(updatedCharacter);
            }
        }
        return getPage().getTextContents();
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
        return Character.isLowerCase(inputCharacter) || inputCharacter == page.getCollideCharacter();
    }

    protected void appendText(char inputCharacter) {
        if (canWriteText()) {
            page.append(inputCharacter);
        } else {
            page.append(ONE_SPACE);
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

    public void sharpen() {
        if (getLength() > 0) {
            this.length--;
            this.durabilityPoint = getInitialDurability();
        }
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

}
