package com.liurui.defines.structures;

/***
 * 散列表抽象接口
 */
public interface Hashable {

    /**
     * 设置元素
     *
     * @param key   　键
     * @param value 值
     */
    void put(int key, String value);

    /**
     * 是否包含键
     *
     * @param key 　键
     * @return　如果包含返回真，否则为假
     */
    boolean contains(int key);

    /**
     * 获取值
     *
     * @param key 　键
     * @return　值
     */
    String get(int key);

    /**
     * 删除键
     * @param key　键
     */
    void remove(int key);

    /**
     * 获取元素个数
     * @return　元素个数
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void clear();
}
