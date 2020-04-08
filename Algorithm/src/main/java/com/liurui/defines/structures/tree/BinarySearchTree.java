package com.liurui.defines.structures.tree;

/**
 * 二叉查找树(BST)
 * <p>
 * 特点：
 * 1. 左子树小于根节点，右子树大于根节点
 * 2. 所有子树都满足第一条规则
 *
 * 查找性能平均为logn,但是最坏的情况依然是n,因为失衡了；可以采用平衡算法(平衡二叉树)使得查找性能为logn
 */
public interface BinarySearchTree<K extends Comparable<K>, V> {
    /**
     * 添加数据
     *
     * @param key  键
     * @param data 数据
     */
    void add(K key, V data);

    /**
     * 查找是否存在特定的键
     *
     * @param key 特定的键
     * @return 是否存在
     */
    boolean contains(K key);

    /**
     * 删除特定的键
     *
     * @param key 特定的键
     */
    void remove(K key);

    /**
     * 打印中序遍历结果
     *
     * @return 值列表
     */
    String printInOrder();

    /**
     * 打印后序遍历结果
     *
     * @return 值列表
     */
    String printPostOrder();
}
