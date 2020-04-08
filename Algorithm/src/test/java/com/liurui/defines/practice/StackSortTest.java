package com.liurui.defines.practice;

import com.liurui.answers.practice.StackSortImpl;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class StackSortTest {
    @Test
    public void test() {
        Stack<Integer> item = new Stack<>();

        item.push(5);
        item.push(10);
        item.push(2);
        item.push(7);
        item.push(9);
        item.push(1);

        new StackSortImpl().sort(item);

        assertEquals(10, (int) item.pop());
        assertEquals(9, (int) item.pop());
        assertEquals(7, (int) item.pop());
        assertEquals(5, (int) item.pop());
        assertEquals(2, (int) item.pop());
        assertEquals(1, (int) item.pop());
    }
}
