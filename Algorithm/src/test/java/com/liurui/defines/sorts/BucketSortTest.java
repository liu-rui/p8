package com.liurui.defines.sorts;

import com.liurui.answers.sorts.BucketSortImpl;
import org.junit.Test;

public class BucketSortTest {
    @Test
    public void sort(){
        BucketSort item = new BucketSortImpl();

        new SortTest().test(item);
    }

}