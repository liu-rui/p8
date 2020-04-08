package com.liurui.answers.practice;

import com.liurui.defines.practice.StackSort;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 题目:
 * 一个栈中元素的类型为整形，现在想将该栈从顶到底按从大到小
 * 的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但
 * 不能申请额外的数据结构。如何完成排序？
 */
public class StackSortImpl implements StackSort {
    /**
     * 排序，栈从顶到底按从大到小
     *
     * @param stack 未排序的栈
     */
    @Override
    public void sort(Stack<Integer> stack) {
        answer1(stack);
    }


    /**
     * 方案1: 申请一个辅助栈,该栈从顶到底按从大到小。
     * 然后循环导入目标栈
     *
     * @param stack
     */
    private void answer1(Stack<Integer> stack) {
        Stack<Integer> tmp = new Stack<>();

        while (!stack.empty()) {
            Integer item = stack.pop();

            if (tmp.empty() || tmp.peek() >= item) {
                tmp.push(item);
            } else {
                while (!tmp.empty() && tmp.peek() < item) {
                    stack.push(tmp.pop());
                }
                tmp.push(item);
            }
        }

        while (!tmp.empty()) {
            stack.push(tmp.pop());
        }
    }


    /**
     * 方案2:使用递归的方式实现
     *
     * @param stack
     */
    private void answer2(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        int max = max(stack);

        sort(stack);
        stack.push(max);
    }

    private int max(Stack<Integer> stack) {
        Integer ret = stack.pop();

        if (stack.empty()) {
            return ret;
        } else {
            int max = max(stack);

            if (max > ret) {
                stack.push(ret);
                return max;
            } else {
                stack.push(max);
                return ret;
            }
        }
    }
}
