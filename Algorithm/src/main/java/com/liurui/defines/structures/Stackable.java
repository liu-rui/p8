package com.liurui.defines.structures;

public interface Stackable {
    void push(int data);

    int pop();

    int peek();

    void clear();

    int getSize();
}
