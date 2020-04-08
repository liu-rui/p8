package com.liurui.defines.structures.array;

import com.liurui.answers.structures.array.ArrayAssignmentImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ArrayAssignmentTest {
    @Test
    public void test() {
        ArrayAssignment item = new ArrayAssignmentImpl();

        assertArrayEquals(new int[]{1024, 512, 256, 128, 64}, item.get(new int[]{1, 2, 4, 8, 16}));
    }
}
