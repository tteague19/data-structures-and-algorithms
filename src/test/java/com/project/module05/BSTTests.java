package com.project.module05;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BSTTests {
    @Test
    public void testAdd() {
        // Build the expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        BSTNode<Integer> node7 = new BSTNode<Integer>(25);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);
        node2.setRight(node7);

        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(25);
        tree.add(5);
        tree.add(10);

        // Check that we do not add duplicated data
        tree.add(10);

        assert(nodeEqualityHelper(node1, tree.getRoot()));
        assert(nodeEqualityHelper(node2, tree.getRoot().getLeft()));
        assert(nodeEqualityHelper(node3, tree.getRoot().getRight()));
        assert(nodeEqualityHelper(node4, tree.getRoot().getLeft().getLeft()));
        assert(nodeEqualityHelper(node7, tree.getRoot().getLeft().getRight()));
        assert(nodeEqualityHelper(node6, tree.getRoot().getLeft().getLeft().getRight()));
        assert(nodeEqualityHelper(node5, tree.getRoot().getRight().getRight()));
    }

    @Test
    public void testAddSizeUpdate() {
        // Build the expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        BSTNode<Integer> node7 = new BSTNode<Integer>(25);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);
        node2.setRight(node7);

        BST<Integer> tree = new BST<>();
        int expectedSize = 0;
        assertEquals(expectedSize, tree.size());

        tree.add(50);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(15);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(75);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(100);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(25);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(5);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        tree.add(10);
        expectedSize++;
        assertEquals(expectedSize, tree.size());

        // Check that we do not add duplicated data
        tree.add(10);
        assertEquals(expectedSize, tree.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullValue() {
        // Build the expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        BSTNode<Integer> node7 = new BSTNode<Integer>(25);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);
        node2.setRight(node7);

        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(25);
        tree.add(5);
        tree.add(10);

        tree.add(null);
    }


    public boolean nodeEqualityHelper(BSTNode<Integer> expected, BSTNode<Integer> actual) {
        boolean condition1 = (actual.getData() == expected.getData());
        boolean condition2;
        boolean condition3;

        if (actual.getLeft() != null && expected.getLeft() != null) {
            condition2 = (actual.getLeft().getData() == expected.getLeft().getData());
        } else {
            // In this case, both nodes must be either be null or the nodes are
            // not the same.
            condition2 = (actual.getLeft() == actual.getLeft());
        }

        if (actual.getRight() != null && expected.getRight() != null) {
            condition3 = (actual.getRight().getData() == expected.getRight().getData());
        } else {
            // In this case, both nodes must be either be null or the nodes are
            // not the same.
            condition3 = (actual.getRight() == actual.getRight());
        }

        return condition1 && condition2 && condition3;
    }

    @Test
    public void testRemoveNoChildren() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(10);

        int removedData = tree.remove(10);
        node4.setRight(null);
        assertEquals(removedData, 10);
        assert(nodeEqualityHelper(node4, tree.getRoot().getLeft().getLeft()));
    }

    @Test
    public void testRemoveOneChild() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(10);

        int removedData = tree.remove(5);
        node2.setLeft(node6);
        assertEquals(removedData, 5);
        assert(nodeEqualityHelper(node2, tree.getRoot().getLeft()));
        assert(nodeEqualityHelper(node6, tree.getRoot().getLeft().getLeft()));
    }

    @Test
    public void testRemoveTwoChildren() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(25);
        BSTNode<Integer> node7 = new BSTNode<Integer>(20);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node6);
        node3.setRight(node5);
        node6.setRight(node7);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(25);
        tree.add(20);

        int removedData = tree.remove(15);
        node1.setLeft(node7);
        node7.setLeft(node4);
        node7.setRight(node6);
        node6.setLeft(null);
        node6.setRight(null);
        assertEquals(removedData, 15);
        assert(nodeEqualityHelper(node1, tree.getRoot()));
        assert(nodeEqualityHelper(node7, tree.getRoot().getLeft()));
        assert(nodeEqualityHelper(node4, tree.getRoot().getLeft().getLeft()));
        assert(nodeEqualityHelper(node6, tree.getRoot().getLeft().getRight()));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNotInTree() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(10);

        int removedData = tree.remove(19);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmptyTree() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();

        int removedData = tree.remove(19);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullValue() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(10);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node3.setRight(node5);
        node4.setRight(node6);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(10);

        int removedData = tree.remove(null);
    }

    @Test
    public void testRemoveSizeUpdate() {
        // Build the initial expected tree
        BSTNode<Integer> node1 = new BSTNode<Integer>(50);
        BSTNode<Integer> node2 = new BSTNode<Integer>(15);
        BSTNode<Integer> node3 = new BSTNode<Integer>(75);
        BSTNode<Integer> node4 = new BSTNode<Integer>(5);
        BSTNode<Integer> node5 = new BSTNode<Integer>(100);
        BSTNode<Integer> node6 = new BSTNode<Integer>(25);
        BSTNode<Integer> node7 = new BSTNode<Integer>(20);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node6);
        node3.setRight(node5);
        node6.setRight(node7);

        // Build the initial expected tree
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(75);
        tree.add(100);
        tree.add(5);
        tree.add(25);
        tree.add(20);
        int expectedSize = 7;

        int removedData1 = tree.remove(15);
        expectedSize--;
        assertEquals(expectedSize, tree.size());

        int removedData2 = tree.remove(75);
        expectedSize--;
        assertEquals(expectedSize, tree.size());

        int removedData3 = tree.remove(25);
        expectedSize--;
        assertEquals(expectedSize, tree.size());
    }
}
