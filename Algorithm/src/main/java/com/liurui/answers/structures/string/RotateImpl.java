package com.liurui.answers.structures.string;

import com.liurui.defines.structures.string.Rotate;

/**
 * 使用三步旋转法
 * 时间复杂度O(N)
 * <p>
 * 过程:
 * 1. 先旋转左侧
 * 2. 旋转右侧
 * 3.旋转整体
 * <p>
 * 例如： abcdefg ,旋转的下标为3
 * <p>
 * 1.先旋转abcd,结果为dcba
 * 2.旋转efg,结果为gfe
 * 3.旋转整体dcbagfe，结果为efgabcd
 */
public class RotateImpl implements Rotate {
    @Override
    public String rotate(String str, int index) {
        char[] chars = str.toCharArray();

        reverse(chars, 0, index);
        reverse(chars, index + 1, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int begin, int end) {
        int mid = (end - begin) / 2;

        for (int i = 0; i <= mid; i++) {
            swap(chars, begin + i, end - i);
        }
    }

    private void swap(char[] chars, int a, int b) {
        char tmp = chars[a];

        chars[a] = chars[b];
        chars[b] = tmp;
    }
}
