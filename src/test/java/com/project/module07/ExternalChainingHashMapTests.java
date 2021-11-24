package com.project.module07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ExternalChainingHashMapTests {

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentExceptionNullKey() {
        ExternalChainingHashMap<Integer, String> actuals = new ExternalChainingHashMap<>();
        actuals.put(null, "entry1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalArgumentExceptionNullValue() {
        ExternalChainingHashMap<Integer, String> actuals = new ExternalChainingHashMap<>();
        actuals.put(5, null);
    }

    @Test
    public void testAddNoCollisions() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(5, 5);
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(11, 11);
        actuals.put(25, 25);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[5] = new ExternalChainingMapEntry<Integer,Integer>(5, 5);
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(6, 6);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[11] = new ExternalChainingMapEntry<Integer,Integer>(11, 11);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);

        assertArrayEquals(expecteds, actuals.getTable());
    }

    @Test
    public void testAddCollisions() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(5, 5);
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(11, 11);
        actuals.put(25, 25);
        actuals.put(19, 19);
        actuals.put(32, 32);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[5] = new ExternalChainingMapEntry<Integer,Integer>(5, 5);
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(32, 32);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[11] = new ExternalChainingMapEntry<Integer,Integer>(11, 11);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);
        expecteds[6].setNext(new ExternalChainingMapEntry<Integer,Integer>(19, 19));
        expecteds[6].getNext().setNext(new ExternalChainingMapEntry<Integer,Integer>(6, 6));

        assertArrayEquals(expecteds, actuals.getTable());
    }

    @Test
    public void testAddDuplicates() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(5, 5);
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(11, 11);
        actuals.put(25, 25);
        actuals.put(19, 19);
        actuals.put(32, 32);

        int returnValue = actuals.put(6, 7);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[5] = new ExternalChainingMapEntry<Integer,Integer>(5, 5);
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(32, 32);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[11] = new ExternalChainingMapEntry<Integer,Integer>(11, 11);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);
        expecteds[6].setNext(new ExternalChainingMapEntry<Integer,Integer>(19, 19));
        expecteds[6].getNext().setNext(new ExternalChainingMapEntry<Integer,Integer>(6, 7));

        assertArrayEquals(expecteds, actuals.getTable());
        assertEquals(6, returnValue);
    }

    @Test
    public void testAddDuplicatesResize() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(5, 5);
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(11, 11);
        actuals.put(25, 25);
        actuals.put(19, 19);
        actuals.put(32, 32);
        actuals.put(20, 20);
        actuals.put(4, 4);

        int capacity = 27;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[5] = new ExternalChainingMapEntry<Integer,Integer>(32, 32);
        expecteds[5].setNext(new ExternalChainingMapEntry<Integer,Integer>(5, 5));
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(6, 6);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[11] = new ExternalChainingMapEntry<Integer,Integer>(11, 11);
        expecteds[25] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);
        expecteds[19] = new ExternalChainingMapEntry<Integer,Integer>(19, 19);
        expecteds[20] = new ExternalChainingMapEntry<Integer,Integer>(20, 20);
        expecteds[4] = new ExternalChainingMapEntry<Integer,Integer>(4, 4);

        assertArrayEquals(expecteds, actuals.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgumentExceptionNullKey() {
        ExternalChainingHashMap<Integer, String> actuals = new ExternalChainingHashMap<>();
        actuals.remove(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveIllegalArgumentExceptionNullValue() {
        ExternalChainingHashMap<Integer, String> actuals = new ExternalChainingHashMap<>();
        actuals.remove(5);
    }

    @Test
    public void testRemoveNoChaining() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(4, 4);
        actuals.remove(4);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        assertArrayEquals(expecteds, actuals.getTable());
        assertEquals(0, actuals.size());

        // expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(32, 32);
        // expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        // expecteds[11] = new ExternalChainingMapEntry<Integer,Integer>(11, 11);
        // expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);
        // expecteds[6].setNext(new ExternalChainingMapEntry<Integer,Integer>(19, 19));
        // expecteds[6].getNext().setNext(new ExternalChainingMapEntry<Integer,Integer>(6, 6));
    }

    @Test
    public void testRemoveChainingHead() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(38, 38);
        actuals.put(25, 25);
        actuals.put(12, 12);
        actuals.remove(12);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(6, 6);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(25, 25);
        expecteds[12].setNext(new ExternalChainingMapEntry<Integer,Integer>(38, 38));

        assertArrayEquals(expecteds, actuals.getTable());
        assertEquals(4, actuals.size());
     }

     @Test
    public void testRemoveChainingMiddle() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(38, 38);
        actuals.put(25, 25);
        actuals.put(12, 12);
        actuals.remove(25);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(6, 6);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(12, 12);
        expecteds[12].setNext(new ExternalChainingMapEntry<Integer,Integer>(38, 38));

        assertArrayEquals(expecteds, actuals.getTable());
        assertEquals(4, actuals.size());
     }

     @Test
    public void testRemoveChainingTail() {
        ExternalChainingHashMap<Integer, Integer> actuals = new ExternalChainingHashMap<>();
        actuals.put(6, 6);
        actuals.put(8, 8);
        actuals.put(38, 38);
        actuals.put(25, 25);
        actuals.put(12, 12);
        actuals.remove(38);

        int capacity = 13;
        ExternalChainingMapEntry<Integer, Integer>[] expecteds = (ExternalChainingMapEntry<Integer, Integer>[]) new ExternalChainingMapEntry[capacity];
        expecteds[6] = new ExternalChainingMapEntry<Integer,Integer>(6, 6);
        expecteds[8] = new ExternalChainingMapEntry<Integer,Integer>(8, 8);
        expecteds[12] = new ExternalChainingMapEntry<Integer,Integer>(12, 12);
        expecteds[12].setNext(new ExternalChainingMapEntry<Integer,Integer>(25, 25));

        assertArrayEquals(expecteds, actuals.getTable());
        assertEquals(4, actuals.size());
     }
}
