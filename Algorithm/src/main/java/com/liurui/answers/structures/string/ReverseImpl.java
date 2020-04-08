package com.liurui.answers.structures.string;

import com.liurui.defines.structures.string.Reverse;

/**
 * 使用对换反转法
 * 时间复杂度O(N)
 */
public class ReverseImpl implements Reverse {
    @Override
    public String reverse(String str, int begin, int end) {
        char[]  chars = str.toCharArray();
        int mid = (end - begin) / 2;

        for (int i = 0; i <= mid; i++) {
            swap(chars , begin + i , end - i);
        }
        return new String(chars);
    }

    private void swap(char[] str, int a, int b) {
        char tmp = str[a];

        str[a] = str[b];
        str[b] = tmp;
    }
}
