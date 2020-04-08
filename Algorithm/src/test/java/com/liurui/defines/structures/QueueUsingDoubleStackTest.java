package com.liurui.defines.structures;

import com.liurui.answers.structures.QueueUsingDoubleStackImpl;
import org.junit.Test;

public class QueueUsingDoubleStackTest {
    @Test
    public void test() {
        QueueUsingDoubleStack item = new QueueUsingDoubleStackImpl();

        new QueueTest().test(item);
    }
}