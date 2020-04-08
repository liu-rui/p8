package com.liurui.defines.searchs;

import com.liurui.answers.searchs.YoungSearchImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class YoungSearchTest {
    @Test
    public void test() {
        YoungSearch youngSearch = new YoungSearchImpl();
        int[][] data = new int[][]{{1, 3, 4},
                {2, 4, 6},
                {5, 7, 8}};

        assertTrue(youngSearch.search(data, 7));
        assertFalse(youngSearch.search(data, 9));
    }
}