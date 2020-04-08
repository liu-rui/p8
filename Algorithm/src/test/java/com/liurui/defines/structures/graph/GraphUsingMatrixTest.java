package com.liurui.defines.structures.graph;

import com.liurui.answers.structures.graph.GraphUsingMatrixImpl;
import org.junit.Test;

public class GraphUsingMatrixTest {
    @Test
    public void test() {
        GraphUsingMatrix graph = new GraphUsingMatrixImpl();

        GraphTest graphTest = new GraphTest();

        graphTest.test(graph);
        graphTest.testSearch(graph);
        graphTest.testShortestPath(graph);
    }
}
