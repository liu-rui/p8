package com.liurui.defines.searchs;

import com.liurui.answers.searchs.BinarySearchImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {
    @Test
    public void find()  {
        BinarySearch search = new BinarySearchImpl();

        assertEquals(-1, search.find(new int[]{2, 3, 6, 8, 9, 10, 45, 78, 100}, 4));
        assertEquals(7, search.find(new int[]{2, 3, 6, 8, 9, 10, 45, 78, 100}, 78));
        assertEquals(2, search.find(new int[]{2, 3, 6}, 6));
    }
}