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

}
