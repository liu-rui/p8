package com.liurui.answers.structures.array;


import com.liurui.defines.structures.array.FindMaxSumSubArrayUsingDynamicPlanning;

import java.util.Arrays;

/**
 * 找出连加值最大的子数组,使用动态规划法
 * 子数组：数组中连续的一部分
 * 时间复杂度为O(N)
 * 解法：
 * 利用动态规划的思想。
 * 设f(n)表示以a[n]为子序列最后一个元素的最大和，则可以有下面的规则：
 *（1）当f(n-1)<0时，f(n)=a[n];
 *（2）当n!=0且f(n-1)>0时，f(n)=f(n-1)+a[n]。
 * 用一个nGreatestNum来记录最大值，每次与f(n)进行比较，不断更新即可。
 */
public class FindMaxSumSubArrayUsingDynamicPlanningImpl implements FindMaxSumSubArrayUsingDynamicPlanning {

    /**
     * 找出连加值最大的子数组
     *
     * @param ary 原数组
     * @return 子数组
     */
    @Override
    public int[] find(int[] ary) {
        int begin = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int lastMax = Integer.MIN_VALUE;
        int lastBegin = 0;

        for (int i = 0; i < ary.length; i++) {
            if (lastMax > 0) {
                lastMax += ary[i];
            } else {
                lastBegin = i;
                lastMax = ary[i];
            }

            if (lastMax > max) {
                max = lastMax;
                begin = lastBegin;
                end = i;
            }
        }
        return Arrays.copyOfRange(ary, begin, end + 1);
    }
}
