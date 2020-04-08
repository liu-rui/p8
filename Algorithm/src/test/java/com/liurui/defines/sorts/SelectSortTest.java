package com.liurui.defines.sorts;


import com.liurui.answers.sorts.SelectSortImpl;
import org.junit.Test;

public class SelectSortTest {
    @Test
    public void sort()   {
        SelectSort item = new SelectSortImpl();

        new SortTest().test(item);
    }

}