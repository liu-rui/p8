package com.liurui.defines.sorts;

import com.liurui.answers.sorts.BubbleSortImpl;
import org.junit.Test;

public class BubbleSortTest {
    @Test
    public void sort() {
        BubbleSort item = new BubbleSortImpl();

        new SortTest().test(item);
    }
}