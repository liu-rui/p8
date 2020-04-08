package com.liurui.defines.structures.array;

import com.liurui.answers.structures.array.DutchFlagImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class DutchFlagTest {
    @Test
    public void test() {
        DutchFlag item = new DutchFlagImpl();
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2},
                item.sort(new int[]{1, 1, 0, 0, 2, 2, 1, 0, 1, 2, 1, 0, 2}));
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2},
                item.sort(new int[]{0, 1, 0, 0, 2, 2, 1, 0, 1, 2, 1, 0, 1}));
    }
}
