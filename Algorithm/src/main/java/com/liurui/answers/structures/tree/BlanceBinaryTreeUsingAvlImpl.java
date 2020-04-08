package com.liurui.answers.structures.tree;


import com.liurui.defines.structures.tree.BlanceBinaryTreeUsingAvl;

/**
 * 平衡二叉树,通过AVL算法实现
 * <p>
 * 插入，删除，查找都是O(logN)
 */
public class BlanceBinaryTreeUsingAvlImpl<K extends Comparable<K>, V> implements BlanceBinaryTreeUsingAvl<K, V> {
    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V data;
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;

        public Node(K key, V data) {
            this.key = key;
            this.data = data;
        }

        public Node(K key, V data, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public String getText() {
            return String.format("[%s:%s]", key, height);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", height=" + height +
                    '}';
        }
    }

    Node<K, V> root;

    @Override
    public void add(K key, V data) {
        root = add(root, key, data);
    }


    private Node<K, V> add(Node<K, V> node, K key, V data) {
        if (node == null) {
            return new Node<>(key, data);
        }
        int compare = node.key.compareTo(key);

        if (compare == 0) {
            node.data = data;
            return node;
        } else if (compare > 0) {
            node.left = add(node.left, key, data);
        } else {
            node.right = add(node.right, key, data);
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node<K, V> balance(Node<K, V> node) {
        //Left?
        if (blanceFactor(node) > 1) {
            //leftRight
            if (blanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (blanceFactor(node) < -1) { //Right?
            //RightLeft
            if (blanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private int blanceFactor(Node<K, V> node) {
        return height(node.left) - height(node.right);
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> newNode = node.right;

        node.right = newNode.left;
        newNode.left = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));
        return newNode;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> newNode = node.left;

        node.left = newNode.right;
        newNode.right = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));
        return newNode;
    }


    private int height(Node<K, V> node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        int compare = node.key.compareTo(key);

        switch (compare) {
            case 0:
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    Node<K, V> newNode = min(node.right);
                    newNode.right = deleteMin(node.right);
                    newNode.left = node.left;
                    node = newNode;
                }
                break;
            case 1:
                node.left = remove(node.left, key);
                break;
            case -1:
                node.right = remove(node.right, key);
                break;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;

        node.left = deleteMin(node.left);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }


    @Override
    public String printInOrder() {
        StringBuilder sb = new StringBuilder();

        printInOrder(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void printInOrder(StringBuilder sb, Node<K, V> node) {
        if (node == null) {
            return;
        }

        printInOrder(sb, node.left);

        sb.append(String.format("%s,", node.getText()));
        printInOrder(sb, node.right);
    }

    @Override
    public String printPostOrder() {
        StringBuilder sb = new StringBuilder();

        printPostOrder(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void printPostOrder(StringBuilder sb, Node<K, V> node) {
        if (node == null) {
            return;
        }
        printPostOrder(sb, node.left);
        printPostOrder(sb, node.right);
        sb.append(String.format("%s,", node.getText()));
    }
}