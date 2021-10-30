package com.project.module03;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class ArrayQueueTests {
    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueIllegalArgumentException() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        actuallArrayQueue.enqueue(null);
    }
}
