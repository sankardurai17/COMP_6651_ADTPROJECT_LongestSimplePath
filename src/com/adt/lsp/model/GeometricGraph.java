package com.adt.lsp.model;

import java.util.List;
import java.util.*;

public class GeometricGraph {
    int n;

    Map<Vertex, List<Vertex>> adjacencyList;

    public GeometricGraph() {
    }

    public GeometricGraph(int n, Map<Vertex, List<Vertex>> adjacencyList) {
        this.n = n;
        this.adjacencyList=adjacencyList;
    }

    public GeometricGraph(GeometricGraph graph){
        this.n = graph.getN(); // Copy number of vertices

        // Deep copy adjacency list using a new HashMap
        this.adjacencyList = new HashMap<>();
        for (Map.Entry<Vertex, List<Vertex>> entry : graph.getAdjacencyList().entrySet()) {
            Vertex newVertex = new Vertex(entry.getKey()); // Create new Vertex object
            List<Vertex> copiedNeighbors = new ArrayList<>(entry.getValue()); // Deep copy neighbor list
            adjacencyList.put(newVertex, copiedNeighbors);
        }
    }

    public int getN() {
        return n;
    }

    public Map<Vertex, List<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "n=" + n +
                ", adjacencyList=" + adjacencyList +
                '}';
    }
}
