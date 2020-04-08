package com.liurui.defines.structures.tree;

import com.liurui.answers.structures.tree.BinaryTreeUsingLinkImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeUsingLinkTest {

    public void test() {

    }

    @Test
    public void clear() {
        BinaryTree<Integer, Integer> item = new BinaryTreeUsingLinkImpl<>();
        BinaryTreeNode<Integer, Integer> node = new BinaryTreeNode<>(1, 1, new BinaryTreeNode(2, 2), null);

        item.setRoot(node);
        assertFalse(item.isEmpty());
        assertEquals(2, item.getSize());
        assertEquals(1, item.getSize(1));
        assertEquals(1, item.getSize(2));
        assertEquals(0, item.getSize(3));


        assertEquals(node, item.getRoot());
        item.clear();
        assertNull(item.getRoot());
        assertTrue(item.isEmpty());
        assertEquals(0, item.getSize());
    }

    @Test
    public void getSize() {
        BinaryTree<String, Integer> item = new BinaryTreeUsingLinkImpl<>();
        BinaryTreeNode<String, Integer> a = new BinaryTreeNode<>("a", 1);
        BinaryTreeNode<String, Integer> b = new BinaryTreeNode<>("b", 2);
        BinaryTreeNode<String, Integer> c = new BinaryTreeNode<>("c", 3);
        BinaryTreeNode<String, Integer> d = new BinaryTreeNode<>("d", 4);
        BinaryTreeNode<String, Integer> e = new BinaryTreeNode<>("e", 5);

        item.insertLeft(a, b);
        item.insertRight(a, c);
        item.insertRight(c, d);
        item.insertRight(d, e);

        item.setRoot(a);
        assertFalse(item.isEmpty());
        assertEquals(5, item.getSize());

        assertEquals(1, item.getSize(1));
        assertEquals(2, item.getSize(2));
        assertEquals(1, item.getSize(3));
        assertEquals(1, item.getSize(4));
        assertEquals(0, item.getSize(5));


        assertEquals(4, item.getHeight());

        assertEquals(d, item.getParent(e));
        assertEquals(a, item.getParent(c));
        assertNull(item.getParent(a));

        assertEquals(b, item.getLeftNode(a));
        assertEquals(c, item.getRightNode(a));
        assertNull(item.getLeftNode(d));
        assertEquals(e, item.getRightNode(d));

        assertEquals("[a:1],[b:2],[c:3],[d:4],[e:5]", item.printPreOrder());
        assertEquals("[b:2],[a:1],[c:3],[d:4],[e:5]", item.printInOrder());
        assertEquals("[b:2],[e:5],[d:4],[c:3],[a:1]", item.printPostOrder());
        assertEquals("[a:1],[b:2],[c:3],[d:4],[e:5]", item.printLevelOrder());
    }
}
