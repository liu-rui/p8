package com.liurui.answers.practice;

import com.liurui.defines.practice.MaxTree;

/**
 * 构造数组的MaxTree
 * 一个数组的MaxTree定义如下：
 * 1，数组必须没有重复元素；
 * 2，MaxTree是一棵二叉树，数组的每一个值对应一个二叉树节点；
 * 3，包括MaxTree树在内并且在其中的每一颗子树上，值最大的节点都是树的头；
 * 给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree的函数
 * 要求:
 * 如果数组长度为N，时间复杂度O(N)，额外空间复杂度O(N)。
 */
public class MaxTreeImpl implements MaxTree {


    /**
     * 构造数组的MaxTree
     *
     * @param ary 数组
     * @return MaxTree
     */
    @Override
    public Node getMaxTree(int[] ary) {
        if (ary == null) return null;
        Node ret = null;

        for (int item : ary) {

        }
        return ret;
    }
}
