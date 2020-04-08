package com.liurui.templates.structures.tree;

import com.liurui.defines.structures.tree.HaFuManTree;

import java.util.HashMap;

/**
 * 哈夫曼树
 */
public class HaFuManTreeImpl implements HaFuManTree {
    /**
     * 生成哈夫曼编码
     *
     * @param keys 字母的出现频率
     */
    @Override
    public void generic(HashMap<String, Integer> keys) {

    }


    /**
     * 获取特定字母的哈夫曼编码
     *
     * @param key 字母
     * @return 哈夫曼编码
     */
    @Override
    public String getCode(String key) {
        return "";
    }

    /**
     * 运用哈夫曼编码对文本进行编码
     *
     * @param data 文本
     * @return 编码后的文本
     */
    @Override
    public String encode(String data) {
        return "";
    }

    /**
     * 运用哈夫曼编码对文本进行解码
     *
     * @param data 文本
     * @return 解码后的文本
     */
    @Override
    public String decode(String data) {
        return "";
    }
}
