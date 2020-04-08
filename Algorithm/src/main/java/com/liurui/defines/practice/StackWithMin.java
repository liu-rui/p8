package com.liurui.defines.practice;


import com.liurui.defines.structures.Stackable;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 要求：
 * 1、pop、push、getMin操作的时间复杂度都是O(1)
 * 2、设计的栈类型可以输用现成的栈结构
 */
public interface StackWithMin extends Stackable {

    /**
     * 返回最小值
     *
     * @return 最小值
     */
    int getMin();
}
