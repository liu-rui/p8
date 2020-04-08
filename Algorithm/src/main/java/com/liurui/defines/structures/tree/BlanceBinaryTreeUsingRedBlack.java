package com.liurui.defines.structures.tree;

/**
 * 平衡二叉树,通过红黑树算法实现
 * 红黑树特点：
 * 1. 每个结点是黑色或者红色
 * 2. 根结点是黑色
 * 3. 每个叶子结点是黑色
 * 4. 如果一个结点为红色的，则它的子结点为黑色
 * 5. 从一个结点到该结点的子孙结点的所有路径上包含相同数目的黑色结点
 *
 * 插入，删除，查找都是O(logn)
 */
public interface BlanceBinaryTreeUsingRedBlack<K extends Comparable<K>, V> extends BlanceBinaryTree<K, V> {
}
