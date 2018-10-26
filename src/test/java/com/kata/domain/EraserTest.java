package com.kata.domain;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class EraserTest {
    public void whenEraserCreatedItHasPositiveDurability() {
        Eraser eraser = new Eraser();
        assertTrue(eraser.getDurability() > 0);
    }
}
