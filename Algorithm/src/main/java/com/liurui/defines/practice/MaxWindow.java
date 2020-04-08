package com.liurui.defines.practice;

/**
 * 生成窗口最大值数组
 * 有数组[2，3，4，3，2，5，6]，窗口尺寸为3，设窗口滑动方向为从左向右。得到下列路径图
 * [2 3 4] 3 2 5 6
 * 2 [3 4 3] 2 5 6
 * 2 3 [4 3 2] 5 6
 * 2 3 4 [3 2 5] 6
 * 2 3 4 3 [2 5 6]
 * 沿路径取窗口最大值得数组[4，4，4，5，6]，要求在长
 * 度为N的数组和w指定的窗口尺寸在求得窗口最大值数组。
 */
public interface MaxWindow {
    /**
     * 生成窗口最大值数组
     * @param ary 数组
     * @param w 窗口尺寸
     * @return 窗口最大值数组
     */
    int[] get(int[] ary, int w);
}
