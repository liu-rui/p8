package com.liurui.defines.structures.tree;

/**
 * 平衡二叉树,通过AVL算法实现
 * <p>
 * 插入，删除，查找都是O(logn)
 * <p>
 * 好文： http://blog.csdn.net/javazejian/article/details/53892797
 */
public interface BlanceBinaryTreeUsingAvl<K extends Comparable<K>, V> extends BlanceBinaryTree<K, V> {
}
