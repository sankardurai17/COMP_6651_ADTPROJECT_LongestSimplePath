package com.adt.lsp.model;

import java.util.List;
import java.util.Map;

public class GeometricGraph {
    int n;

    Map<Vertex, List<Vertex>> adjacencyList;

    public GeometricGraph() {
    }

    public GeometricGraph(int n, Map<Vertex, List<Vertex>> adjacencyList) {
        this.n = n;
        this.adjacencyList=adjacencyList;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Map<Vertex, List<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Vertex, List<Vertex>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "n=" + n +
                ", adjacencyList=" + adjacencyList +
                '}';
    }
}
