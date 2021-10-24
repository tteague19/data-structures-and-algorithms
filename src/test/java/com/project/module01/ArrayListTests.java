package com.project.module01;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class ArrayListTests {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToFront(null);
    }

    @Test
    public void testAddToFrontNoResize() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToFront(3);
        actualArrayList.addToFront(8);
        actualArrayList.addToFront(5);

        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 5;
        expected[1] = 8;
        expected[2] = 3;
        assertArrayEquals(expected, actualArrayList.getBackingArray());
    }

    @Test
    public void testAddToFrontWithResize() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToFront(3);
        actualArrayList.addToFront(8);
        actualArrayList.addToFront(5);
        actualArrayList.addToFront(42);
        actualArrayList.addToFront(23);
        actualArrayList.addToFront(16);
        actualArrayList.addToFront(15);
        actualArrayList.addToFront(8);
        actualArrayList.addToFront(4);
        actualArrayList.addToFront(19);

        Integer[] expected = new Integer[]{19,4,8,15,16,23,42,5,8,3,null,null,null,null,null,null,null,null};
        assertArrayEquals(expected, actualArrayList.getBackingArray());

        int size = actualArrayList.size();
        assertEquals(10, size);
    }

    @Test
    public void testAddToBackWithResize() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToBack(3);
        actualArrayList.addToBack(8);
        actualArrayList.addToBack(5);
        actualArrayList.addToBack(42);
        actualArrayList.addToBack(23);
        actualArrayList.addToBack(16);
        actualArrayList.addToBack(15);
        actualArrayList.addToBack(8);
        actualArrayList.addToBack(4);
        actualArrayList.addToBack(19);

        Integer[] expected = new Integer[]{3,8,5,42,23,16,15,8,4,19,null,null,null,null,null,null,null,null};
        assertArrayEquals(expected, actualArrayList.getBackingArray());

        int size = actualArrayList.size();
        assertEquals(10, size);
    }

    @Test
    public void testAddToBackNoResize() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToFront(3);
        actualArrayList.addToFront(8);
        actualArrayList.addToFront(5);
        actualArrayList.addToBack(1);
        actualArrayList.addToBack(14);

        Integer[] expected = new Integer[]{5,8,3,1,14,null,null,null,null};

        assertArrayEquals(expected, actualArrayList.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackIllegalArgumentException() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToBack(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontNoSuchElementException() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        int front = actualArrayList.removeFromFront();
    }

    @Test
    public void testRemoveFromFront() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToBack(0);
        actualArrayList.addToBack(1);
        actualArrayList.addToBack(2);
        actualArrayList.addToBack(3);
        actualArrayList.addToBack(4);
        actualArrayList.addToBack(5);
        actualArrayList.addToBack(6);
        actualArrayList.addToBack(7);
        actualArrayList.addToBack(8);

        assertEquals(Integer.valueOf(0), actualArrayList.removeFromFront());
        assertEquals(Integer.valueOf(1), actualArrayList.removeFromFront());
        assertEquals(Integer.valueOf(2), actualArrayList.removeFromFront());

        Integer[] expected = new Integer[]{3,4,5,6,7,8,null,null,null};
        assertArrayEquals(expected, actualArrayList.getBackingArray());
        assertEquals(6, actualArrayList.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackNoSuchElementException() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        int back = actualArrayList.removeFromBack();
    }

    @Test
    public void testRemoveFromBack() {
        ArrayList<Integer> actualArrayList = new ArrayList<>();
        actualArrayList.addToBack(0);
        actualArrayList.addToBack(1);
        actualArrayList.addToBack(2);
        actualArrayList.addToBack(3);
        actualArrayList.addToBack(4);
        actualArrayList.addToBack(5);
        actualArrayList.addToBack(6);
        actualArrayList.addToBack(7);
        actualArrayList.addToBack(8);

        assertEquals(Integer.valueOf(0), actualArrayList.removeFromFront());
        assertEquals(Integer.valueOf(1), actualArrayList.removeFromFront());
        assertEquals(Integer.valueOf(2), actualArrayList.removeFromFront());
        assertEquals(Integer.valueOf(8), actualArrayList.removeFromBack());
        assertEquals(Integer.valueOf(7), actualArrayList.removeFromBack());

        Integer[] expected = new Integer[]{3,4,5,6,null,null,null,null,null};
        assertArrayEquals(expected, actualArrayList.getBackingArray());
        assertEquals(4, actualArrayList.size());
    }
}

