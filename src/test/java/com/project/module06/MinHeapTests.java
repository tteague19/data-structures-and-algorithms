package com.project.module06;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MinHeapTests {

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentException() {
        MinHeap<Integer> actual = new MinHeap<>();

        actual.add(null);
    }

