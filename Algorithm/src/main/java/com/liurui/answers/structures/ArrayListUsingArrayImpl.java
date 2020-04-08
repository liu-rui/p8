package com.liurui.answers.structures;


import com.liurui.defines.structures.ArrayListUsingArray;
import com.liurui.defines.structures.ArrayListable;

import java.util.Arrays;


public class ArrayListUsingArrayImpl implements ArrayListUsingArray {
    public final static int DEFAULT_CAPACITY = 10;
    protected int[] list;
    protected int size;


    public ArrayListUsingArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListUsingArrayImpl(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity");
        }

        list = new int[capacity];
    }

    @Override
    public void add(int data) {
        insert(size, data);
    }

    @Override
    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index");
        }

        for (int i = size - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = data;
        size++;
        resize();
    }

    protected void resize() {
        if (size < list.length) {
            return;
        }
        list = Arrays.copyOf(list, list.length * 2);
    }

    @Override
    public boolean contains(int data) {
        if (isEmpty()) {
            return false;
        }
        if (list[0] == data) {
            return true;
        }
        int first = list[0];

        list[0] = data;
        int i = size - 1;

        while (data != list[i]) {
            i--;
        }
        list[0] = first;
        return i != 0;
    }

    @Override
    public int remove() {
        return remove(size - 1);
    }

    @Override
    public int remove(int index) {
        if (isEmpty() || index < 0) {
            throw new IndexOutOfBoundsException("index");
        }
        int ret = list[index];

        for (int i = index + 1; i < size; i++) {
            list[i - 1] = list[i];
        }
        size--;
        return ret;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int capacity() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int get(int index) {
        if (isEmpty() || index < 0) {
            throw new IndexOutOfBoundsException("index");
        }
        return list[index];
    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public void reverse() {

    }

    @Override
    public void sort(boolean asc) {

    }

    @Override
    public void marge(ArrayListable another, boolean asc) {

    }
}
