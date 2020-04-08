package com.liurui.templates.structures.tree;


import com.liurui.defines.structures.tree.BinaryTreeNode;
import com.liurui.defines.structures.tree.BinaryTreeUsingLink;

public class BinaryTreeUsingLinkImpl<K extends Comparable<K>, V> implements BinaryTreeUsingLink<K, V> {

    @Override
    public void setRoot(BinaryTreeNode<K, V> root) {

    }

    @Override
    public BinaryTreeNode<K, V> getRoot() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getSize(int level) {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public BinaryTreeNode getParent(BinaryTreeNode<K,V> node) {
        return null;
    }

    @Override
    public BinaryTreeNode<K, V> getLeftNode(BinaryTreeNode<K, V> node) {
        return null;
    }

    @Override
    public BinaryTreeNode<K, V> getRightNode(BinaryTreeNode<K, V> node) {
        return null;
    }

    @Override
    public void insertLeft(BinaryTreeNode<K, V> parent, BinaryTreeNode<K, V> node) {

    }

    @Override
    public void insertRight(BinaryTreeNode<K, V> parent, BinaryTreeNode<K, V> node) {

    }

    @Override
    public String printPreOrder() {
        return null;
    }

    @Override
    public String printInOrder() {
        return null;
    }

    @Override
    public String printPostOrder() {
        return null;
    }

    @Override
    public String printLevelOrder() {
        return null;
    }
}
