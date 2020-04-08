package com.liurui.defines.structures.string;

/**
 * 判断字符串是否包含另一个字符串
 */
public interface Contains {
    /**
     * 判断字符串a是否包含字符串b
     * @param a 字符串a
     * @param b 字符串b
     * @return 字符串a是否包含字符串b
     */
    boolean contains(String a, String b);
}
