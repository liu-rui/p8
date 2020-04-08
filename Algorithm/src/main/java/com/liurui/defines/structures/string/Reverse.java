package com.liurui.defines.structures.string;

/**
 * 反转字符串
 */
public interface Reverse {
    /**
     * 反转整个字符串
     *
     * @param str 字符串
     * @return 反转后的字符串
     */
    default String reverse(String str) {
        return reverse(str, 0, str.length() - 1);
    }

    /**
     * 反转字符串
     *
     * @param str   字符串
     * @param begin 起始位置
     * @param end   终止位置
     * @return 反转后的字符串
     */
    String reverse(String str, int begin, int end);
}
