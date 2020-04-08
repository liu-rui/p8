package com.liurui.defines.structures;

public interface Queuable {
    void add(int data);

    int pop();

    int peek();

    void clear();

    int getSize();
}
