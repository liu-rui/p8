package com.liurui.answers.structures.graph;


import com.liurui.defines.structures.graph.GraphUsingMatrix;

import java.util.*;

/**
 * 图，使用邻接矩阵表示
 */
public class GraphUsingMatrixImpl implements GraphUsingMatrix {
    int[][] matrix;
    HashMap<String, Integer> verticesByName = new HashMap<>();
    HashMap<Integer, String> verticesByIndex = new HashMap<>();

    @Override
    public void init(String[] vertices) {
        if (vertices == null || vertices.length == 0) throw new IllegalArgumentException();

        matrix = new int[vertices.length][vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            this.verticesByName.put(vertices[i], i);
            verticesByIndex.put(i, vertices[i]);
        }
    }

    @Override
    public void addEdge(String start, String end) {
        addEdge(start, end, 1);
    }

    @Override
    public void addEdge(String start, String end, int value) {
        if (!verticesByName.containsKey(start) || !verticesByName.containsKey(end))
            throw new IllegalArgumentException();
        matrix[verticesByName.get(start)][verticesByName.get(end)] = value;
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
        matrix[verticesByName.get(start)][verticesByName.get(end)] = 0;
    }

    @Override
    public String[] printEdges() {
        ArrayList<String> edges = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > 0) {
                    edges.add(String.format("%s -> %s", verticesByIndex.get(i), verticesByIndex.get(j)));
                }
            }
        }
        return edges.toArray(new String[0]);
    }

    @Override
    public String printDepthFirstSearch() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.add(0);
        visited.add(0);
        sb.append(verticesByIndex.get(0) + ",");

        while (!stack.empty()) {
            Integer currentNode = stack.peek();
            boolean hadNotVisitNode = false;

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[currentNode][i] == 0 || visited.contains(i)) continue;
                stack.add(i);
                visited.add(i);
                sb.append(verticesByIndex.get(i) + ",");
                hadNotVisitNode = true;
                break;
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
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(0);
        visited.add(0);
        sb.append(verticesByIndex.get(0) + ",");

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[currentNode][i] > 0 && !visited.contains(i)) {
                    queue.add(i);
                    visited.add(i);
                    sb.append(verticesByIndex.get(i) + ",");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String[] printShortestPathUsingDijkstra() {
        ArrayList<String> ret = new ArrayList<>();
        int[] visited = new int[matrix.length];
        int[][] distances = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                distances[i][j] = matrix[i][j];
            }
        }
        visited[0] = 1;

        for (int i = 0; i < matrix.length - 1; i++) {
            Integer min = Integer.MAX_VALUE;
            Integer v = -1;
            //选出新的顶点
            for (int j = 0; j < matrix.length; j++) {
                if (j != 0 && visited[j] != 1 && distances[0][j] != 0 && distances[0][j] < min) {
                    min = distances[0][j];
                    v = j;
                }
            }

            visited[v] = 1;
            ret.add(String.format("%s -> %s %s", verticesByIndex.get(0), verticesByIndex.get(v), distances[0][v]));
            //由于出现新的最短顶点，调整路径
            for (int j = 0; j < matrix.length; j++) {
                if (j != 0 && visited[j] != 1 && distances[v][j] != 0 && (min + distances[v][j] < distances[0][j] || distances[0][j] == 0)) {
                    distances[0][j] = min + distances[v][j];
                }
            }
        }
        return ret.toArray(new String[0]);
    }

    @Override
    public String[] printShortestPathUsingFloyd() {
        int[][] distances = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                distances[i][j] = matrix[i][j] == 0 ? Integer.MAX_VALUE : matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix.length; j++)
                System.out.printf("%4d", distances[i][j]);
        }

        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    int tmp = (distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE)
                            ? Integer.MAX_VALUE
                            : (distances[i][k] + distances[k][j]);
                    if (distances[i][j] > tmp)
                        distances[i][j] = tmp;
                }
            }
        }
        PriorityQueue<Item> queue = new PriorityQueue<>();
        for (int i = 1; i < matrix.length; i++) {
            queue.add(new Item(i, distances[0][i]));
        }

        String[] ret = new String[queue.size()];
        int i = 0;

        while (!queue.isEmpty()) {
            Item item = queue.poll();
            ret[i++] = String.format("%s -> %s %s", verticesByIndex.get(0), verticesByIndex.get(item.index), item.value);
        }
        return ret;
    }

    private static class Item implements Comparable<Item> {
        private int index;
        private int value;

        public Item(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.value, o.value);
        }
    }

}
