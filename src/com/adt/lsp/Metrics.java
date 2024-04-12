package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.Vertex;

import java.util.List;
import java.util.Map;

public class Metrics {
    public static void calculateMetrics(GeometricGraph graph, List<Vertex> lcc){
            // Metrics
            int n = graph.getN();
            int vlccSize = lcc.size();
            int deltaLCC = calculateDeltaLCC(graph, lcc);
            double kLCC = calculateAverageDegree(graph, lcc);
            // Output metrics
            System.out.println("Metrics:");
            System.out.println("n: " + n);
            System.out.println("|VLCC|: " + vlccSize);
            System.out.println("∆(LCC): " + deltaLCC);
            System.out.println("k(LCC): " + kLCC);
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
