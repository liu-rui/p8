package com.liurui.defines.structures;

import static org.junit.Assert.assertEquals;

public class QueueTest {
    public  void test(Queuable queue) {
        queue.add(10);
        queue.add(20);
        queue.add(30);

        assertEquals(3, queue.getSize());
        assertEquals(10, queue.pop());
        assertEquals(20, queue.peek());
        assertEquals(20, queue.pop());
        assertEquals(30, queue.pop());
        assertEquals(0, queue.getSize());


        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.pop();
        queue.add(40);

        assertEquals(3, queue.getSize());
        assertEquals(20, queue.pop());
        assertEquals(30, queue.pop());
        assertEquals(40, queue.pop());
        assertEquals(0, queue.getSize());
    }
}
