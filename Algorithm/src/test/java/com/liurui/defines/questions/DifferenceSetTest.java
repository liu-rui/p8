package com.liurui.defines.questions;

import com.liurui.answers.questions.DifferenceSetImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class DifferenceSetTest {

    @Test
    public void test() {
        DifferenceSet diff = new DifferenceSetImpl();
        assertArrayEquals(new int[]{5,7}, diff.get(new int[]{5, 8, 3, 7}, new int[]{8, 3, 6, 10}));
        assertArrayEquals(new int[]{}, diff.get(new int[]{6}, new int[]{8, 3, 6, 10}));
    }
}