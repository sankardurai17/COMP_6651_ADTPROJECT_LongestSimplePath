package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

public class BeamSearchWithHeuristic {

    public static int findLongestSimplePath(GeometricGraph graph) {
        List<Vertex> largestComponent = LongestConnectedComponent.getLargestConnectedComponent(graph);
        List<Vertex> longestPath = new ArrayList<>();

        for (int i = 0; i < largestComponent.size(); i++) {
            for (int j = i + 1; j < largestComponent.size(); j++) {
                Vertex sourceVertex = largestComponent.get(i);
                Vertex goal=largestComponent.get(j);
                if(sourceVertex.id==goal.id){
                    continue;
                }
                List<Vertex> path = beamSearchFromSource(graph, sourceVertex, goal);
                if (path.size() > longestPath.size()) {
                    longestPath = path;
                }
            }
        }

        return longestPath.size()-1;
    }

    private static List<Vertex> beamSearchFromSource(GeometricGraph graph, Vertex sourceVertex, Vertex goal) {
        Set<Vertex> visited = new HashSet<>();
        List<Vertex> longestPath = new ArrayList<>();
        Queue<List<Vertex>> queue = new PriorityQueue<>(new PathComparator(goal));

        List<Vertex> initialPath = new ArrayList<>();
        initialPath.add(sourceVertex);
        queue.add(initialPath);

        while (!queue.isEmpty()) {
            List<Vertex> path = queue.poll();
            Vertex current = path.get(path.size() - 1);

            if (current.id == goal.id) {
                return path; // goal reached
            }

            if (path.size() > longestPath.size()) {
                longestPath = new ArrayList<>(path);
            }

            for (Vertex neighbor : graph.getAdjacencyList().get(current)) {
                if (!visited.contains(neighbor)) {
                    List<Vertex> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                    visited.add(neighbor);
                }
            }
        }

        return longestPath;
    }

    static class PathComparator implements Comparator<List<Vertex>> {
        private Vertex goal;

        public PathComparator(Vertex goal) {
            this.goal = goal;
        }

        @Override
        public int compare(List<Vertex> list1, List<Vertex> list2) {
            double cost1 = getPathCost(list1, goal);
            double cost2 = getPathCost(list2, goal);
            return Double.compare(cost2, cost1);
        }

        private double getPathCost(List<Vertex> path, Vertex goal) {
            // Heuristic value: Euclidean distance
            Vertex lastVertex = path.get(path.size() - 1);
            return path.size() + heuristic(lastVertex, goal);
        }
    }

    public static double heuristic(Vertex v, Vertex goal) {
        return Math.sqrt(Math.pow(goal.x - v.x, 2) + Math.pow(goal.y - v.y, 2));
    }
}
