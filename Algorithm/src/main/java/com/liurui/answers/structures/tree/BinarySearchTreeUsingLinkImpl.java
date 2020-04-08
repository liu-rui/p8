package com.liurui.answers.structures.tree;


import com.liurui.defines.structures.tree.BinarySearchTreeUsingLink;
import com.liurui.defines.structures.tree.BinaryTreeNode;

public class BinarySearchTreeUsingLinkImpl<K extends Comparable<K>, V> implements BinarySearchTreeUsingLink<K, V> {
    private BinaryTreeNode<K, V> root;

    @Override
    public void add(K key, V data) {
        if (root == null) {
            root = new BinaryTreeNode<>(key, data);
        } else {
            BinaryTreeNode<K, V> parent = root;

            while (true) {
                int compare = parent.getKey().compareTo(key);

                switch (compare) {
                    case 0:
                        parent.setData(data);
                        return;
                    case 1:
                        if (parent.getLeft() != null) {
                            parent = parent.getLeft();
                        } else {
                            parent.setLeft(new BinaryTreeNode<>(key, data));
                            return;
                        }
                        break;
                    case -1:
                        if (parent.getRight() != null) {
                            parent = parent.getRight();
                        } else {
                            parent.setRight(new BinaryTreeNode<>(key, data));
                            return;
                        }
                        break;
                }
            }
        }
    }

    @Override
    public boolean contains(K key) {
        BinaryTreeNode<K, V>[] nodeAndParent = getNodeAndParent(key);

        return nodeAndParent[0] != null;
    }

    @Override
    public void remove(K key) {
        BinaryTreeNode<K, V>[] nodeAndParent = getNodeAndParent(key);
        BinaryTreeNode<K, V> node = nodeAndParent[0];
        BinaryTreeNode<K, V> parent = nodeAndParent[1];

        if (node == null) return;
        boolean isLeftLeaf = parent != null && node == parent.getLeft();

        if (node.getLeft() == null && node.getRight() == null) {
            if (root == node) {
                root = null;
            } else {
                if (isLeftLeaf) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
        } else if (node.getLeft() != null && node.getRight() == null) {
            if (root == node) {
                root = node.getLeft();
            } else {
                if (isLeftLeaf) {
                    parent.setLeft(node.getLeft());
                } else {
                    parent.setRight(node.getLeft());
                }
            }
            node.setLeft(null);//释放资源
        } else if (node.getLeft() == null && node.getRight() != null) {
            if (root == node) {
                root = node.getRight();
            } else {
                if (isLeftLeaf) {
                    parent.setLeft(node.getRight());
                } else {
                    parent.setRight(node.getRight());
                }
                node.setRight(null);//释放资源
            }
        } else {
            if (node.getRight().getLeft() == null) {
                node.getRight().setLeft(node.getLeft());

                if (root == node) {
                    root = node.getRight();
                } else {
                    if (isLeftLeaf) {
                        parent.setLeft(node.getRight());
                    } else {
                        parent.setRight(node.getRight());
                    }
                }
            } else {
                BinaryTreeNode<K, V> newNodeParent = node.getRight();
                BinaryTreeNode<K, V> newNode = newNodeParent.getLeft();

                while (newNode.getLeft() != null) {
                    newNodeParent = newNode;
                    newNode = newNode.getLeft();
                }
                newNodeParent.setLeft(newNode.getRight());
                newNode.setLeft(node.getLeft());
                newNode.setRight(node.getRight());

                if (root == node) {
                    root = newNode;
                } else {
                    if (isLeftLeaf) {
                        parent.setLeft(newNode);
                    } else {
                        parent.setRight(newNode);
                    }
                }
            }

            //释放资源
            node.setLeft(null);
            node.setRight(null);
        }
    }

    /**
     * 使用固长数组存储返回值；0：存储查找的节点，1存储父节点
     *
     * @param key
     * @return
     */
    private BinaryTreeNode<K, V>[] getNodeAndParent(K key) {
        BinaryTreeNode<K, V>[] ret = new BinaryTreeNode[2];
        BinaryTreeNode<K, V> parent = null;
        BinaryTreeNode<K, V> node = root;

        while (node != null) {
            int compare = node.getKey().compareTo(key);

            switch (compare) {
                case 0:
                    ret[0] = node;
                    ret[1] = parent;
                case 1:
                    parent = node;
                    node = node.getLeft();
                    break;
                case -1:
                    parent = node;
                    node = node.getRight();
                    break;
            }
        }
        return ret;
    }

    @Override
    public String printInOrder() {
        StringBuilder sb = new StringBuilder();

        printInOrder(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void printInOrder(StringBuilder sb, BinaryTreeNode<K, V> node) {
        if (node == null) return;

        printInOrder(sb, node.getLeft());
        sb.append(String.format("%s,", node.getText()));
        printInOrder(sb, node.getRight());
    }


    @Override
    public String printPostOrder() {
        StringBuilder sb = new StringBuilder();

        printPostOrder(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void printPostOrder(StringBuilder sb, BinaryTreeNode<K, V> node) {
        if (node == null) return;

        printPostOrder(sb, node.getLeft());
        printPostOrder(sb, node.getRight());
        sb.append(String.format("%s,", node.getText()));
    }
}
