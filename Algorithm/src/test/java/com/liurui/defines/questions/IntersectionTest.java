package com.liurui.defines.questions;

import com.liurui.answers.questions.IntersectionImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class IntersectionTest {
    @Test
    public void test() {
        Intersection intersection = new IntersectionImpl();

        assertArrayEquals(new int[]{3, 8}, intersection.get(new int[]{5, 8, 3, 7}, new int[]{8, 3, 6, 10}));
        assertArrayEquals(new int[]{}, intersection.get(new int[]{50,60}, new int[]{8, 3, 6, 10}));
    }
}
