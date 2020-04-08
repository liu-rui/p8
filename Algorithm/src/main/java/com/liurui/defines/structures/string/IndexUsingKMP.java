package com.liurui.defines.structures.string;

/**
 * 运用KMP方法，判断字符串在原字符串的索引位置
 * 时间复杂度为O(n+m)
 * n为原字符串的长度
 * m为字符串的长度
 */
public interface IndexUsingKMP {
    /**
     * 运用KMP方法，判断字符串在原字符串的索引位置
     *
     * @param source 原字符串
     * @param str    字符串
     * @param pos    起始位置
     * @return 索引位置，不包含是返回-1
     */
    int index(int source, int str, int pos);

    /**
     * 获取字符串的next数组
     *
     * @param str 字符串
     * @return next数组
     */
    int[] getNext(String str);
}
