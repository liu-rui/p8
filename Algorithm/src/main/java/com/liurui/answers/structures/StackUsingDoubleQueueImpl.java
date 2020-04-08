package com.liurui.answers.structures;

import com.liurui.defines.structures.StackUsingDoubleQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/***
 * 使用两个队列实现栈功能
 */
public class StackUsingDoubleQueueImpl implements StackUsingDoubleQueue {
    Queue<Integer> a = new ArrayDeque<>();
    Queue<Integer> b = new ArrayDeque<>();

    public void push(int data) {
        if (a.isEmpty()) {
            b.add(data);
        } else {
            a.add(data);
        }
    }

    public int getSize() {
        return a.size() + b.size();
    }

    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }

    public int pop() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }

        if (a.isEmpty()) {
            return popPrivate(b, a);
        } else {
            return popPrivate(a, b);
        }
    }

    @Override
    public int peek() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }

        if (a.isEmpty()) {
            return peekPrivate(b, a);
        } else {
            return peekPrivate(a, b);
        }
    }

    @Override
    public void clear() {
        a.clear();
        b.clear();
    }

    private int popPrivate(Queue<Integer> first, Queue<Integer> second) {
        while (first.size() != 1) {
            second.add(first.poll());
        }
        return first.poll();
    }

    private int peekPrivate(Queue<Integer> first, Queue<Integer> second) {
        while (first.size() != 1) {
            second.add(first.poll());
        }
        int ret = first.peek();
        second.add(first.poll());
        return ret;
    }
}
