package com.liurui.answers.structures;


import com.liurui.defines.structures.Item;
import com.liurui.defines.structures.StackUsingLink;

public class StackUsingLinkImpl implements StackUsingLink {
    Item<Integer> tail;
    int size = 0;


    @Override
    public void push(int data) {
        Item<Integer> item = new Item<>(data);

        item.setNext(tail);
        tail = item;
        size++;
    }

    @Override
    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int ret = tail.getData();

        tail = tail.getNext();
        size--;
        return ret;
    }

    @Override
    public int peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }

        return tail.getData();
    }

    @Override
    public void clear() {
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }
}
