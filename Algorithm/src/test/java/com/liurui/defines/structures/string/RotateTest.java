package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.RotateImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RotateTest {
    @Test
    public void test() {
        Rotate item = new RotateImpl();
        assertEquals("efgabcd", item.rotate("abcdefg", 3));
        assertEquals("fgabcde", item.rotate("abcdefg", 4));
    }
}


