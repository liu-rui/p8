package com.liurui.answers.questions;

import com.liurui.defines.questions.Exchange;

/**
 * 交换两个变量的值，不能使用其它的变量
 */
public class ExchangeImpl implements Exchange {

    /**
     * 交换两个变量的值，不能使用其它的变量
     *
     * @param ary 为了简单,使用长度为2的数组存储来代替两个变量
     * @return 交换后的数组
     */
    @Override
    public int[] exec(int[] ary) {
        return answer2(ary);
    }

    /**
     * 方案1: 使用先+,后-
     *
     * @param ary
     * @return
     */
    private int[] answer1(int[] ary) {
        ary[0] += ary[1];
        ary[1] = ary[0] - ary[1];
        ary[0] -= ary[1];
        return ary;
    }

    /**
     * 方案2: 使用异或，反反得正
     *
     * @param ary
     * @return
     */
    private int[] answer2(int[] ary) {
        ary[0] ^= ary[1];
        ary[1] ^= ary[0];
        ary[0] ^= ary[1];
        return ary;
    }
}
