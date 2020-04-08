package com.liurui.answers.structures.array;

import com.liurui.defines.structures.array.FindRotateInflectionPoint;

/**
 * 寻找旋转数组的拐点
 * 原数组1,2,3,4,5,6,7旋转后得到4,5,6,7,1,2,3;我们将新得到的数组为原数组
 * 的旋转数组，同时1为这个新数据的拐点
 * <p>
 * <p>
 * 分析:
 * 分析发现旋转数组由2个排好序的数组组成，可以考虑使用二分查找法
 * <p>
 * 时间复杂度为O(logn)
 */
public class FindRotateInflectionPointImpl implements FindRotateInflectionPoint {

    /**
     * 寻找旋转数组的拐点
     *
     * @param ary 旋转数组
     * @return 旋转数组的拐点
     */
    @Override
    public int find(int[] ary) {
        int begin = 0;
        int end = ary.length - 1;


        while (begin < end) {
            if (end - begin == 1) {
                if (ary[begin] < ary[0]) {
                    return ary[begin];
                } else {
                    return ary[end];
                }
            }

            int mid = (begin + end) / 2;

            if (ary[mid] >= ary[begin]) {
                begin = mid;  //注意，虽然范围缩小了，但是中间值还是包含在范围内的
            } else {
                end = mid;
            }
        }
        return -1;
    }
}
