package com.liurui.defines.structures.tree;

/**
 * 堆
 */
public interface Heap {

    /**
     * 初始化堆
     *
     * @param big  如果为真创建大顶堆，否则创建小顶堆
     * @param data 数据
     */
    void init(boolean big, int[] data);

    /**
     * 弹出最顶的数据
     *
     * @return 数据
     */
    int pop();

    /**
     * 堆成员个数
     *
     * @return 成员个数
     */
    int size();
}
