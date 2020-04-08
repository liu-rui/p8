package com.liurui.defines.structures;

import com.liurui.answers.structures.QueueUsingArrayImpl;
import org.junit.Test;

public class QueueUsingArrayTest {
    @Test
    public void test() {
        QueueUsingArray item = new QueueUsingArrayImpl();

        new QueueTest().test(item);
    }

}
