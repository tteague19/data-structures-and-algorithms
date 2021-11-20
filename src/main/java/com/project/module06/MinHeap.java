package com.project.module06;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // In this case, we are adding to an empty array

        // Resize the array, if necessary. We account for the empty
        // space at the first index of the array.
        if (this.size == (this.backingArray.length - 1)) {
            T[] newBackingArray = (T[]) new Comparable[2 * this.backingArray.length];
            for (int index = 1; index <= this.size; index++) {
                newBackingArray[index] = this.backingArray[index];
            }
            this.backingArray = newBackingArray;
        }

        // We first place the new entry after the element in the
        // backing array that was previously in the final position.
        // This operation is equivalent to placing the new entry in a
        // node on the bottom level of the tree filled left to right.
        this.size++;
        this.backingArray[this.size] = data;

        // Now, we perform the "heapify up" operation.
        for (int index = this.size; index / 2 > 0; index = index / 2) {
            T parentData = this.backingArray[index / 2];
            T childData = this.backingArray[index];

            if (childData.compareTo(parentData) < 0) {
                this.backingArray[index] = parentData;
                this.backingArray[index / 2] = childData;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
