package com.liurui.defines.structures.tree;

import com.liurui.answers.structures.tree.BlanceBinaryTreeUsingAvlImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlanceBinaryTreeTest {
    /**
     * https://www.cnblogs.com/skywang12345/p/3577479.html
     * <p>
     * add 3 2 1 4 5 6 7 16 15 14 13 12 11 10 8 9
     */

    @Test
    public  void testLL(){
        BinarySearchTree<Integer, Integer> item = new BlanceBinaryTreeUsingAvlImpl<>();

        item.add(3, 3);
        item.add(2, 2);
        item.add(1, 1);

        assertTrue(item.contains(3));
        assertFalse(item.contains(5));
        assertEquals("[1:0],[2:1],[3:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1]", item.printPostOrder());
    }


    @Test
    public  void testRR(){
        BinarySearchTree<Integer, Integer> item = new BlanceBinaryTreeUsingAvlImpl<>();

        item.add(1, 1);
        item.add(2, 2);
        item.add(3, 3);

        assertEquals("[1:0],[2:1],[3:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1]", item.printPostOrder());
    }


    @Test
    public void testPut() {
        BinarySearchTree<Integer, Integer> item = new BlanceBinaryTreeUsingAvlImpl<>();

        item.add(3, 3);
        item.add(2, 2);
        item.add(1, 1);

        assertEquals("[1:0],[2:1],[3:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1]", item.printPostOrder());


        item.add(4, 4);
        item.add(5, 5);
        assertEquals("[1:0],[2:2],[3:0],[4:1],[5:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[5:0],[4:1],[2:2]", item.printPostOrder());
        item.add(6, 6);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:1],[6:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[6:0],[5:1],[4:2]", item.printPostOrder());
        item.add(7, 7);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[7:0],[6:1],[4:2]", item.printPostOrder());
        item.add(16, 16);
        item.add(15, 15);
        assertEquals("[1:0],[2:1],[3:0],[4:3],[5:0],[6:2],[7:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[7:0],[16:0],[15:1],[6:2],[4:3]", item.printPostOrder());
        item.add(14, 14);
        assertEquals("[1:0],[2:1],[3:0],[4:3],[5:0],[6:1],[7:2],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[14:0],[16:0],[15:1],[7:2],[4:3]", item.printPostOrder());
        item.add(13, 13);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:3],[13:0],[14:1],[15:2],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[4:2],[13:0],[14:1],[16:0],[15:2],[7:3]", item.printPostOrder());
        item.add(12, 12);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:3],[12:0],[13:1],[14:0],[15:2],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[4:2],[12:0],[14:0],[13:1],[16:0],[15:2],[7:3]", item.printPostOrder());
        item.add(11, 11);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:3],[11:0],[12:1],[13:2],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[4:2],[11:0],[12:1],[14:0],[16:0],[15:1],[13:2],[7:3]", item.printPostOrder());
        item.add(10, 10);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:3],[10:0],[11:1],[12:0],[13:2],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[4:2],[10:0],[12:0],[11:1],[14:0],[16:0],[15:1],[13:2],[7:3]", item.printPostOrder());
        item.add(8, 8);
        item.add(9, 9);
        assertEquals("[1:0],[2:1],[3:0],[4:2],[5:0],[6:1],[7:4],[8:0],[9:1],[10:0],[11:2],[12:0],[13:3],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[5:0],[6:1],[4:2],[8:0],[10:0],[9:1],[12:0],[11:2],[14:0],[16:0],[15:1],[13:3],[7:4]", item.printPostOrder());

        item.remove(4);
        assertEquals("[1:0],[2:1],[3:0],[5:2],[6:0],[7:4],[8:0],[9:1],[10:0],[11:2],[12:0],[13:3],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:0],[2:1],[6:0],[5:2],[8:0],[10:0],[9:1],[12:0],[11:2],[14:0],[16:0],[15:1],[13:3],[7:4]", item.printPostOrder());

        item.remove(2);
        assertEquals("[1:0],[3:1],[5:2],[6:0],[7:4],[8:0],[9:1],[10:0],[11:2],[12:0],[13:3],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[3:1],[6:0],[5:2],[8:0],[10:0],[9:1],[12:0],[11:2],[14:0],[16:0],[15:1],[13:3],[7:4]", item.printPostOrder());
        item.remove(5);
        assertEquals("[1:0],[3:1],[6:0],[7:2],[8:0],[9:1],[10:0],[11:3],[12:0],[13:2],[14:0],[15:1],[16:0]", item.printInOrder());
        assertEquals("[1:0],[6:0],[3:1],[8:0],[10:0],[9:1],[7:2],[12:0],[14:0],[16:0],[15:1],[13:2],[11:3]", item.printPostOrder());
    }
}