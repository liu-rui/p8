package com.liurui.answers.practice;

import com.liurui.defines.practice.StackWithMin;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 要求：
 * 1、pop、push、getMin操作的时间复杂度都是O(1)
 * 2、设计的栈类型可以输用现成的栈结构
 */
public class StackWithMinImpl implements StackWithMin {
    private Stack<Integer> list = new Stack<>();
    private Stack<Integer> minList = new Stack<>();

    /**
     * 返回最小值
     *
     * @return 最小值
     */
    @Override
    public int getMin() {
        return minList.peek();
    }

    @Override
    public void push(int data) {
        list.push(data);

        if (minList.empty()) {
            minList.push(data);
        } else if (minList.peek() >= data) {
            minList.push(data);
        }
    }

    @Override
    public int pop() {
        Integer ret = list.pop();

        if (minList.peek() == ret) {
            minList.pop();
        }
        return ret;
    }

    @Override
    public int peek() {
        return list.peek();
    }

    @Override
    public void clear() {
        list.clear();
        minList.clear();
    }

    @Override
    public int getSize() {
        return list.size();
    }
}
