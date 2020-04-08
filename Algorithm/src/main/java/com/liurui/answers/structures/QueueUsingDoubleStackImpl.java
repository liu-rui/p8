package com.liurui.answers.structures;


import com.liurui.defines.structures.QueueUsingDoubleStack;

import java.util.Stack;

/***
 * 使用两个栈实现队列功能
 */
public class QueueUsingDoubleStackImpl implements QueueUsingDoubleStack {
    Stack<Integer> a = new Stack<>();
    Stack<Integer> b = new Stack<>();

    public int getSize() {
        return a.size() + b.size();
    }

    @Override
    public void add(int data) {
        a.push(data);
    }

    public int pop() {
        fill();
        return b.pop();
    }

    @Override
    public int peek() {
        fill();
        return b.peek();
    }


    private void fill() {
        if (getSize() == 0) {
            throw new IndexOutOfBoundsException();
        }

        if (b.empty()) {
            while (!a.empty()) {
                b.push(a.pop());
            }
        }
    }

    @Override
    public void clear() {
        a.clear();
        b.clear();
    }
}
