package com.liurui.templates.structures.string;

import com.liurui.defines.structures.string.ContainsUsingHash;

/**
 * 判断字符串是否包含另一个字符串,使用散列表方式
 * 时间复杂度为O(M+N)
 * 空间复杂度为O(M)
 * M 为a串的长度
 * N为b串的长度
 */
public class ContainsUsingHashImpl  implements ContainsUsingHash {
    @Override
    public boolean contains(String a, String b) {
        return false;
    }
}
