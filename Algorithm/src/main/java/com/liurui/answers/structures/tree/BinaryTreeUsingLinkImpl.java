package com.liurui.answers.structures.tree;

import com.liurui.defines.structures.tree.BinaryTreeNode;
import com.liurui.defines.structures.tree.BinaryTreeUsingLink;

import java.util.LinkedList;

public class BinaryTreeUsingLinkImpl<K extends Comparable<K>, V> implements BinaryTreeUsingLink<K , V> {
    private BinaryTreeNode root;


    @Override
    public void setRoot(BinaryTreeNode<K,V> root) {
        this.root = root;
    }

    @Override
    public BinaryTreeNode<K,V> getRoot() {
        return root;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return getSize(root);
    }

    public int getSize(BinaryTreeNode<K,V> node) {
        if (node == null) return 0;

        return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
    }

    @Override
    public int getSize(int level) {
        return getSize(root, 1, level);
    }

    private int getSize(BinaryTreeNode<K,V> node, int currentLevel, int level) {
        if (node == null || currentLevel > level) return 0;
        if (currentLevel == level) return 1;

        return getSize(node.getLeft(), currentLevel + 1, level) +
                getSize(node.getRight(), currentLevel + 1, level);
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode<K,V> node) {
        if (node == null) return 0;

        return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }


    @Override
    public BinaryTreeNode getParent(BinaryTreeNode<K,V> node) {
        if (root == null || node == null) return null;

        return getParent(null, root, node);
    }

    public BinaryTreeNode getParent(BinaryTreeNode<K,V> parent, BinaryTreeNode<K,V> current, BinaryTreeNode<K,V> node) {
        if (current == null) return null;
        if (current == node) return parent;

        BinaryTreeNode ret = getParent(current, current.getLeft(), node);
        return ret != null ? ret : getParent(current, current.getRight(), node);
    }


    @Override
    public BinaryTreeNode<K,V> getLeftNode(BinaryTreeNode<K,V> node) {
        if (node == null) return null;
        return node.getLeft();
    }

    @Override
    public BinaryTreeNode<K,V> getRightNode(BinaryTreeNode<K,V> node) {
        if (node == null) return null;
        return node.getRight();
    }

    @Override
    public void insertLeft(BinaryTreeNode<K,V> parent, BinaryTreeNode<K,V> node) {
        if (parent == null) throw new IllegalArgumentException();
        parent.setLeft(node);
    }

    @Override
    public void insertRight(BinaryTreeNode<K,V> parent, BinaryTreeNode<K,V> node) {
        if (parent == null) throw new IllegalArgumentException();
        parent.setRight(node);
    }

    @Override
    public String printPreOrder() {
        StringBuilder sb = new StringBuilder();

        printPreOrder(sb, root);
        return sb.toString();
    }

    public void printPreOrder(StringBuilder sb, BinaryTreeNode<K,V> node) {
        if (node == null) return;

        if (sb.length() != 0) {
            sb.append(",");
        }
        sb.append(node.getText());
        printPreOrder(sb, node.getLeft());
        printPreOrder(sb, node.getRight());
    }


    @Override
    public String printInOrder() {
        StringBuilder sb = new StringBuilder();

        printInOrder(sb, root);
        return sb.toString();
    }

    public void printInOrder(StringBuilder sb, BinaryTreeNode<K,V> node) {
        if (node == null) return;

        printInOrder(sb, node.getLeft());
        if (sb.length() != 0) {
            sb.append(",");
        }
        sb.append(node.getText());
        printInOrder(sb, node.getRight());
    }


    @Override
    public String printPostOrder() {
        StringBuilder sb = new StringBuilder();

        printPostOrder(sb, root);
        return sb.toString();
    }

    public void printPostOrder(StringBuilder sb, BinaryTreeNode<K,V> node) {
        if (node == null) return;

        printPostOrder(sb, node.getLeft());
        printPostOrder(sb, node.getRight());
        if (sb.length() != 0) {
            sb.append(",");
        }
        sb.append(node.getText());
    }

    @Override
    public String printLevelOrder() {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        LinkedList<BinaryTreeNode<K,V>> nodes = new LinkedList<>();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            BinaryTreeNode<K,V> node = nodes.poll();

            sb.append(node.getText());
            sb.append(",");

            if (node.getLeft() != null) {
                nodes.add(node.getLeft());
            }

            if (node.getRight() != null) {
                nodes.add(node.getRight());
            }
        }
        sb.deleteCharAt(sb.length() -1);
        return sb.toString();
    }
}
