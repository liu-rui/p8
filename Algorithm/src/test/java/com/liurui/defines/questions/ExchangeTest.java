package com.liurui.defines.questions;

import com.liurui.answers.questions.ExchangeImpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ExchangeTest {
    @Test
    public void test() {
        Exchange item = new ExchangeImpl();
        assertArrayEquals(new int[]{56, 78}, item.exec(new int[]{78, 56}));
    }
}
