package com.adt.lsp.model;

import java.util.*;

public class LongestConnectedComponent {
    public static List<Vertex> getLargestConnectedComponent(GeometricGraph graph) {
        Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
        Set<Integer> visited = new HashSet<>();
        List<Vertex> largestComponent = new ArrayList<>();
        int maxSize = 0;

        for (Vertex vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex.id)) {
                List<Vertex> component = new ArrayList<>();
                dfs(adjacencyList, visited, vertex, component);
                if (component.size() > maxSize) {
                    // System.out.println("Component node Inside loop: "+component.get(0)+"Component size: "+component.size()+"Max size: "+maxSize);
                    largestComponent = component;
                    maxSize = component.size();
                }
            }
        }
        Collections.sort(largestComponent, Comparator.comparing(Vertex::getId));
        return largestComponent;
    }

    // DFS traversal to explore connected components
    private static void dfs(Map<Vertex, List<Vertex>> adjacencyList, Set<Integer> visited, Vertex vertex, List<Vertex> component) {
        visited.add(vertex.id);
        component.add(vertex);

        for (Vertex neighbor : adjacencyList.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor.id)) {
                dfs(adjacencyList, visited, neighbor, component);
            }
        }
    }
}