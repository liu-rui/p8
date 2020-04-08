package com.liurui.answers.structures.string;


import com.liurui.defines.structures.string.ContainsUsingHash;

import java.util.HashSet;

/**
 * 判断字符串是否包含另一个字符串,使用散列表方式
 * 时间复杂度为O(M+N)
 * 空间复杂度为O(M)
 * M 为a串的长度
 * N为b串的长度
 */
public class ContainsUsingHashImpl implements ContainsUsingHash {
    @Override
    public boolean contains(String a, String b) {
        HashSet<Character> set = new HashSet<>();
        int lenM = a.length();

        for (int i = 0; i < lenM; i++) {
            set.add(a.charAt(i));
        }

        int lenN = b.length();

        for (int i = 0; i < lenN; i++) {
            if (!set.contains(b.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
