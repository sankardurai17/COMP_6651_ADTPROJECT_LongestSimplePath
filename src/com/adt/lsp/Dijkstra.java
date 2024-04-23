package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

public class Dijkstra {

    public static int findLSPUsingDijkstra(GeometricGraph graph){
        List<Vertex> lcc = LongestConnectedComponent.getLargestConnectedComponent(graph);
        int max_depth=Integer.MIN_VALUE;
        Map<Integer,Integer> maxList=new HashMap<>();

        for(Vertex vertex:lcc){
            int depth = dijkstraMAX(graph, lcc, vertex);
            maxList.put(vertex.id, depth);
            if(depth>max_depth) {
                max_depth=depth;
            }
        }
        return max_depth;
    }

    public static int dijkstraMAX(GeometricGraph graph, List<Vertex> LCC, Vertex sourceVertex) {
        Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
        int[] d = new int[graph.getN()+1];
        Arrays.fill(d, Integer.MIN_VALUE);
        int source= sourceVertex.id;
        d[source] = 0;

        // Modified Dijkstra's algorithm
        PriorityQueue<Vertex> Q = new PriorityQueue<>();
        //Q.addAll(LCC);
        Q.add(sourceVertex);


        Set<Integer> visited=new HashSet<>();

        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            visited.add(u.id);
            List<Vertex> neighbors=adjacencyList.getOrDefault(u, Collections.emptyList());
            for (Vertex v : neighbors) {

                //relax max
                if (!visited.contains(v.id) &&d[v.id] < d[u.id] + 1) {
                    d[v.id] = d[u.id] + 1;
                    Q.remove(v);
                    Q.add(v);
                }

            }
        }
        // Finding the maximum distance
        int maxDistance = getMaxDistance(graph, source, d);
        return maxDistance;
    }

    private static int getMaxDistance(GeometricGraph graph, int source, int[] d) {
        int maxDistance = Integer.MIN_VALUE;
        int i=0;
        for (i=0; i< graph.getN()+1; i++) {
            if (d[i] > maxDistance) {
                maxDistance = d[i];
            }
        }

        return maxDistance;
    }



}
