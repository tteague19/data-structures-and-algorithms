package com.project.module02;

import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

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
        Assert.assertEquals(1, actualSLList.size());
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
        Assert.assertEquals(2, actualSLList.size());

        actualSLList.addToFront(testData3);
        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getTail().getData());
        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.getHead().getNext().getData());
        Assert.assertEquals(null, actualSLList.getTail().getNext());
        Assert.assertEquals(3, actualSLList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackIllegalArgumentException() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        actualSLList.addToBack(null);
    }

    @Test
    public void testAddToBackEmptySLL() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData = 3;

        actualSLList.addToBack(testData);

        Assert.assertEquals(Integer.valueOf(testData), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData), actualSLList.getTail().getData());
        Assert.assertEquals(null, actualSLList.getHead().getNext());
        Assert.assertEquals(null, actualSLList.getTail().getNext());
        Assert.assertEquals(1, actualSLList.size());
    }

    @Test
    public void testAddToBackMultipleElements() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData1 = 3;
        int testData2 = 8;
        int testData3 = 5;
        int testData4 = 1;
        int testData5 = 14;

        actualSLList.addToFront(testData1);
        actualSLList.addToFront(testData2);
        actualSLList.addToFront(testData3);

        actualSLList.addToBack(testData4);
        actualSLList.addToBack(testData5);

        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getTail().getData());
        Assert.assertEquals(Integer.valueOf(testData4),
                            actualSLList.getHead().getNext().getNext().getNext().getData());
        Assert.assertEquals(Integer.valueOf(testData4),
                            actualSLList.getHead().getNext().getNext().getNext().getData());

        Assert.assertEquals(Integer.valueOf(testData5),
                            actualSLList.getHead().getNext().getNext().getNext().getNext().getData());
        Assert.assertEquals(null, actualSLList.getTail().getNext());
        Assert.assertEquals(5, actualSLList.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontNoSuchElement() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int value = actualSLList.removeFromFront();
    }

    @Test
    public void testRemoveFromFront() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData1 = 1;
        int testData2 = 2;
        int testData3 = 3;
        int testData4 = 4;
        int testData5 = 5;
        int size = 5;

        actualSLList.addToBack(testData1);
        actualSLList.addToBack(testData2);
        actualSLList.addToBack(testData3);
        actualSLList.addToBack(testData4);
        actualSLList.addToBack(testData5);

        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.removeFromFront());
        size--;
        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.removeFromFront());
        size--;
        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.removeFromFront());
        size--;
        Assert.assertEquals(Integer.valueOf(testData4), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData4), actualSLList.removeFromFront());
        size--;
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.removeFromFront());
        size--;
        Assert.assertEquals(null, actualSLList.getHead());
        Assert.assertEquals(null, actualSLList.getTail());
        Assert.assertEquals(size, actualSLList.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackNoSuchElement() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int value = actualSLList.removeFromBack();
    }

    @Test
    public void testRemoveFromBack() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        int testData1 = 1;
        int testData2 = 2;
        int testData3 = 3;
        int testData4 = 4;
        int testData5 = 5;
        int size = 5;

        actualSLList.addToBack(testData1);
        actualSLList.addToBack(testData2);
        actualSLList.addToBack(testData3);
        actualSLList.addToBack(testData4);
        actualSLList.addToBack(testData5);

        Assert.assertEquals(Integer.valueOf(testData5), actualSLList.removeFromBack());
        size--;
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData4), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData4), actualSLList.removeFromBack());
        size--;
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData3), actualSLList.removeFromBack());
        size--;
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData2), actualSLList.removeFromBack());
        size--;
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getHead().getData());
        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.getTail().getData());
        Assert.assertEquals(size, actualSLList.size());

        Assert.assertEquals(Integer.valueOf(testData1), actualSLList.removeFromBack());
        size--;
        Assert.assertEquals(null, actualSLList.getHead());
        Assert.assertEquals(null, actualSLList.getTail());
        Assert.assertEquals(size, actualSLList.size());
    }
}
