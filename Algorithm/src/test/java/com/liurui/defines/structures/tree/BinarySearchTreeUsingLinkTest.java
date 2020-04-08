package com.liurui.defines.structures.tree;

import com.liurui.answers.structures.tree.BinarySearchTreeUsingLinkImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeUsingLinkTest {
    BinarySearchTree item;


    @Before
    public void init() {
        item = new BinarySearchTreeUsingLinkImpl<Integer,Integer>();
        item.add(42,42);
        item.add(14,14);
        item.add(65,65);
        item.add(9,9);
        item.add(23,23);
        item.add(55,55);
        item.add(72,72);
        item.add(1,1);
        item.add(68,68);
        item.add(88,88);
        item.add(70,70);
    }

    @Test
    public void testContains() {
        assertTrue(item.contains(72));
        assertFalse(item.contains(100));

        assertEquals("[1:1],[9:9],[14:14],[23:23],[42:42],[55:55],[65:65],[68:68],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[9:9],[23:23],[14:14],[55:55],[70:70],[68:68],[88:88],[72:72],[65:65],[42:42]" , item.printPostOrder());
    }

    @Test
    public void testRemove1() {
        item.remove(23);
        assertFalse(item.contains(23));
        assertEquals("[1:1],[9:9],[14:14],[42:42],[55:55],[65:65],[68:68],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[9:9],[14:14],[55:55],[70:70],[68:68],[88:88],[72:72],[65:65],[42:42]", item.printPostOrder());
    }

    @Test
    public  void  testRemove2(){
        item.remove(9);
        assertFalse(item.contains(9));
        assertEquals("[1:1],[14:14],[23:23],[42:42],[55:55],[65:65],[68:68],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[23:23],[14:14],[55:55],[70:70],[68:68],[88:88],[72:72],[65:65],[42:42]", item.printPostOrder());
    }


    @Test
    public void testRemove3() {
        item.remove(68);
        assertFalse(item.contains(68));

        assertEquals("[1:1],[9:9],[14:14],[23:23],[42:42],[55:55],[65:65],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[9:9],[23:23],[14:14],[55:55],[70:70],[88:88],[72:72],[65:65],[42:42]" , item.printPostOrder());
    }


    @Test
    public void testRemove4() {
        item.remove(65);
        assertFalse(item.contains(65));

        assertEquals("[1:1],[9:9],[14:14],[23:23],[42:42],[55:55],[68:68],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[9:9],[23:23],[14:14],[55:55],[70:70],[88:88],[72:72],[68:68],[42:42]" , item.printPostOrder());
    }

    @Test
    public void testRemove5() {
        item.remove(42);
        assertFalse(item.contains(42));

        assertEquals("[1:1],[9:9],[14:14],[23:23],[55:55],[65:65],[68:68],[70:70],[72:72],[88:88]", item.printInOrder());
        assertEquals("[1:1],[9:9],[23:23],[14:14],[70:70],[68:68],[88:88],[72:72],[65:65],[55:55]" , item.printPostOrder());
    }
}
