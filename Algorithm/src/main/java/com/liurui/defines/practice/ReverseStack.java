package com.liurui.defines.practice;


import java.util.Stack;

/**
 * 一个栈依次压入1,2,3,4,5那么从栈顶到栈底分别为5,4,3,2,1。
 * 将这个栈转置后，从栈顶到栈底为1,2,3,4,5，也就
 * 是实现栈中元素的逆序，但是只能用递归函数来实现，而不能用另外的数据结构。
 */
public interface ReverseStack {
    void reverse(Stack<Integer> stack);
}
