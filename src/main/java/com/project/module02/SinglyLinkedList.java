package com.project.module02;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        SinglyLinkedListNode<T> frontNode = new SinglyLinkedListNode<>(data);

        // In this case, the SLL is empty. Also note that the next
        // pointer of frontNode is null by default.
        if (this.head == null) {
            this.head = frontNode;
            this.tail = frontNode;
        } else {
            frontNode.setNext(this.head);
            this.head = frontNode;
        }

        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        SinglyLinkedListNode<T> backNode = new SinglyLinkedListNode<>(data);

        // In this case, the SLL is empty. Aslo note that the next
        // pointer of backNode is null by default.
        if (this.head == null) {
            this.head = backNode;
            this.tail = backNode;
        } else {
            this.tail.setNext(backNode);
            this.tail = backNode;
        }

        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        // This case occurs if we have an empty list.
        if (this.head == null) {
            throw new NoSuchElementException();
        }

        SinglyLinkedListNode<T> headTemp = this.head;
        this.head = this.head.getNext();
        headTemp.setNext(null);

        // This case occurs if we had one element in the list.
        if (this.tail == headTemp) {
            this.tail = null;
        }

        // We decrement the size after removing one element from the
        // list.
        size--;

        // Finally, we return the data from the element that used to
        // be at the front of the list.
        return headTemp.getData();
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // This case occurs if we have an empty list.
        if (this.tail == null) {
            throw new NoSuchElementException();
        }

        SinglyLinkedListNode<T> tailTemp = this.tail;

        // This case occurs if we had one element in the list.
        if (this.head == tailTemp) {
            this.head = null;
            this.tail = null;
        } else {
            SinglyLinkedListNode<T> current = this.head;
            // We move down the list until we reach the node whose next
            // pointer is the current tail node.
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }
            this.tail = current;
            current.setNext(null);
        }

        // We decrement the size after removing one element from the
        // list.
        size--;

        return tailTemp.getData();
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}