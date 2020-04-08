package com.liurui.defines.sorts;

/***
 * 排序接口
 */
public interface Sortable {
    /**
     * 排序
     * TODO: 目前只支持升序，以后会支持降序
     *
     * @param data 数据
     * @return 排序后的数据
     */
    int[] sort(int[] data);
}
