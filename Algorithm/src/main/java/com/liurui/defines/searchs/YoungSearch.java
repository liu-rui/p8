package com.liurui.defines.searchs;

/***
 * 定位查找杨氏矩阵
 */
public interface YoungSearch {

    /**
     * 判断特定值是否存在于杨氏矩阵
     * @param data 杨氏矩阵
     * @param item 特定值
     * @return 是否存在
     */
    boolean search(int[][] data, int item);
}
