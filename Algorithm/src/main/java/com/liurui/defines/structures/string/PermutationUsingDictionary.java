package com.liurui.defines.structures.string;

/**
 * 计算出字符的所有排列情况,使用字典序的方式
 * <p>
 * 时间复杂度为O(N!)
 * <p>
 * 思想：
 * 1. 从右向左找到第一个位置k,其值比右边的数中的任意一个数小，其实就是
 * 在从右向左的升序中第一个被打乱顺序的数
 * 2. 找到k右边的序列中做右边的一个比k上数大的数，位置为y
 * 3. 交换k与y上的数
 * 4. 把k右侧的序列反转
 * <p>
 * <p>
 * 参考:
 * 1. 《轻松学算法》 p279
 */
public interface PermutationUsingDictionary extends Permutation {
}
