package com.liurui.defines.structures;

import static org.junit.Assert.assertEquals;

public class StackTest {


    public void test(Stackable stack) {
        assertEquals(0, stack.getSize());

        stack.push(100);
        assertEquals(1, stack.getSize());
        assertEquals(100, stack.peek());
        assertEquals(100, stack.pop());
        assertEquals(0, stack.getSize());


        stack.push(200);
        stack.push(300);
        stack.push(400);
        stack.push(500);
        stack.push(600);


        assertEquals(5, stack.getSize());
        assertEquals(600, stack.pop());
        assertEquals(500, stack.pop());
        assertEquals(400, stack.pop());
        assertEquals(300, stack.pop());
        assertEquals(200, stack.pop());
        assertEquals(0, stack.getSize());
    }
}