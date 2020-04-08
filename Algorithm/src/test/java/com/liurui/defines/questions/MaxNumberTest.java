package com.liurui.defines.questions;

import com.liurui.answers.questions.MaxNumberImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxNumberTest {
    @Test
    public void test() {
        MaxNumber item = new MaxNumberImpl();

        assertEquals(45, item.find(new int[]{23, 45}));
        assertEquals(45, item.find(new int[]{45, 12}));
    }
}
