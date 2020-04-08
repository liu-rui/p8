package com.liurui.templates.structures.array;

import com.liurui.defines.structures.array.RandomArray;

import java.util.Arrays;

/**
 * 给定一个整型数组，把数组随机打乱顺序
 * <p>
 * 时间复杂度为O(N)
 * <p>
 * 思想：
 * 利用插入排序思想，将数组分为两段
 * 左侧一段为已经打乱的数据
 * 右侧一段为待打乱的数据
 * 随机产生的数为待打乱的数据的索引
 */
public class RandomArrayImpl implements RandomArray {

    /**
     * 给定一个整型数组，把数组随机打乱顺序
     *
     * @param ary 整型数组
     * @return 打乱顺序的整型数组
     */
    @Override
    public int[] random(int[] ary) {
        return ary;
    }

    public static void main(String[] args) {
        RandomArrayImpl array = new RandomArrayImpl();

        System.out.println(Arrays.toString(array.random(new int[]{1, 2, 3, 4, 5, 6})));
    }
}
