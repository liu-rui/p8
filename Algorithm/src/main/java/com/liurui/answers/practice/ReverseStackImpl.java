package com.liurui.answers.practice;


import com.liurui.defines.practice.ReverseStack;

import java.util.Stack;

/**
 * 一个栈依次压入1,2,3,4,5那么从栈顶到栈底分别为5,4,3,2,1。
 * 将这个栈转置后，从栈顶到栈底为1,2,3,4,5，也就
 * 是实现栈中元素的逆序，但是只能用递归函数来实现，而不能用另外的数据结构。
 */
public class ReverseStackImpl implements ReverseStack {

    @Override
    public void reverse(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        } else {
            int last = get(stack);
            reverse(stack);
            stack.push(last);
        }
    }

    public int get(Stack<Integer> stack) {
        Integer top = stack.pop();

        if (stack.empty()) {
            return top;
        } else {
            int last = get(stack);
            stack.push(top);
            return last;
        }
    }
}
