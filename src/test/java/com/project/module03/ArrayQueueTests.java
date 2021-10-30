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
    public void testEnqueueWrappingNoResize() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected = new Integer[]{9, 10, 11, 12, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
        }

        for (int num = arrayCapacity; num < (arrayCapacity + maxIndex); num++) {
            actuallArrayQueue.enqueue(num);
        }
        assertArrayEquals(expected, expected);
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

    @Test
    public void testEnqueueWrappingResize() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected1 = new Integer[]{9, 10, 11, 12, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
        }

        for (int num = arrayCapacity; num < (arrayCapacity + maxIndex); num++) {
            actuallArrayQueue.enqueue(num);
        }
        assertArrayEquals(expected1, actuallArrayQueue.getBackingArray());

        actuallArrayQueue.enqueue(arrayCapacity + maxIndex);
        Integer[] expected2 = new Integer[]{
            4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            null, null, null, null, null, null, null, null};

        assertArrayEquals(expected2, actuallArrayQueue.getBackingArray());
    }

    @Test
    public void testEnqueueAfterDequeueingAllItemsNoResize() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected1 = new Integer[]{9, 10, 11, 12, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
        }

        for (int num = arrayCapacity; num < (arrayCapacity + maxIndex); num++) {
            actuallArrayQueue.enqueue(num);
        }
        assertArrayEquals(expected1, actuallArrayQueue.getBackingArray());

        for (int val = maxIndex; val < (maxIndex + arrayCapacity); val++) {
            assertEquals(Integer.valueOf(val), actuallArrayQueue.dequeue());
        }

        actuallArrayQueue.enqueue(100);
        Integer[] expected2 = new Integer[]{null, null, null, null, 100, null, null, null, null};
        assertArrayEquals(expected2, actuallArrayQueue.getBackingArray());
        assertEquals(1, actuallArrayQueue.size());
    }

    @Test
    public void testEnqueueAfterDequeueingAllItemsResize() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected1 = new Integer[]{9, 10, 11, 12, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
        }

        for (int num = arrayCapacity; num < (arrayCapacity + maxIndex); num++) {
            actuallArrayQueue.enqueue(num);
        }
        assertArrayEquals(expected1, actuallArrayQueue.getBackingArray());

        actuallArrayQueue.enqueue(13);

        Integer[] expected2 = new Integer[]{
            4, 5, 6, 7, 8, 9, 10, 11, 12,
            13, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected2, actuallArrayQueue.getBackingArray());

        for (int val = maxIndex; val < (arrayCapacity + maxIndex + 1); val++) {
            assertEquals(Integer.valueOf(val), actuallArrayQueue.dequeue());
        }

        actuallArrayQueue.enqueue(100);
        Integer[] expected3 = new Integer[]{
            null, null, null, null, null, null, null, null, null,
            null, 100, null, null, null, null, null, null, null};
        assertArrayEquals(expected3, actuallArrayQueue.getBackingArray());
    }

    @Test
    public void testDequeueNoWrapping() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
            expected[index] = null;
            assertArrayEquals(expected, actuallArrayQueue.getBackingArray());
            assertEquals(arrayCapacity - index - 1, actuallArrayQueue.size());
        }

        for (int index = maxIndex; index < arrayCapacity; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
            expected[index] = null;
            assertArrayEquals(expected, actuallArrayQueue.getBackingArray());
            assertEquals(arrayCapacity - index - 1, actuallArrayQueue.size());
        }
    }

    @Test
    public void testDequeueWrapping() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;
        Integer[] expected1 = new Integer[]{9, 10, 11, 12, 4, 5, 6, 7, 8};

        for (int num = 0; num < arrayCapacity; num++) {
            actuallArrayQueue.enqueue(num);
        }

        int maxIndex = arrayCapacity / 2;
        for (int index = 0; index < maxIndex; index++) {
            assertEquals(Integer.valueOf(index), actuallArrayQueue.dequeue());
        }

        for (int num = arrayCapacity; num < (arrayCapacity + maxIndex); num++) {
            actuallArrayQueue.enqueue(num);
        }
        assertArrayEquals(expected1, actuallArrayQueue.getBackingArray());

        for (int val = maxIndex; val < (maxIndex + arrayCapacity); val++) {
            assertEquals(Integer.valueOf(val), actuallArrayQueue.dequeue());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmptyQueue() {
        ArrayQueue<Integer> actuallArrayQueue = new ArrayQueue<>();
        int arrayCapacity = 9;

        for (int num = 0; num <= arrayCapacity; num++) {
            actuallArrayQueue.dequeue();
        }
    }
}
