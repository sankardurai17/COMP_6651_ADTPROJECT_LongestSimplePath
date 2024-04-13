package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

public class Dijkstra {

    public static int findLSPUsingDijkstra(GeometricGraph graph,List<Vertex> lcc){
        int max_depth=Integer.MIN_VALUE;
        Map<Integer,Integer> maxList=new HashMap<>();
        int mainSourceOfLSP=-1;
        Vertex sourceVertex;
        for(Vertex vertex:lcc){

                //System.out.println("Vertex Source: " + vertex);
                int depth = dijkstraMAX(graph, lcc, vertex);
                maxList.put(vertex.id, depth);
                if(depth>max_depth) {
                    max_depth=depth;
                    mainSourceOfLSP= vertex.id;;
                }

                /*int depth=dijkstraMAX(graph, lcc, vertex);
                maxList.put(vertex.id,depth);
                max_depth=Integer.max(depth,max_depth);*/
        }
        System.out.println("--------Max_Depth Dijkstra: "+max_depth);
        System.out.println("--------Source ID:" +mainSourceOfLSP );
        return max_depth;
    }



    private static void relaxMAX(Vertex u, Vertex v, Map<Vertex, Integer> distance, Map<Vertex, Vertex> predecessor) {
        if (distance.get(v) < distance.get(u) + 1) {
            distance.put(v, distance.get(u) + 1);
            predecessor.put(v, u);
        }
    }


    public static int dijkstraMAXOG(GeometricGraph graph, List<Vertex> LCC, Vertex sourceVertex) {
        List<Vertex> tempLcc=new ArrayList<>(LCC);
        Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
        int[] d = new int[graph.getN()+1]; // Distance array
        Arrays.fill(d, Integer.MIN_VALUE); // Initialize with -∞
        int source= sourceVertex.id;
        d[source] = 0;

        // Modified Dijkstra's algorithm
        PriorityQueue<Vertex> Q = new PriorityQueue<>(Collections.reverseOrder());
        Q.add(sourceVertex);


        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            Set<Integer> visited=new HashSet<>();
            //System.out.println("Vertex u: "+u);
            visited.add(u.id);
            List<Vertex> neighbors=adjacencyList.getOrDefault(u, Collections.emptyList());
            //System.out.println("Neighbors:  "+neighbors);
            for (Vertex v : neighbors) {
                //change this
                if (!visited.contains(v.id)&&d[v.id] <= d[u.id] + 1) {
                    d[v.id] = d[u.id] + 1;
                   // Q.remove(v);
                    Q.add(v);
                }
            }
        }

        //System.out.println("Visited====="+visited);

        // Finding the maximum distance
        int maxDistance = getMaxDistance(graph, source, d);
        return maxDistance;
    }

   public static int dijkstraMAX(GeometricGraph graph, List<Vertex> LCC, Vertex sourceVertex) {
        Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
        int[] d = new int[graph.getN()+1]; // Distance array
        Arrays.fill(d, Integer.MIN_VALUE); // Initialize with -∞
        int source= sourceVertex.id;
        d[source] = 0;

        // Modified Dijkstra's algorithm
        PriorityQueue<Vertex> Q = new PriorityQueue<>();
        //Q.addAll(LCC);
        Q.add(sourceVertex);


        Set<Integer> visited=new HashSet<>();

        while (!Q.isEmpty()) {
            Vertex u = Q.poll();
            //System.out.println("Vertex u: "+u);
            visited.add(u.id);
            List<Vertex> neighbors=adjacencyList.getOrDefault(u, Collections.emptyList());
            //Q.addAll(neighbors);
            //System.out.println("Neighbors:  "+neighbors);
            for (Vertex v : neighbors) {

                //change this
                if (!visited.contains(v.id) &&d[v.id] < d[u.id] + 1) {
                    d[v.id] = d[u.id] + 1;
                    Q.remove(v);
                    Q.add(v);
                }

            }
        }

        //System.out.println("Visited====="+visited);

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
