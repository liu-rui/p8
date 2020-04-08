package com.liurui.defines.practice;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 题目:
 *一个栈中元素的类型为整形，现在想将该栈从顶到底按从大到小
 * 的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但
 * 不能申请额外的数据结构。如何完成排序？
 */
public interface StackSort {
    /**
     * 排序，栈从顶到底按从大到小
     * @param stack 未排序的栈
     */
    void  sort(Stack<Integer> stack);
}
