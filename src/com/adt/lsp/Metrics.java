package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.Vertex;

import java.util.List;
import java.util.Map;

public class Metrics {
    public static void calculateMetrics(GeometricGraph graph, List<Vertex> lcc){
            int n = graph.getN();
            int vlccSize = lcc.size();
            int deltaLCC = calculateDeltaLCC(graph, lcc);
            double kLCC = calculateAverageDegree(graph, lcc);
            // Output metrics
            System.out.println("Metrics:");
            System.out.println("Number of nodes in the graph, n: " + n);
            System.out.println("Number of nodes in the largest connected component, |VLCC|: " + vlccSize);
            System.out.println("The maximum degree of any node in ∥LCC∥, ∆(LCC): " + deltaLCC);
            System.out.println("The average degree of nodes in ∥LCC∥, k(LCC): " + kLCC);
        }


        public static int calculateDeltaLCC(GeometricGraph graph, List<Vertex> lcc) {
                int maxDegree = 0;
                Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
                for (Vertex vertex : lcc) {
                        int degree = adjacencyList.get(vertex).size();
                        maxDegree = Math.max(maxDegree, degree);
                }
                return maxDegree;
        }

        public static double calculateAverageDegree(GeometricGraph graph, List<Vertex> lcc) {
                int totalDegree = 0;
                Map<Vertex, List<Vertex>> adjacencyList = graph.getAdjacencyList();
                for (Vertex vertex : lcc) {
                        totalDegree += adjacencyList.get(vertex).size();
                }
                return (double) totalDegree / lcc.size();
        }


}
