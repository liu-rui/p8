package com.liurui.defines.structures.graph;

/**
 * 图
 */
public interface Graph {

    /**
     * 使用顶点初始化图
     *
     * @param vertices 顶点列表
     */
    void init(String[] vertices);

    /**
     * 添加边
     *
     * @param start 起始顶点
     * @param end   终止顶点
     */
    void addEdge(String start, String end);

    /**
     * 添加边
     *
     * @param start 起始顶点
     * @param end   终止顶点
     * @param value 权
     */
    void addEdge(String start, String end, int value);

    /**
     * 添加边(双向的)
     * @param start 顶点
     * @param end 顶点
     * @param value 权
     */
    void addDoubleEdge(String start, String end, int value);

    /**
     * 删除边
     *
     * @param start 起始顶点
     * @param end   终止顶点
     */
    void removeEdge(String start, String end);

    /**
     * 打印所有的边
     * <p>
     * 打印规则：
     * 1. 边输出规则： A -> B
     * 2. 边的输出按照顶点的顺序输出
     *
     * @return
     */
    String[] printEdges();


    /**
     * 打印深度优先搜索(DFS)结果
     *
     * @return 深度优先遍历结果，用","隔开
     */
    String printDepthFirstSearch();

    /**
     * 打印广度优先搜索(BFS)结果
     *
     * @return 广先遍历结果，用","隔开
     */
    String printBreadthFirstSearch();


    /**
     * 使用迪杰斯特拉算法打印最短路径,结果按照距离升序排列
     *
     * 最短路径格式为
     * 顶点 -> 顶点 距离
     * 例如：A -> B 12
     *
     * @return 最短路径
     */
    String[] printShortestPathUsingDijkstra();


    /**
     * 使用弗洛伊德算法打印最短路径,结果按照距离升序排列
     *
     * 最短路径格式为
     * 顶点 -> 顶点 距离
     * 例如：A -> B 12
     *
     * @return 最短路径
     */
    String[] printShortestPathUsingFloyd();
}
