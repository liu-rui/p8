package com.liurui.defines.questions;

/**
 * 求数组中最大的前num个数
 */
public interface TopN {
    /**
     *  求数组中最大的前num个数
     * @param data  数组
     * @param num 需要返回的数量
     * @return 最大数列表，结果从大到小排列
     */
    int[] max(int[] data, int num);

    /**
     *  求数组中最小的前num个数
     * @param data  数组
     * @param num 需要返回的数量
     * @return 最小数列表，结果从小到大排列
     */
    int[] min(int[] data, int num);
}
