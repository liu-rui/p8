package com.liurui.defines.structures.string;

/**
 * 旋转字符串
 * <p>
 * 如: abcdefg ,旋转的下标为3
 * 结果为: efgabcd
 * <p>
 * 就是将下标后面的数放在最前面
 */
public interface Rotate {
    /**
     * 旋转字符串
     * @param str 待旋转的字符串
     * @param index 旋转的下标
     * @return 旋转后的字符串
     */
    String rotate(String str, int index);
}
