package com.liurui.defines.structures.string;

/**
 * 计算出字符的所有排列情况
 *
 *
 * 如xyz的排列情况有:
 * xyz,xzy,yxz,yzx,zxy,zyx
 *
 * 个数为 N!
 */
public interface Permutation {

    /**
     * 输出字符所有的排列情况
     *
     * @param str 参与排列的字符
     * @return 字符所有的排列情况
     */
    String[] permutation(String str);
}
