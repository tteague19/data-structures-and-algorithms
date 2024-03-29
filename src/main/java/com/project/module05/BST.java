package com.project.module05;

import java.util.Objects;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (Objects.equals(data, null)) {
            throw new IllegalArgumentException();
        }

        this.root = addHelper(data, this.root);
    }

    private BSTNode<T> addHelper(T data, BSTNode<T> current) {

        if (Objects.equals(current, null)) {
            this.size++;
            return new BSTNode<T>(data);
        }
        if (data.compareTo(current.getData()) > 0) {
            current.setRight(addHelper(data, current.getRight()));
            return current;
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
            return current;
        }

        // If a node with this value is already in the BST, do not
        // add it.
        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (Objects.equals(data, null)) {
            throw new IllegalArgumentException();
        }

        BSTNode<T> dummy = new BSTNode<>(null);
        this.root = removeHelper(data, this.root, dummy);

        if (Objects.equals(dummy.getData(), null)) {
            throw new NoSuchElementException();
        }

        this.size--;
        return dummy.getData();
    }

    private BSTNode<T> removeHelper(T data, BSTNode<T> current, BSTNode<T> dummy) {
        if (Objects.equals(current, null)) {
            return null;
        } else if (current.getData().equals(data)) {
            dummy.setData(current.getData());

            int numChildren = countNumberOfChildren(current);
            if (Objects.equals(numChildren, 0)) {
                return null;
            } else if (Objects.equals(numChildren, 1)) {
                if (!Objects.equals(current.getLeft(), null)) {
                    return current.getLeft();
                } else {
                    return current.getRight();
                }
            } else if (Objects.equals(numChildren, 2)) {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                BSTNode<T> successor = findSuccessor(current.getRight(), dummy2);
                current.setRight(successor);
                current.setData(dummy2.getData());
            }

        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dummy));
        } else {
            current.setRight(removeHelper(data, current.getRight(), dummy));
        }

        return current;
    }

    private int countNumberOfChildren(BSTNode<T> node) {
        int numChildren = 0;

        if (!Objects.equals(node.getLeft(), null)) {
            numChildren++;
        }

        if (!Objects.equals(node.getRight(), null)) {
            numChildren++;
        }

        return numChildren;
    }

    private BSTNode<T> findSuccessor(BSTNode<T> current, BSTNode<T> dummy) {
        if (Objects.equals(current.getLeft(), null)) {
            dummy.setData(current.getData());
            return current.getRight();
        }

        current.setLeft(findSuccessor(current.getLeft(), dummy));

        return current;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
