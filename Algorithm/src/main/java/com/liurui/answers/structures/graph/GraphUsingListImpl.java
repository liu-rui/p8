package com.liurui.answers.structures.graph;

import com.liurui.defines.structures.graph.GraphUsingList;

import java.util.*;

/**
 * 图，使用邻接表表示
 */
public class GraphUsingListImpl implements GraphUsingList {
    private static class Node {
        private int index;
        private int value;
        private Node next;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public Node(int index, int value, Node next) {
            this.index = index;
            this.value = value;
            this.next = next;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    Node[] list;
    HashMap<String, Integer> verticesByName = new HashMap<>();
    HashMap<Integer, String> verticesByIndex = new HashMap<>();

    @Override
    public void init(String[] vertices) {
        if (vertices == null || vertices.length == 0) throw new IllegalArgumentException();
        list = new Node[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            this.verticesByName.put(vertices[i], i);
            verticesByIndex.put(i, vertices[i]);
        }
    }

    @Override
    public void addEdge(String start, String end) {
        addEdge(start, end, 0);
    }

    @Override
    public void addEdge(String start, String end, int value) {
        if (!verticesByName.containsKey(start) || !verticesByName.containsKey(end))
            throw new IllegalArgumentException();
        int beginIndex = verticesByName.get(start);
        int endIndex = verticesByName.get(end);

        if (list[beginIndex] == null) {
            list[beginIndex] = new Node(endIndex, value);
        } else {
            Node parent = null;
            Node item = list[beginIndex];

            while (item != null && item.getIndex() < endIndex) {
                parent = item;
                item = item.getNext();
            }

            if (item != null) {
                if (item.getIndex() == endIndex) {
                    return;
                } else {
                    Node newItem = new Node(endIndex, value, item);

                    if (parent == null) {
                        list[beginIndex] = newItem;
                    } else {
                        parent.setNext(newItem);
                    }
                }
            } else {
                parent.setNext(new Node(endIndex, value));
            }
        }
    }

    @Override
    public void addDoubleEdge(String start, String end, int value) {
        addEdge(start, end, value);
        addEdge(end, start, value);
    }

    @Override
    public void removeEdge(String start, String end) {
        if (!verticesByName.containsKey(start) || !verticesByName.containsKey(end))
            throw new IllegalArgumentException();
        int beginIndex = verticesByName.get(start);
        int endIndex = verticesByName.get(end);

        if (list[beginIndex] == null) return;

        Node parent = null;
        Node item = list[beginIndex];

        while (item != null && item.getIndex() < endIndex) {
            parent = item;
            item = item.getNext();
        }

        if (item != null && item.getIndex() == endIndex) {
            if (parent == null) {
                list[beginIndex] = item.getNext();
            } else {
                parent.setNext(item.getNext());
            }
        }
    }

    @Override
    public String[] printEdges() {
        ArrayList<String> ret = new ArrayList<>();

        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) continue;
            String begin = verticesByIndex.get(i);
            Node node = list[i];

            while (node != null) {
                ret.add(String.format("%s -> %s", begin, verticesByIndex.get(node.getIndex())));
                node = node.getNext();
            }
        }
        return ret.toArray(new String[0]);
    }

    @Override
    public String printDepthFirstSearch() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(0);
        visited.add(0);
        sb.append(verticesByIndex.get(0) + ",");

        while (!stack.empty()) {
            Integer currentNode = stack.peek();
            boolean hadNotVisitNode = false;
            Node node = list[currentNode];

            while (node != null && !hadNotVisitNode) {
                if (visited.contains(node.index)) {
                    node = node.getNext();
                } else {
                    stack.push(node.getIndex());
                    visited.add(node.getIndex());
                    sb.append(verticesByIndex.get(node.getIndex()) + ",");
                    hadNotVisitNode = true;
                }
            }

            if (!hadNotVisitNode) {
                stack.pop();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String printBreadthFirstSearch() {
        //存储遍历结果，顶点间用","隔开
        StringBuilder sb = new StringBuilder();
        //队列
        LinkedList<Integer> queue = new LinkedList<>();
        //存储已经访问过的顶点列表
        HashSet<Integer> visited = new HashSet<>();

        queue.add(0);
        visited.add(0);
        sb.append(verticesByIndex.get(0) + ",");

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            Node node = list[currentNode];

            while (node != null) {
                if (visited.contains(node.getIndex())) {
                    node = node.getNext();
                } else {
                    queue.add(node.getIndex());
                    visited.add(node.getIndex());
                    sb.append(verticesByIndex.get(node.getIndex()) + ",");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String[] printShortestPathUsingDijkstra() {
        ArrayList<String> ret = new ArrayList<>();
        int[][] distances = new int[list.length][list.length];
        int[] visited = new int[list.length];

        //产生距离矩阵
        for (int i = 0; i < list.length; i++) {
            Node node = list[i];

            while (node != null) {
                distances[i][node.getIndex()] = node.getValue();
                node = node.getNext();
            }
        }

        visited[0] = 1;

        for (int i = 0; i < visited.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            int v = 0;

            for (int j = 0; j < list.length; j++) {
                if (visited[j] != 1 && distances[0][j] != 0 && distances[0][j] < min) {
                    min = distances[0][j];
                    v = j;
                }
            }

            visited[v] = 1;
            ret.add(String.format("%s -> %s %s", verticesByIndex.get(0), verticesByIndex.get(v), distances[0][v]));

            for (int j = 0; j < list.length; j++) {
                if (visited[j] != 1 && distances[v][j] != 0 && (min + distances[v][j] < distances[0][j] || distances[0][j] == 0)) {
                    distances[0][j] = min + distances[v][j];
                }
            }
        }
        return ret.toArray(new String[0]);
    }

    @Override
    public String[] printShortestPathUsingFloyd() {
        int[][] distances = new int[list.length][list.length];

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < list.length; i++) {
            Node node = list[i];

            while (node != null) {
                distances[i][node.getIndex()] = node.getValue();
                node = node.getNext();
            }
        }

        for (int k = 0; k < list.length; k++) {
            for (int i = 0; i < list.length; i++) {
                for (int j = 0; j < list.length; j++) {
                    int tmp = distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE
                            ? Integer.MAX_VALUE
                            : distances[i][k] + distances[k][j];
                    if (distances[i][j] > tmp) {
                        distances[i][j] = tmp;
                    }
                }
            }
        }
        PriorityQueue<Item> queue = new PriorityQueue<>();

        for (int i = 1; i < list.length; i++) {
            queue.add(new Item(i, distances[0][i]));
        }
        String[]  ret= new String[queue.size()];
        int i = 0;

        while (!queue.isEmpty()){
            Item item = queue.poll();
            ret[i++] = String.format("%s -> %s %s" , verticesByIndex.get(0) , verticesByIndex.get(item.index) , item.value);
        }
        return ret;
    }

    public static class Item implements Comparable<Item> {
        private int index;
        private int value;

        public Item(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(value, o.value);
        }
    }
}
