package com.liurui.answers.structures;


import com.liurui.defines.structures.ArrayListUsingLink;
import com.liurui.defines.structures.ArrayListable;
import com.liurui.defines.structures.Item;

public class ArrayListUsingLinkImpl implements ArrayListUsingLink {
    Item<Integer> head;
    Item<Integer> tail;
    int size;


    @Override
    public void add(int data) {
        insert(size, data);
    }

    @Override
    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Item<Integer> item = new Item<>(data);

        if (size == 0) {
            head = item;
            tail = item;
        } else {
            if (index == 0) {
                item.setNext(head);
                head = item;
            } else if (index == size) {
                tail.setNext(item);
                tail = item;
            } else {
                Item<Integer> preItem = getItem(index - 1);

                item.setNext(preItem.getNext());
                preItem.setNext(item);
            }

        }
        size++;
    }

    private Item<Integer> getItem(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Item<Integer> ret = head;
        int i = 0;

        while (i != index) {
            ret = ret.getNext();
            i++;
        }
        return ret;
    }

    @Override
    public boolean contains(int data) {
        Item<Integer> ret = head;

        while (ret != null) {
            if (ret.getData() == data) {
                return true;
            }

            ret = ret.getNext();
        }
        return false;
    }

    @Override
    public int remove() {
        return remove(size - 1);
    }

    @Override
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Item<Integer> item = getItem(index);

        if (size == 1) {
            clear();
        } else if (index == 0) {
            head = head.getNext();
            size--;
        } else {
            Item<Integer> prevItem = getItem(index - 1);

            prevItem.setNext(item.getNext());
            if (index == size - 1) {
                tail = prevItem;
            }
            size--;
        }
        return item.getData();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int get(int index) {
        Item<Integer> item = getItem(index);

        return item.getData();
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
