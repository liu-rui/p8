package com.liurui.defines.searchs;

import com.liurui.answers.searchs.FindKImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindKTest {

    @Test
    public void test() {
        FindK item = new FindKImpl();
        int[] ary = {10, 5, 8, 2, 9, 1, 6, 3, 7, 4};

        assertEquals(9, item.find(ary, 2));
        assertEquals(6, item.find(ary, 5));
    }
}
