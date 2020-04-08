package com.liurui.defines.questions;

import com.liurui.answers.questions.OddEvenSortImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class OddEvenSortTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{3, 9, 5, 8, 6, 10}, new OddEvenSortImpl().exec(new int[]{3, 6, 8, 5, 9, 10}));
        assertArrayEquals(new int[]{1,3,6,4}, new OddEvenSortImpl().exec(new int[]{4,6,3,1}));
    }
}