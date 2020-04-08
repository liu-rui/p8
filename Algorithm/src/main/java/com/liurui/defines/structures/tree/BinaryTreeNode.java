package com.liurui.defines.structures.tree;

public class BinaryTreeNode<K extends Comparable<K>, V> implements Comparable<BinaryTreeNode<K, V>> {
    private K key;
    private V data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(K key, V data) {
        this.key = key;
        this.data = data;
    }

    public BinaryTreeNode(K key, V data, BinaryTreeNode left, BinaryTreeNode right) {
        this.key = key;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public String getText() {
        return String.format("[%s:%s]", key, data);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof BinaryTreeNode)) return false;
        BinaryTreeNode<K, V> another = (BinaryTreeNode<K, V>) obj;

        return this.key.equals(another.getKey());
    }

    @Override
    public int compareTo(BinaryTreeNode<K, V> o) {
        if (this == o) return 0;
        if (o == null) return 1;

        return this.getKey().compareTo(o.getKey());
    }
}
