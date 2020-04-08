package com.liurui.defines.structures.tree;

import org.junit.Test;

/**
 * https://www.jianshu.com/p/0190985635eb
 */
public class BinaryTreeTest {

    /***
     *          a
     *       b     c
     *    d    e
     * f
     */
    @Test
    public void test() {
//        String data = "abcdef";
//        BinaryTree.Node[] nodes = new BinaryTree.Node[data.length()];
//
//        for (int i = 0; i < data.length(); i++) {
//            nodes[i] = new BinaryTree.Node(String.valueOf(data.charAt(i)));
//        }
//
//        BinaryTree tree = new BinaryTree(nodes[0]);
//
//        assertEquals(1, tree.getSize());
//        assertEquals(1, tree.getHeight());
//
//        tree.insertLeft(tree.getRoot(), nodes[1]);
//
//        assertEquals(2, tree.getSize());
//        assertEquals(2, tree.getHeight());
//        assertEquals(tree.getRoot(), tree.getParent(nodes[1]));
//        assertTrue(tree.exists(nodes[1]));
//
//        tree.insertRight(tree.getRoot(), nodes[2]);
//
//        assertEquals(3, tree.getSize());
//        assertEquals(2, tree.getHeight());
//        assertEquals(tree.getRoot(), tree.getParent(nodes[2]));
//        assertTrue(tree.exists(nodes[2]));
//
//
//        tree.insertLeft(nodes[1], nodes[3]);
//        tree.insertRight(nodes[1], nodes[4]);
//        tree.insertLeft(nodes[3], nodes[5]);
//
//
//        assertEquals(6, tree.getSize());
//        assertEquals(4, tree.getHeight());
//
//        assertEquals("abdfec", tree.print(BinaryTree.OrderType.Pre_Order));
//        assertEquals("fdbeac", tree.print(BinaryTree.OrderType.In_Order));
//        assertEquals("fdebca", tree.print(BinaryTree.OrderType.Post_Order));
    }
}
