package com.liurui.defines.questions;

import com.liurui.answers.questions.TopNImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TopNTest {
    private TopN topN = new TopNImpl();
    int[] data = new int[]{3, 7, 1, 9, 20, 60, 15, 2, 40};
    int num = 4;


    @Test
    public void testMax() {
        assertArrayEquals(new int[]{60, 40, 20, 15}, topN.max(data, num));
    }

    @Test
    public void testMin() {
        assertArrayEquals(new int[]{1, 2, 3, 7}, topN.min(data, num));
    }
}
