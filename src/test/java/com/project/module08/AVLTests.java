package com.project.module08;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;


public class AVLTests {

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullData() {
        AVL<Integer> avl = new AVL();
        avl.add(null);
    }

    @Test
    public void testAdd() {
        AVL<Integer> avl = new AVL();
        avl.add(2);
        avl.add(0);
        avl.add(7);
        avl.add(1);
        avl.add(4);
        avl.add(8);
        avl.add(3);
        avl.add(6);
        avl.add(0);

        assertEquals(Integer.valueOf(2), avl.getRoot().getData());
        assertEquals(3, avl.getRoot().getHeight());
        assertEquals(-1, avl.getRoot().getBalanceFactor());

        AVLNode<Integer> testNode = avl.getRoot().getLeft();
        assertEquals(Integer.valueOf(0), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft().getRight();
        assertEquals(Integer.valueOf(1), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight();
        assertEquals(Integer.valueOf(7), testNode.getData());
        assertEquals(2, testNode.getHeight());
        assertEquals(1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft();
        assertEquals(Integer.valueOf(4), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight();
        assertEquals(Integer.valueOf(8), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft().getLeft();
        assertEquals(Integer.valueOf(3), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());


        testNode = avl.getRoot().getRight().getLeft().getRight();
        assertEquals(Integer.valueOf(6), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        assertEquals(8, avl.size());

        // Finally, we add a node that will require a rebalancing.
        avl.add(5);

        testNode = avl.getRoot().getRight();
        assertEquals(Integer.valueOf(6), testNode.getData());
        assertEquals(2, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft();
        assertEquals(Integer.valueOf(4), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight();
        assertEquals(Integer.valueOf(7), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft().getLeft();
        assertEquals(Integer.valueOf(3), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft().getRight();
        assertEquals(Integer.valueOf(5), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight().getRight();
        assertEquals(Integer.valueOf(8), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        assertEquals(9, avl.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullData() {
        AVL<Integer> avl = new AVL();
        avl.add(null);
    }

    @Test
    public void testRemove() {
        AVL<Integer> avl = new AVL();
        avl.add(2);
        avl.add(0);
        avl.add(4);
        avl.add(1);
        avl.add(3);
        avl.add(6);
        avl.add(5);

        AVLNode<Integer> testNode = avl.getRoot();
        assertEquals(Integer.valueOf(2), testNode.getData());
        assertEquals(3, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft();
        assertEquals(Integer.valueOf(0), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft().getRight();
        assertEquals(Integer.valueOf(1), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight();
        assertEquals(Integer.valueOf(4), testNode.getData());
        assertEquals(2, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft();
        assertEquals(Integer.valueOf(3), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight();
        assertEquals(Integer.valueOf(6), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight().getLeft();
        assertEquals(Integer.valueOf(5), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        assertEquals(7, avl.size());

        // Finally, we add a node that will require a rebalancing.
        avl.remove(4);

        testNode = avl.getRoot();
        assertEquals(Integer.valueOf(2), testNode.getData());
        assertEquals(2, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft();
        assertEquals(Integer.valueOf(0), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft().getRight();
        assertEquals(Integer.valueOf(1), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight();
        assertEquals(Integer.valueOf(5), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft();
        assertEquals(Integer.valueOf(3), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight();
        assertEquals(Integer.valueOf(6), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        assertEquals(6, avl.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveDataNotPresent() {AVL<Integer> avl = new AVL();
        avl.add(2);
        avl.add(0);
        avl.add(4);
        avl.add(1);
        avl.add(3);
        avl.add(6);
        avl.add(5);

        AVLNode<Integer> testNode = avl.getRoot();
        assertEquals(Integer.valueOf(2), testNode.getData());
        assertEquals(3, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft();
        assertEquals(Integer.valueOf(0), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getLeft().getRight();
        assertEquals(Integer.valueOf(1), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight();
        assertEquals(Integer.valueOf(4), testNode.getData());
        assertEquals(2, testNode.getHeight());
        assertEquals(-1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getLeft();
        assertEquals(Integer.valueOf(3), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight();
        assertEquals(Integer.valueOf(6), testNode.getData());
        assertEquals(1, testNode.getHeight());
        assertEquals(1, testNode.getBalanceFactor());

        testNode = avl.getRoot().getRight().getRight().getLeft();
        assertEquals(Integer.valueOf(5), testNode.getData());
        assertEquals(0, testNode.getHeight());
        assertEquals(0, testNode.getBalanceFactor());

        assertEquals(7, avl.size());

        avl.remove(19);
    }
}
