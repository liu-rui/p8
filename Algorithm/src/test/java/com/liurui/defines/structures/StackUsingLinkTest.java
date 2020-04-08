package com.liurui.defines.structures;

import com.liurui.answers.structures.StackUsingLinkImpl;
import org.junit.Test;

public class StackUsingLinkTest {
    @Test
    public void test() {
        StackUsingLink item = new StackUsingLinkImpl();

        new StackTest().test(item);
    }
}
