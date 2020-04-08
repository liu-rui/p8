package com.liurui.defines.structures;

import com.liurui.answers.structures.StackUsingDoubleQueueImpl;
import org.junit.Test;

public class StackUsingDoubleQueueTest {

    @Test
    public void test() {
        StackUsingDoubleQueue item = new StackUsingDoubleQueueImpl();

        new StackTest().test(item);
    }
}