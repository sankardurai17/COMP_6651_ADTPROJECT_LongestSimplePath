package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

import static java.lang.Math.max;

public class DFS {
    private static void dfs(GeometricGraph graph, Vertex vertex, Set<Vertex> visited, int depth, Map<Vertex, Integer> depthMap) {
        visited.add(vertex);
        depthMap.put(vertex, depth);

        for (Vertex neighbor : graph.getAdjacencyList().getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, depth + 1, depthMap);
            }
        }

    }


    private static Vertex findVertexAtMaxDepth(Map<Vertex, Integer> depthMap) {
        Vertex vertexMaxDepth = null;
        int maxDepth = Integer.MIN_VALUE;
        for (Map.Entry<Vertex, Integer> entry : depthMap.entrySet()) {
            if (entry.getValue() > maxDepth) {
                maxDepth = entry.getValue();
                vertexMaxDepth = entry.getKey();
            }
        }
        return vertexMaxDepth;
    }

    public static int lspWithDFS(GeometricGraph graph) {
        int lmax = 0;

        List<Vertex> vlccList = LongestConnectedComponent.getLargestConnectedComponent(graph);
        int vlccCount = vlccList.size();

        for(int i=0; i< (int)Math.sqrt(vlccCount); i++){
            int randomIndex = new Random().nextInt(vlccCount);
            Vertex randomVertex = vlccList.get(randomIndex);

            Map<Vertex, Integer> depthMap = new HashMap<>();
            Set<Vertex> visited = new HashSet<>();
            dfs(graph, randomVertex, visited, 0, depthMap);

            Vertex vertexMaxDepth = findVertexAtMaxDepth(depthMap);
            int vDepth = depthMap.get(vertexMaxDepth);
            depthMap.clear();
            visited.clear();

            dfs(graph, vertexMaxDepth, visited, 0, depthMap);
            Vertex vertexMaxDepth2 = findVertexAtMaxDepth(depthMap);
            int wDepth = depthMap.get(vertexMaxDepth2);


            lmax = max(lmax, max(vDepth, wDepth));
        }
        return lmax;
    }
}





