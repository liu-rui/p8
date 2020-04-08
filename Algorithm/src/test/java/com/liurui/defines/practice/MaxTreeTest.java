package com.liurui.defines.practice;

import com.liurui.answers.practice.MaxTreeImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxTreeTest {
    @Test
    public void test() {
        MaxTree item = new MaxTreeImpl();
        MaxTree.Node maxTree = item.getMaxTree(new int[]{3, 4, 5, 1, 2});

        assertEquals(5, maxTree.value);
        assertEquals(4, maxTree.left);
        assertEquals(2, maxTree.right);
        assertEquals(3, maxTree.left.left);
        assertEquals(1, maxTree.right.left);
    }
}
