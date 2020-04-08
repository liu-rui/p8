package com.liurui.defines.questions;

/***
 * 请求两个数组的差集
 * 时间复杂度为　nLOGn（a的排序） + mLOGm（b的排序） + (n + m)（差集计算）
 * 最终时间复杂度为: O(nLOGn)
 */
public interface DifferenceSet {
     int[] get(int[] a, int[] b);
}
