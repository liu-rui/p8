package com.liurui.defines.structures.graph;

import com.liurui.answers.structures.graph.GraphUsingListImpl;
import org.junit.Test;

public class GraphUsingListTest {
    @Test
    public void test() {
        GraphUsingList graph = new GraphUsingListImpl();

        GraphTest graphTest = new GraphTest();

        graphTest.test(graph);
        graphTest.testSearch(graph);
        graphTest.testShortestPath(graph);
    }
}
