package com.liurui.defines.structures.string;

/**
 * 计算出字符的所有排列情况,使用固定位的方式
 *  时间复杂度为O(N!)
 * 思想：
 * 先固定好前面的，然后后面的进行交换，当全部排完之后把前一位与
 * 后面的元素进行交换，继续全排列，在当前变化的几位都排完了之
 * 后，再往前新增一位交换位参与到排列中
 */
public interface PermutatioUsingFixPosition extends Permutation {
}
