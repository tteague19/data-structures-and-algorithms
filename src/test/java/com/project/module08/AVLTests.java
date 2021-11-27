package com.project.module08;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AVLTests {
    @Test
    public void testUpdateHeightAndBalanceFactorLeftHeight() {
        AVLNode<Integer> parent = new AVLNode<Integer>(5);
        AVLNode<Integer> leftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> rightChild = new AVLNode<Integer>(2);

        parent.setLeft(leftChild);
        parent.setRight(rightChild);

        leftChild.setBalanceFactor(-1);
        rightChild.setBalanceFactor(1);

        leftChild.setHeight(3);
        rightChild.setHeight(1);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(parent);

        assertEquals(2, parent.getBalanceFactor());
        assertEquals(4, parent.getHeight());
    }

    @Test
    public void testUpdateHeightAndBalanceFactorRightHeight() {
        AVLNode<Integer> parent = new AVLNode<Integer>(5);
        AVLNode<Integer> leftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> rightChild = new AVLNode<Integer>(2);

        parent.setLeft(leftChild);
        parent.setRight(rightChild);

        leftChild.setBalanceFactor(1);
        rightChild.setBalanceFactor(-1);

        leftChild.setHeight(1);
        rightChild.setHeight(3);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(parent);

        assertEquals(-2, parent.getBalanceFactor());
        assertEquals(4, parent.getHeight());
    }

    @Test
    public void testUpdateHeightAndBalanceFactorNullNode() {
        AVLNode<Integer> parent = new AVLNode<Integer>(5);
        AVLNode<Integer> leftChild = new AVLNode<Integer>(10);

        parent.setLeft(leftChild);

        leftChild.setBalanceFactor(-1);

        leftChild.setHeight(3);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(parent);

        assertEquals(4, parent.getBalanceFactor());
        assertEquals(4, parent.getHeight());
    }

    @Test
    public void testRotateLeft() {
        AVLNode<Integer> nodeA = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeC = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeCLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeB);

        nodeB.setLeft(nodeBLeftChild);
        nodeB.setRight(nodeC);

        nodeC.setLeft(nodeCLeftChild);
        nodeC.setRight(nodeCRightChild);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeBLeftChild.setHeight(0);
        nodeBLeftChild.setBalanceFactor(0);

        nodeCLeftChild.setHeight(0);
        nodeCLeftChild.setBalanceFactor(0);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeC);
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeA);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(-2, nodeA.getBalanceFactor());
        assertEquals(3, nodeA.getHeight());

        assertEquals(-1, nodeB.getBalanceFactor());
        assertEquals(2, nodeB.getHeight());

        assertEquals(0, nodeC.getBalanceFactor());
        assertEquals(1, nodeC.getHeight());

        // Now, we test thet rotateLeft() method.
        avl.rotateLeft(nodeA);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeBLeftChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeCLeftChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }

    @Test
    public void testRotateRight() {
        AVLNode<Integer> nodeC = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeA = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeARightChild = new AVLNode<Integer>(10);

        nodeC.setLeft(nodeB);
        nodeC.setRight(nodeCRightChild);

        nodeB.setLeft(nodeA);
        nodeB.setRight(nodeBRightChild);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeARightChild);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        nodeBRightChild.setHeight(0);
        nodeBRightChild.setBalanceFactor(0);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeARightChild.setHeight(0);
        nodeARightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeA);
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeC);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(2, nodeC.getBalanceFactor());
        assertEquals(3, nodeC.getHeight());

        assertEquals(1, nodeB.getBalanceFactor());
        assertEquals(2, nodeB.getHeight());

        assertEquals(0, nodeA.getBalanceFactor());
        assertEquals(1, nodeA.getHeight());

        // Now, we test thet rotateRight() method.
        avl.rotateRight(nodeC);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeARightChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeBRightChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }

    @Test
    public void testBalanceRightRotation() {
        AVLNode<Integer> nodeC = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeA = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeARightChild = new AVLNode<Integer>(10);

        nodeC.setLeft(nodeB);
        nodeC.setRight(nodeCRightChild);

        nodeB.setLeft(nodeA);
        nodeB.setRight(nodeBRightChild);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeARightChild);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        nodeBRightChild.setHeight(0);
        nodeBRightChild.setBalanceFactor(0);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeARightChild.setHeight(0);
        nodeARightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeA);
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeC);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(2, nodeC.getBalanceFactor());
        assertEquals(3, nodeC.getHeight());

        assertEquals(1, nodeB.getBalanceFactor());
        assertEquals(2, nodeB.getHeight());

        assertEquals(0, nodeA.getBalanceFactor());
        assertEquals(1, nodeA.getHeight());

        // Now, we test thet rotateRight() method.
        avl.balance(nodeC);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeARightChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeBRightChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }

    @Test
    public void testBalanceLeftRightRotation() {
        AVLNode<Integer> nodeC = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeA = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBRightChild = new AVLNode<Integer>(10);

        nodeC.setLeft(nodeA);
        nodeC.setRight(nodeCRightChild);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeB);

        nodeB.setLeft(nodeBLeftChild);
        nodeB.setRight(nodeBRightChild);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeBLeftChild.setHeight(0);
        nodeBLeftChild.setBalanceFactor(0);

        nodeBRightChild.setHeight(0);
        nodeBRightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeA);
        avl.updateHeightAndBF(nodeC);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(2, nodeC.getBalanceFactor());
        assertEquals(3, nodeC.getHeight());

        assertEquals(-1, nodeA.getBalanceFactor());
        assertEquals(2, nodeA.getHeight());

        assertEquals(0, nodeB.getBalanceFactor());
        assertEquals(1, nodeB.getHeight());

        // Now, we test thet rotateRight() method.
        avl.balance(nodeC);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeBLeftChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeBRightChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }

    @Test
    public void testBalanceLeft() {
        AVLNode<Integer> nodeA = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeC = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeCLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeB);

        nodeB.setLeft(nodeBLeftChild);
        nodeB.setRight(nodeC);

        nodeC.setLeft(nodeCLeftChild);
        nodeC.setRight(nodeCRightChild);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeBLeftChild.setHeight(0);
        nodeBLeftChild.setBalanceFactor(0);

        nodeCLeftChild.setHeight(0);
        nodeCLeftChild.setBalanceFactor(0);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeC);
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeA);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(-2, nodeA.getBalanceFactor());
        assertEquals(3, nodeA.getHeight());

        assertEquals(-1, nodeB.getBalanceFactor());
        assertEquals(2, nodeB.getHeight());

        assertEquals(0, nodeC.getBalanceFactor());
        assertEquals(1, nodeC.getHeight());

        // Now, we test thet rotateLeft() method.
        avl.balance(nodeA);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeBLeftChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeCLeftChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }

    @Test
    public void testBalanceRightLeft() {
        AVLNode<Integer> nodeA = new AVLNode<Integer>(5);
        AVLNode<Integer> nodeC = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeB = new AVLNode<Integer>(10);

        AVLNode<Integer> nodeALeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeCRightChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBLeftChild = new AVLNode<Integer>(10);
        AVLNode<Integer> nodeBRightChild = new AVLNode<Integer>(10);

        nodeA.setLeft(nodeALeftChild);
        nodeA.setRight(nodeC);

        nodeC.setLeft(nodeB);
        nodeC.setRight(nodeCRightChild);

        nodeB.setLeft(nodeBLeftChild);
        nodeB.setRight(nodeBRightChild);

        nodeALeftChild.setHeight(0);
        nodeALeftChild.setBalanceFactor(0);

        nodeCRightChild.setHeight(0);
        nodeCRightChild.setBalanceFactor(0);

        nodeBLeftChild.setHeight(0);
        nodeBLeftChild.setBalanceFactor(0);

        nodeBRightChild.setHeight(0);
        nodeBRightChild.setBalanceFactor(0);

        AVL<Integer> avl = new AVL();
        avl.updateHeightAndBF(nodeB);
        avl.updateHeightAndBF(nodeC);
        avl.updateHeightAndBF(nodeA);

        // We first perform checks to ensure we constructed the AVL
        // tree correctly and made the updates correctly.
        assertEquals(-2, nodeA.getBalanceFactor());
        assertEquals(3, nodeA.getHeight());

        assertEquals(1, nodeC.getBalanceFactor());
        assertEquals(2, nodeC.getHeight());

        assertEquals(0, nodeB.getBalanceFactor());
        assertEquals(1, nodeB.getHeight());

        // Now, we test thet rotateLeft() method.
        avl.balance(nodeA);

        assertEquals(nodeA, nodeB.getLeft());
        assertEquals(nodeC, nodeB.getRight());
        assertEquals(2, nodeB.getHeight());
        assertEquals(0, nodeB.getBalanceFactor());

        assertEquals(nodeALeftChild, nodeA.getLeft());
        assertEquals(nodeBLeftChild, nodeA.getRight());
        assertEquals(1, nodeA.getHeight());
        assertEquals(0, nodeA.getBalanceFactor());

        assertEquals(nodeBRightChild, nodeC.getLeft());
        assertEquals(nodeCRightChild, nodeC.getRight());
        assertEquals(1, nodeC.getHeight());
        assertEquals(0, nodeC.getBalanceFactor());
    }
}
