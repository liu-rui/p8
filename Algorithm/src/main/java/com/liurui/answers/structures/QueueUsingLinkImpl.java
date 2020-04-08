package com.liurui.answers.structures;


import com.liurui.defines.structures.Item;
import com.liurui.defines.structures.QueueUsingLink;

public class QueueUsingLinkImpl implements QueueUsingLink {
    Item<Integer> head;
    Item<Integer> tail;
    int size = 0;

    @Override
    public void add(int data) {
        Item<Integer> item = new Item<>(data);
        if (size == 0) {
            head = item;
            tail = item;
        } else {
            tail.setNext(item);
            tail = item;
        }
        size++;
    }

    @Override
    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int ret = head.getData();

        if (size == 1) {
            clear();
        } else {
            head = head.getNext();
            size--;
        }
        return ret;
    }

    @Override
    public int peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return head.getData();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }
}
