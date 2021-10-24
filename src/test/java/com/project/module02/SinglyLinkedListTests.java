package com.project.module02;

import java.lang.IllegalArgumentException;

import org.junit.Test;

public class SinglyLinkedListTests {
    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontIllegalArgumentException() {
        SinglyLinkedList<Integer> actualSLList = new SinglyLinkedList<>();
        actualSLList.addToFront(null);
    }
}
