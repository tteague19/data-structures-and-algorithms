package com.project.module06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class MinHeapTests {

    @Test
    public void testAdd() {
        MinHeap<Integer> actuals = new MinHeap<>();
        actuals.add(1);
        actuals.add(2);
        actuals.add(3);
        actuals.add(4);
        actuals.add(5);
        actuals.add(6);
        actuals.add(7);
        actuals.add(8);
        actuals.add(9);
        actuals.add(0);

        Integer[] expecteds =  new Integer[]{null, 0, 1, 3, 4, 2, 6, 7, 8, 9, 5, null, null};
        assertArrayEquals(expecteds, actuals.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentException() {
        MinHeap<Integer> actual = new MinHeap<>();

        actual.add(null);
    }

    @Test
    public void testAddSizeIncrement() {
        MinHeap<Integer> actuals = new MinHeap<>();
        actuals.add(1);
        actuals.add(2);
        actuals.add(3);
        actuals.add(4);
        actuals.add(5);
        actuals.add(6);
        actuals.add(7);
        actuals.add(8);
        actuals.add(9);
        actuals.add(0);

        assertEquals(10, actuals.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveElementFromEmptyHeap() {
        MinHeap<Integer> actuals = new MinHeap<>();

        int minVal = actuals.remove();
    }

    @Test
    public void testRemove() {
        MinHeap<Integer> actuals = new MinHeap<>();
        actuals.add(0);
        actuals.add(1);
        actuals.add(2);
        actuals.add(4);
        actuals.add(3);
        actuals.add(5);
        actuals.add(7);
        actuals.add(6);
        actuals.add(8);
        actuals.add(9);
        actuals.add(10);

        int minVal1 = actuals.remove();
        Integer[] expecteds1 =  new Integer[]{null, 1, 3, 2, 4, 9, 5, 7, 6, 8, 10, null, null};
        assertArrayEquals(expecteds1, actuals.getBackingArray());
        assertEquals(0, minVal1);


        int minVal2 = actuals.remove();
        Integer[] expecteds2 =  new Integer[]{null, 2, 3, 5, 4, 9, 10, 7, 6, 8, null, null, null};
        assertArrayEquals(expecteds2, actuals.getBackingArray());
        assertEquals(1, minVal2);
    }

    @Test
    public void testRemoveSizeDecrement() {
        MinHeap<Integer> actuals = new MinHeap<>();
        actuals.add(0);
        actuals.add(1);
        actuals.add(2);
        actuals.add(4);
        actuals.add(3);
        actuals.add(5);
        actuals.add(7);
        actuals.add(6);
        actuals.add(8);
        actuals.add(9);
        actuals.add(10);

        int minVal = actuals.remove();
        assertEquals(10, actuals.size());

        int minVal2 = actuals.remove();
        assertEquals(9, actuals.size());
    }
}
