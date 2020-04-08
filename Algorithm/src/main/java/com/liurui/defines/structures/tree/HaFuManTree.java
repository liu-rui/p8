package com.liurui.defines.structures.tree;

import java.util.HashMap;

/**
 * 哈夫曼树
 */
public interface HaFuManTree {
    /**
     * 生成哈夫曼编码
     * @param keys 字母的出现频率
     */
    void generic(HashMap<String, Integer> keys);

    /**
     * 获取特定字母的哈夫曼编码
     * @param key 字母
     * @return 哈夫曼编码
     */
    String getCode(String key);

    /**
     * 运用哈夫曼编码对文本进行编码
     * @param data 文本
     * @return 编码后的文本
     */
    String encode(String data);

    /**
     * 运用哈夫曼编码对文本进行解码
     * @param data 文本
     * @return 解码后的文本
     */
    String decode(String data);
}
