package com.project.module02;

import java.lang.IllegalArgumentException;

import org.junit.Assert;
import org.junit.Test;

public class SinglyLinkedListTests {
    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontIllegalArgumentException() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        actualSLList.addToFront(null);
    }

    @Test
    public void testAddToFrontEmptySLL() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData = 3;

        actualSLList.addToFront(testData);

        Assert.assertEquals(Integer.valueOf(testData), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData), actualSLList.getTail().getData());
        Assert.assertEquals(null, actualSLList.getHead().getNext());
        Assert.assertEquals(null, actualSLList.getTail().getNext());
    }

    @Test
    public void testAddToFrontMultipleElements() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData1 = 3;
        int testData2 = 8;
        int testData3 = 5;

        actualSLList.addToFront(testData1);
        actualSLList.addToFront(testData2);

        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getTail().getData());
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getHead().getNext().getData());
        Assert.assertEquals(null, actualSLList.getTail().getNext());

        actualSLList.addToFront(testData3);
        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getTail().getData());
        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.getHead().getNext().getData());
        Assert.assertEquals(null, actualSLList.getTail().getNext());
    }
}
