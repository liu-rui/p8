package com.liurui.answers.structures;


import com.liurui.defines.structures.QueueUsingArray;

public class QueueUsingArrayImpl implements QueueUsingArray {
    public final static int DEFAULT_CAPACITY = 4;
    int[] list;
    int head;
    int tail;


    public QueueUsingArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    public QueueUsingArrayImpl(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }
        list = new int[capacity];
    }

    @Override
    public void add(int data) {
        if (full()) {
            throw new IndexOutOfBoundsException();
        }
        list[tail] = data;
        tail = (tail + 1) % list.length;
    }

    @Override
    public int pop() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        int ret = list[head];

        head = (head + 1) % list.length;
        return ret;
    }

    @Override
    public int peek() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }

        return list[head];
    }

    @Override
    public void clear() {
        head = 0;
        tail = 0;
    }

    @Override
    public int getSize() {
        if (tail >= head) {
            return tail - head;
        } else {
            return tail + list.length - head;
        }
    }

    private boolean full() {
        return head == (tail + 1) % list.length;
    }

    private boolean empty() {
        return head == tail;
    }
}
