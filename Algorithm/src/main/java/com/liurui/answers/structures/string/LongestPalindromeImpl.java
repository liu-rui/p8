package com.liurui.answers.structures.string;


import com.liurui.defines.structures.string.LongestPalindrome;

/**
 * 时间复杂度为O(N^2)
 *
 *
 * 思想：
 * 1. 首先循环每一个字符，作为回文子串的中点
 * 2. 里面再来个循环(需要考虑奇数和偶数情况，所以是两个循环)，判断是否是回文，如果发现大的更新max
 */
public class LongestPalindromeImpl implements LongestPalindrome {
    @Override
    public String search(String str) {
        int current = 0, max = 0, begin = 0, len = str.length();

        //i作为回文子串的中点
        for (int i = 0; i < len; i++) {
            //考虑回文子串为奇数长度的情况
            for (int j = 0; i - j >= 0 && i + j < len; j++) {
                if (str.charAt(i - j) != str.charAt(i + j)) {
                    break;
                }
                current = j * 2 + 1;
            }

            if (current > max) {
                begin = i - current / 2;
                max = current;
            }

            //考虑回文子串为偶数长度的情况
            for (int j = 0; i - j >= 0 && i + j + 1 < len; j++) {
                if (str.charAt(i - j) != str.charAt(i + j + 1)) {
                    break;
                }
                current = j * 2 + 2;
            }
            if (current > max) {
                begin = i - current / 2;
                max = current;
            }
        }
        return str.substring(begin , begin + max);
    }
}
