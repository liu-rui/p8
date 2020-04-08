package com.liurui.templates.structures.tree;


import com.liurui.defines.structures.tree.BlanceBinaryTreeUsingRedBlack;

/**
 * 平衡二叉树,通过红黑树算法实现
 *
 * 插入，删除，查找都是O(logn)
 *
 * 参考:
 * 1. http://www.cnblogs.com/yangecnu/p/Introduce-Red-Black-Tree.html
 */
public class BlanceBinaryTreeUsingRedBlackImpl<K extends Comparable<K>, V> implements BlanceBinaryTreeUsingRedBlack<K , V> {

    @Override
    public void add(K key, V data) {

    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public String printInOrder() {
        return null;
    }

    @Override
    public String printPostOrder() {
        return null;
    }
}
