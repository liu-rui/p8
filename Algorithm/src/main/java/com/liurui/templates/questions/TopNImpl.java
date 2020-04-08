package com.liurui.templates.questions;


import com.liurui.defines.questions.TopN;

public class TopNImpl  implements TopN {
    /**
     *  求数组中最大的前num个数
     * @param data  数组
     * @param num 需要返回的数量
     * @return 最大数列表，结果从大到小排列
     */
    @Override
    public int[] max(int[] data, int num){
        return new int[0];
    }

    /**
     *  求数组中最小的前num个数
     * @param data  数组
     * @param num 需要返回的数量
     * @return 最小数列表，结果从小到大排列
     */
    @Override
    public int[] min(int[] data, int num){
        return new int[0];
    }
}
