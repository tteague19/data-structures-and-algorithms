package com.project.module03;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void testEnqueueNoWrapping() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
            assertEquals(num + 1, actuallArrayQueue.size());
        }

        assertArrayEquals(expected, actuallArrayQueue.getBackingArray());
    }

    @Test
    public void testEnqueueResizeNoWrapping() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 10;
        Integer[] expected = new Integer[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, null, null, null, null, null, null, null, null};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
            assertEquals(num + 1, actuallArrayQueue.size());
        }

        assertArrayEquals(expected, actuallArrayQueue.getBackingArray());
    }
}
