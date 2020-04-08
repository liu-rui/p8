package com.liurui.defines.structures.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsTest {
    public void test(Contains item) {
        assertTrue(item.contains("abcdefg", "bdbg"));
        assertFalse(item.contains("abcdefg", "bdbq"));
    }
}
