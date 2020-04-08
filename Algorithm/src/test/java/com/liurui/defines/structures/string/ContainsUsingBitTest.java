package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.ContainsUsingBitImpl;
import org.junit.Test;

public class ContainsUsingBitTest {
    @Test
    public void test() {
        Contains item = new ContainsUsingBitImpl();

        new ContainsTest().test(item);
    }
}
