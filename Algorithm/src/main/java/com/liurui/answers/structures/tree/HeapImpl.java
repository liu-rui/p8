package com.liurui.answers.structures.tree;


import com.liurui.defines.structures.tree.Heap;

/**
 * 堆
 * 是一种特殊的完全二叉树
 * 分为最大堆（第一个元素最大）和最小堆（第一个最小）
 * 特点：
 * 1. 对于最大堆来说，第i个元素一定大于等于2i和2i+1元素
 */
public class HeapImpl implements Heap {
    int[] ret;
    private boolean big;

    /**
     * 初始化堆
     *
     * @param big  如果为真创建大顶堆，否则创建小顶堆
     * @param data 数据
     */
    @Override
    public void init(boolean big, int[] data) {
        this.big = big;
        ret = new int[data.length + 1];

        for (int item : data) {
            add(item);
        }
    }

    private void add(int item) {
        ret[0]++;
        int i = ret[0];

        while (i > 1 && compare(item, ret[i / 2]) > 0) {
            ret[i] = ret[i / 2];
            i /= 2;
        }
        ret[i] = item;
    }

    private int compare(int a, int b) {
        return big ? Integer.compare(a, b) : Integer.compare(b, a);
    }

    /**
     * 弹出最顶的数据
     *
     * @return 数据
     */
    @Override
    public int pop() {
        if (ret[0] == 0) throw new IndexOutOfBoundsException();
        int data = ret[1];
        int item = ret[ret[0]];
        int parent = 1;
        int child = 2;

        ret[0]--;

        while (child <= ret[0]) {
            if ((child + 1) <= ret[0] && compare(ret[child + 1], ret[child]) > 0)
                child++;

            if (compare(item, ret[child]) > 0) {
                break;
            } else {
                ret[parent] = ret[child];
                parent = child;
                child *= 2;
            }
        }
        ret[parent] = item;
        return data;
    }

    /**
     * 堆成员个数
     *
     * @return 成员个数
     */
    @Override
    public int size() {
        return ret[0];
    }
}