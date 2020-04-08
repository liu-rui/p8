package com.liurui.defines.questions;

/**
 * 找出两个数中最大的数，不允许使用比较运算符
 */
public interface MaxNumber {
    /**
     * 找出两个数中最大的数，不允许使用比较运算符
     * @param ary 为了简单,使用长度为2的数组存储来代替两个变量
     * @return 两个数中最大的数
     */
    int find(int[] ary);
}
