package com.kata.domain;


public class Eraser {
    private int durability;

    public Eraser() {
        this(10);
    }

    public Eraser(int durability) {
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }


}
