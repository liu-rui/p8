package com.liurui.defines.practice;

import com.liurui.answers.practice.ReverseStackImpl;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ReverseStackTest {
    @Test
    public void test(){
        Stack<Integer> item = new Stack<>();
        item.push(1);
        item.push(2);
        item.push(3);
        item.push(4);
        item.push(5);

        new ReverseStackImpl().reverse(item);

        assertEquals( 1 , (int)item.pop());
        assertEquals( 2 , (int)item.pop());
        assertEquals( 3 , (int)item.pop());
        assertEquals( 4 , (int)item.pop());
        assertEquals( 5 , (int)item.pop());
    }
}
