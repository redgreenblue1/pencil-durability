package com.kata.domain;

public class BasicSharpener implements Sharpener {
    private final int reduceLengthBy;

    public BasicSharpener(int reduceLengthBy) {
        this.reduceLengthBy = reduceLengthBy;
    }

    @Override
    public int getReduceLengthBy() {
        return reduceLengthBy;
    }
}
