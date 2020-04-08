package com.liurui.defines.structures.tree;

/**
 * 平衡二叉树
 * 满足一下2个条件：
 * 1. 左右子树都是高度平衡的二叉树
 * 2. 且左右子树高度之差绝对值不大于1
 * <p>
 * <p>
 * 平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树
 */
public interface BlanceBinaryTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {
}
