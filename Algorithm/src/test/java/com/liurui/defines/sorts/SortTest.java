package com.liurui.defines.sorts;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {
    public  void test(Sortable item){
        assertArrayEquals(new int[]{1, 1, 3, 5, 5, 6, 7, 9, 9},
                item.sort(new int[]{5, 9, 1, 9, 5, 3, 7, 6, 1}));
    }
}
