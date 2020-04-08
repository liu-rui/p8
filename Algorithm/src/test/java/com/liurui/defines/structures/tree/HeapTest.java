package com.liurui.defines.structures.tree;

import com.liurui.answers.structures.tree.HeapImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    @Test
    public void testBigHeap() {
        Heap bigHeap = new HeapImpl();
        int[] data = {14, 3, 9, 50, 20, 15, 7, 5};
        int[] ret = {50, 20, 15, 14, 9, 7, 5, 3};

        bigHeap.init(true, data);
        assertEquals(ret.length, bigHeap.size());

        for (int i = 0; i < ret.length; i++) {
            assertEquals(ret[i], bigHeap.pop());
        }
        assertEquals(0, bigHeap.size());
    }

    @Test
    public void testSmallHeap() {
        Heap smallHeap = new HeapImpl();
        int[] data = {14, 3, 9, 50, 20, 15, 7, 5};
        int[] ret = {3, 5, 7, 9, 14, 15, 20, 50};

        smallHeap.init(false, data);
        assertEquals(ret.length, smallHeap.size());

        for (int i = 0; i < ret.length; i++) {
            assertEquals(ret[i], smallHeap.pop());
        }
        assertEquals(0, smallHeap.size());
    }

}
