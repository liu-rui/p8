package com.liurui.defines.structures.array;


import com.liurui.answers.structures.array.FindRotateInflectionPointImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindRotateInflectionPointTest {
    @Test
    public void test() {
        FindRotateInflectionPoint item = new FindRotateInflectionPointImpl();

        assertEquals(1, item.find(new int[]{6, 7, 8, 9, 1, 2, 3, 4, 5}));
        assertEquals(1, item.find(new int[]{4, 5, 6, 7, 1, 2, 3}));
    }
}
