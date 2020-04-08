package com.liurui.answers.structures.tree;

import com.liurui.defines.structures.tree.HaFuManTree;

import java.util.HashMap;

/**
 * 哈夫曼树
 */
public class HaFuManTreeImpl implements HaFuManTree {
    private static class Node implements Comparable<Node> {
        private String key;
        private int frequency;
        private Node left;
        private Node right;

        public Node(String key, int frequency) {
            this.key = key;
            this.frequency = frequency;
        }

        public Node(String key, int frequency, Node left, Node right) {
            this.key = key;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) return 1;
            int ret = Integer.compare(frequency, o.frequency);

            return ret == 0 ? key.compareToIgnoreCase(o.key) : ret;
        }

        @Override
        public String toString() {
            return "{" +
                    "k:'" + key + '\'' +
                    ", f:" + frequency +
                    '}';
        }
    }

    private static class MinHeap {
        Node[] heap;
        int size = 0;

        public MinHeap(HashMap<String, Integer> keys) {
            if (keys == null || keys.size() == 0) {
                throw new IllegalArgumentException();
            }
            heap = new Node[keys.size()];
            keys.forEach((k, v) -> push(new Node(k, v)));
        }

        public void push(Node node) {
            int i = size;

            while (i > 0 && i / 2 >= 0 && heap[i / 2].compareTo(node) > 0) {
                heap[i] = heap[i / 2];
                i /= 2;
            }
            heap[i] = node;
            size++;
        }

        public Node pop() {
            if (size == 0) throw new IndexOutOfBoundsException();
            Node ret = heap[0];
            Node item = heap[size - 1];

            size--;

            int parent = 0;
            int child = 1;

            while (child < size) {
                if (child + 1 < size && heap[child].compareTo(heap[child + 1]) > 0) {
                    child++;
                }

                if (heap[child].compareTo(item) > 0) {
                    break;
                } else {
                    heap[parent] = heap[child];
                    parent = child;
                    child *= 2;
                }
            }
            heap[parent] = item;
            return ret;
        }

        public  int getSize(){
            return size;
        }
    }

    private HashMap<String, String> hashByKey = new HashMap<>();
    private HashMap<String, String> hashByCode = new HashMap<>();


    /**
     * 生成哈夫曼编码
     * 由于这里使用了最小堆来存储中间结果，总体的时间复杂度为O(logN)
     *
     * @param keys 字母的出现频率
     */
    @Override
    public void generic(HashMap<String, Integer> keys) {
        if (keys == null || keys.size() == 0) {
            throw new IllegalArgumentException();
        }
        MinHeap minHeap = new MinHeap(keys);

        while (minHeap.getSize() > 1) {
            Node left = minHeap.pop();
            Node right = minHeap.pop();

            minHeap.push(new Node(left.key + right.key, left.frequency + right.frequency, left, right));
        }
        generic(minHeap.pop(), "");
    }

    private void generic(Node node, String code) {
        if (node.left == null && node.right == null) {
            hashByKey.put(node.key, code);
            hashByCode.put(code, node.key);
            return;
        }

        if (node.left != null) {
            generic(node.left, code + "0");
        }

        if (node.right != null) {
            generic(node.right, code + "1");
        }
    }


    /**
     * 获取特定字母的哈夫曼编码
     *
     * @param key 字母
     * @return 哈夫曼编码
     */
    @Override
    public String getCode(String key) {
        return hashByKey.get(key);
    }

    /**
     * 运用哈夫曼编码对文本进行编码
     *
     * @param data 文本
     * @return 编码后的文本
     */
    @Override
    public String encode(String data) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            sb.append(hashByKey.get(String.valueOf(data.charAt(i))));
        }
        return sb.toString();
    }

    /**
     * 运用哈夫曼编码对文本进行解码
     *
     * @param data 文本
     * @return 解码后的文本
     */
    @Override
    public String decode(String data) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;

        for (int i = 0; i < data.length(); i++) {
            String item = data.substring(begin, i + 1);

            if (hashByCode.containsKey(item)) {
                begin = i + 1;
                sb.append(hashByCode.get(item));
            }
        }
        return sb.toString();
    }
}
