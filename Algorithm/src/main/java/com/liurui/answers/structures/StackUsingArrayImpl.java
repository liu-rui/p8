package com.liurui.answers.structures;


import com.liurui.defines.structures.StackUsingArray;

import java.util.Arrays;


public class StackUsingArrayImpl implements StackUsingArray {
    public static final int DEFAULT_CAPACITY = 10;

    int[] list;
    int size;

    public StackUsingArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    public StackUsingArrayImpl(int capacity) {
        if (capacity < 1) {
            capacity = DEFAULT_CAPACITY;
        }

        list = new int[capacity];
    }

    @Override
    public void push(int data) {
        list[size++] = data;
        resize();
    }

    private void resize() {
        if (size < list.length) {
            return;
        }
        list = Arrays.copyOf(list, list.length * 2);
    }

    @Override
    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }

        return list[--size];
    }

    @Override
    public int peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }

        return list[size - 1];
    }


    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }


    public int capacity() {
        return list.length;
    }
}
