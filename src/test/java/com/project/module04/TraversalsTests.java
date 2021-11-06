package com.project.module04;

import org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TraversalsTests {
    @Test
    public void testPreorderTraversal() {
        TreeNode<Integer> node1 = new TreeNode<Integer>(50);
        TreeNode<Integer> node2 = new TreeNode<Integer>(25);
        TreeNode<Integer> node3 = new TreeNode<Integer>(100);
        TreeNode<Integer> node4 = new TreeNode<Integer>(10);
        TreeNode<Integer> node5 = new TreeNode<Integer>(75);
        TreeNode<Integer> node6 = new TreeNode<Integer>(125);
        TreeNode<Integer> node7 = new TreeNode<Integer>(110);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);

        node3.setLeft(node5);
        node3.setRight(node6);

        node6.setLeft(node7);

        List<Integer> expectedTraversal = new ArrayList<>();
        expectedTraversal.add(50);
        expectedTraversal.add(25);
        expectedTraversal.add(10);
        expectedTraversal.add(100);
        expectedTraversal.add(75);
        expectedTraversal.add(125);
        expectedTraversal.add(110);

        List<Integer> actualTraversal = new Traversals<Integer>().preorder(node1);
        assertEquals(expectedTraversal, actualTraversal);
    }

    @Test
    public void testPreorderTraversalNoRoot() {

        List<Integer> expectedTraversal = new ArrayList<>();

        List<Integer> actualTraversal = new Traversals<Integer>().preorder(null);
        assertEquals(expectedTraversal, actualTraversal);
    }

    @Test
    public void testPreorderTraversalRootOnly() {
        TreeNode<Integer> node1 = new TreeNode<Integer>(50);

        List<Integer> expectedTraversal = new ArrayList<>();
        expectedTraversal.add(50);

        List<Integer> actualTraversal = new Traversals<Integer>().preorder(node1);
        assertEquals(expectedTraversal, actualTraversal);
    }

    @Test
    public void testInorderTraversal() {
        TreeNode<Integer> node1 = new TreeNode<Integer>(50);
        TreeNode<Integer> node2 = new TreeNode<Integer>(25);
        TreeNode<Integer> node3 = new TreeNode<Integer>(100);
        TreeNode<Integer> node4 = new TreeNode<Integer>(10);
        TreeNode<Integer> node5 = new TreeNode<Integer>(75);
        TreeNode<Integer> node6 = new TreeNode<Integer>(125);
        TreeNode<Integer> node7 = new TreeNode<Integer>(110);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);

        node3.setLeft(node5);
        node3.setRight(node6);

        node6.setLeft(node7);

        List<Integer> expectedTraversal = new ArrayList<>();
        expectedTraversal.add(10);
        expectedTraversal.add(25);
        expectedTraversal.add(50);
        expectedTraversal.add(75);
        expectedTraversal.add(100);
        expectedTraversal.add(110);
        expectedTraversal.add(125);

        List<Integer> actualTraversal = new Traversals<Integer>().inorder(node1);
        assertEquals(expectedTraversal, actualTraversal);
    }

    @Test
    public void testInorderTraversalNoRoot() {

        List<Integer> expectedTraversal = new ArrayList<>();

        List<Integer> actualTraversal = new Traversals<Integer>().inorder(null);
        assertEquals(expectedTraversal, actualTraversal);
    }

    @Test
    public void testInorderTraversalRootOnly() {
        TreeNode<Integer> node1 = new TreeNode<Integer>(50);

        List<Integer> expectedTraversal = new ArrayList<>();
        expectedTraversal.add(50);

        List<Integer> actualTraversal = new Traversals<Integer>().inorder(node1);
        assertEquals(expectedTraversal, actualTraversal);
    }
}
