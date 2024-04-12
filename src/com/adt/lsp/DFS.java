package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

import static java.lang.Math.max;

public class DFS {

    private static void depthFirstSearch(GeometricGraph graph, Vertex vertex, Set<Vertex> visited, int depth, Map<Vertex, Integer> depthMap) {
        visited.add(vertex);
        depthMap.put(vertex, depth);

        for (Vertex neighbor : graph.getAdjacencyList().getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                depthFirstSearch(graph, neighbor, visited, depth + 1, depthMap);
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

    public static int LongestSimplePath(GeometricGraph graph) {
        int lmax = 0;

        List<Vertex> vlccList = LongestConnectedComponent.getLargestConnectedComponent(graph);
        System.out.println("VCCList:" + vlccList.size());
        int vlccCount = vlccList.size();

        //for(int i=0; i< (int)Math.sqrt(vlccCount); i++){
        for (int i = 0; i < vlccCount; i++) {
            //int randomIndex = new Random().nextInt(vlccCount);
            int randomIndex = i;
            //System.out.println("Random Index:  " + randomIndex);
            Vertex randomVertex = vlccList.get(randomIndex);
            //System.out.println("LCC Vertex: " + randomVertex);

            Map<Vertex, Integer> depthMap = new HashMap<>();
            Set<Vertex> visited = new HashSet<>();
            depthFirstSearch(graph, randomVertex, visited, 0, depthMap);

            Vertex vertexMaxDepth = findVertexAtMaxDepth(depthMap);
            int vDepth = depthMap.get(vertexMaxDepth);
            //System.out.println("VertexMaxDepth: " + vertexMaxDepth + "\t Its size is:" + vDepth);
            //System.out.println("Depth Map: " + depthMap);

            //apply dfs on vertexMaxDepth to find max. depth vertex and wDepth
            depthMap.clear();
            visited.clear();
            //System.out.println("DepthMap: " + depthMap);
            depthFirstSearch(graph, vertexMaxDepth, visited, 0, depthMap);
            Vertex vertexMaxDepth2 = findVertexAtMaxDepth(depthMap);
            int wDepth = depthMap.get(vertexMaxDepth2);
            //System.out.println("VertexMaxDepth2: " + vertexMaxDepth2 + "\t Its size is:" + wDepth);


            lmax = max(lmax, max(vDepth, wDepth));
            //System.out.println("\niteration:" + i + ", lmax:" + lmax);
        }
        return lmax;
    }


}





