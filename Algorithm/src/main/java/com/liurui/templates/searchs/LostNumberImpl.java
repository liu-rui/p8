package com.liurui.templates.searchs;


import com.liurui.defines.searchs.LostNumber;

/**
 * 缺失的数
 * 题目：
 * 有100个连续的数字(1-100),现在对他们随机打乱顺序，然后
 * 随机取出一个数字，那么如何在这个数组里面快速找出缺失的数字
 * 1. 如果使用排序，由于次数超过一半，中间的数就是结果，但是性能受到
 * 排序的影响，时间复杂度最多也就是O(NlogN)
 * 2. 使用散列表的话，时间复杂度为O(N),但是空间复杂度为O(N)
 * <p>
 * 是有还有更好的方法？
 */
public class LostNumberImpl implements LostNumber {
    /**
     * 查找缺失的数
     *
     * @param ary 随机打乱顺序的数组
     * @return 缺失的数
     */
    @Override
    public int find(int[] ary) {
        return 0;
    }
}
