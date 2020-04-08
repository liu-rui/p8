package com.liurui.defines.structures.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class GraphTest {

    public void test(Graph graph) {
        graph.init(new String[]{"A", "B", "C", "D"});

        graph.addEdge("A", "C"); //故意在A->B之前设置
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "C"); //故意重复设置
        graph.addEdge("D", "C");
        assertArrayEquals(new String[]{
                        "A -> B",
                        "A -> C",
                        "A -> D",
                        "B -> C",
                        "D -> C"}
                , graph.printEdges());


        graph.removeEdge("B", "C");
        graph.removeEdge("A", "B");
        assertArrayEquals(new String[]{
                        "A -> C",
                        "A -> D",
                        "D -> C"}
                , graph.printEdges());
    }

    public void testSearch(Graph graph) {
        graph.init(new String[]{"A", "B", "C", "D", "E", "F", "G"});

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("C", "G");
        assertEquals("A,B,D,E,C,F,G", graph.printDepthFirstSearch());
        assertEquals("A,B,C,D,E,F,G", graph.printBreadthFirstSearch());
    }

    public void testShortestPath(Graph graph) {
        graph.init(new String[]{"A", "B", "C", "D", "E", "F"});

        graph.addDoubleEdge("A", "B", 16);
        graph.addDoubleEdge("A", "C", 1);
        graph.addDoubleEdge("A", "E", 12);
        graph.addDoubleEdge("A", "F", 15);
        graph.addDoubleEdge("B", "D", 2);
        graph.addDoubleEdge("B", "F", 8);
        graph.addDoubleEdge("C", "E", 5);
        graph.addDoubleEdge("D", "F", 3);
        graph.addDoubleEdge("E", "F", 8);
        graph.addDoubleEdge("D", "E", 9);

        String[] shortestPaths = {
                "A -> C 1",
                "A -> E 6",
                "A -> F 14",
                "A -> D 15",
                "A -> B 16"};

        assertArrayEquals(shortestPaths,
                graph.printShortestPathUsingDijkstra());
        assertArrayEquals(shortestPaths, graph.printShortestPathUsingFloyd());
    }
}
