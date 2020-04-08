package com.liurui.defines.structures.string;

/**
 * 判断字符串是否是回文
 * 回文是指不管正着读还是反着读都是一样的，如abcba
 */
public interface Palindrome {
    /**
     * 判断字符串是否是回文
     *
     * @param str 字符串
     * @return 是否是回文
     */
    boolean isPalindrome(String str);
}