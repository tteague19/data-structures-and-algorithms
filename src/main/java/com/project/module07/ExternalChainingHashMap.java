package com.project.module07;

import java.util.NoSuchElementException;
import java.lang.Math;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        int hashIndex = Math.abs(key.hashCode() % this.table.length);

        // First, we check if the value is a duplicate.
        // If we find a duplicate, then we replace the value at that
        // key and return the old value.
        if (this.table[hashIndex] != null) {
            ExternalChainingMapEntry<K, V> current = this.table[hashIndex];
            while(current != null) {
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                current = current.getNext();
            }
        }

        // At this stage, the key and value are valid and the key is
        // not a duplicate. Thus, we are certain to increase the size
        // of the hash map. We first check if we should resize in order
        // to meet the criteria of the load factor.
        if ((double)(this.size + 1) / this.table.length > this.MAX_LOAD_FACTOR) {
            this.resizeBackingTable(2 * this.table.length + 1);
        }

        // Finally, we add the new element to the hash map via
        // external chaining.
        ExternalChainingMapEntry<K, V> newEntry = new ExternalChainingMapEntry(key, value);
        if (this.table[hashIndex] != null) {
            newEntry.setNext(this.table[hashIndex]);
        }
        this.table[hashIndex] = newEntry;

        this.size++;
        return null;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int hashIndex = Math.abs(key.hashCode() % this.table.length);
        V value = null;
        if (this.table[hashIndex] != null) {
            ExternalChainingMapEntry<K, V> current = this.table[hashIndex];
            ExternalChainingMapEntry<K, V> previous = null;
            // We search the linked list at this location until we
            // find an item with the specified key.
            while(current != null) {
                if (current.getKey().equals(key)) {
                    value = current.getValue();

                    // If previous is not null, then an element that
                    // is not the head of the list contains the
                    // specified key.
                    if (previous != null) {
                        previous.setNext(current.getNext());
                        current.setNext(null);
                    // If the head of the list does contain the
                    // specified key, then we check if it is the only
                    // item in the linked list. If it is not, then we
                    // rewire the pointers appropriately. If it is,
                    // then we simply set the index to null.
                    } else if (current.getNext() != null) {
                        this.table[hashIndex] = current.getNext();
                        current.setNext(null);
                    } else {
                        this.table[hashIndex] = null;
                    }
                }
                previous = current;
                current = current.getNext();
            }
        }

        // If we have not assigned a new value to the variable "value",
        // then the key does not exist. Therefore, throw an
        // exception. Note that this scenario also arises when we
        // try to remove a key at an index with a null entry.
        if (value == null) {
            throw new NoSuchElementException();
        }

        this.size--;
        return value;
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K, V>[] newBackingArray = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        // We iterate over all the indices in the current hash table.
        for (ExternalChainingMapEntry<K, V> entry : this.table) {

            // We first check to see if the hash table has a value at
            // the current index.
            if (entry != null) {

                // Then, we traverse the linked list at the given index.
                ExternalChainingMapEntry<K, V> current = entry;
                while (current != null) {
                    int hashIndex = Math.abs(current.getKey().hashCode() % length);
                    ExternalChainingMapEntry<K, V> newEntry = new ExternalChainingMapEntry(current.getKey(), current.getValue());

                    // If there is already an entry at this location
                    // in the new hash table, we add the new entry at
                    // the head of that list.
                    if (newBackingArray[hashIndex] != null) {
                        newEntry.setNext(newBackingArray[hashIndex]);
                    }
                    newBackingArray[hashIndex] = newEntry;
                    current = current.getNext();
                }
            }
        }

        this.table = newBackingArray;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
