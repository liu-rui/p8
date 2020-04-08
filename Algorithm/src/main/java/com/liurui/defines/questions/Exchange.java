package com.liurui.defines.questions;

/**
 * 交换两个变量的值，不能使用其它的变量
 */
public interface Exchange {

    /**
     * 交换两个变量的值，不能使用其它的变量
     *
     * @param ary 为了简单,使用长度为2的数组存储来代替两个变量
     * @return  交换后的数组
     */
    int[] exec(int[] ary);
}
