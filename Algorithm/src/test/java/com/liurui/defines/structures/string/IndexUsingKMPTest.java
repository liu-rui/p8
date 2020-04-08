package com.liurui.defines.structures.string;

import com.liurui.templates.structures.string.IndexUsingKMPImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class IndexUsingKMPTest {

    private IndexUsingKMP item;

    @Before
    public void init() {
        item = new IndexUsingKMPImpl();
    }

    @Test
    public void testGetNext() {
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1}, item.getNext("abcdex"));
        assertArrayEquals(new int[]{0, 1, 1, 1, 2, 3}, item.getNext("abcabx"));
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 4, 2, 2, 3}, item.getNext("ababaaaba"));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, item.getNext("aaaaaaaab"));
    }
}
