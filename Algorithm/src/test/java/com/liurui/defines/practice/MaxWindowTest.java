package com.liurui.defines.practice;

import com.liurui.answers.practice.MaxWindowImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MaxWindowTest {
    @Test
    public void test() {
        MaxWindow item = new MaxWindowImpl();

        assertArrayEquals(new int[]{4, 4, 4, 5, 6},
                item.get(new int[]{2, 3, 4, 3, 2, 5, 6}, 3));
        assertArrayEquals(new int[]{5, 5, 5, 4, 6, 7},
                item.get(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3));
    }
}
