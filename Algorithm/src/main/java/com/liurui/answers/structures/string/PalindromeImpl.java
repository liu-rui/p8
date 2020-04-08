package com.liurui.answers.structures.string;

import com.liurui.defines.structures.string.Palindrome;

/**
 * 时间复杂度为O(N)
 */
public class PalindromeImpl implements Palindrome {
    @Override
    public boolean isPalindrome(String str) {
        int len = str.length();
        int mid = len / 2;

        for (int i = 0; i < mid; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
