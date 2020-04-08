package com.liurui.defines.structures.tree;

/**
 * B+树
 *m阶B+树
 *
 * 特点：
 * 1. 每个结点至多有m个子结点
 * 2. 除根结点和叶结点外，其它每个结点至少有ceing(m/2)个子结点
 * 3.根结点至少有两个子结点
 * 4. 有k个子结点的结点必有k个关键码
 *
 * 参考：
 * 1. https://www.cnblogs.com/vincently/p/4526560.html
 */
public interface BPlusTree {
}
