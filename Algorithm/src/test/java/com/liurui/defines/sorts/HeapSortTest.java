package com.liurui.defines.sorts;

import com.liurui.answers.sorts.HeapSortImpl;
import org.junit.Test;

public class HeapSortTest {
    @Test
    public void sort() {
        HeapSort item = new HeapSortImpl();

        new SortTest().test(item);
    }
}