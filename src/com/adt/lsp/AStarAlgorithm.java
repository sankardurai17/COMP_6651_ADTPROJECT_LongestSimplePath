package com.adt.lsp;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.*;

public class AStarAlgorithm {
    public static int findLongestSimplePath(GeometricGraph graph) {
        List<Vertex> lcc = LongestConnectedComponent.getLargestConnectedComponent(graph);
        int maxDepth = Integer.MIN_VALUE;
        List<Vertex> longestPath = null;
        int count=0;
        for (int i = 0; i < lcc.size(); i++) {
            for (int j = i+1; j < lcc.size(); j++) {
                if(lcc.get(i).id==lcc.get(j).id){
                    continue;
                }

                List<Vertex> path = AStarLSP(graph, lcc.get(i), lcc.get(j));

                if (path.size() > maxDepth) {
                    count++;
                    maxDepth = path.size();
                    longestPath = path;
                }
            }
        }
        return longestPath.size()-1;

    }


    public static List<Vertex> constructPath(Vertex d){
        List<Vertex> path=new ArrayList<>();
        Vertex curr=d;
        while(curr!=null){
            path.add(curr);
            curr=curr.parent;
        }
        return path;
    }

    public static void InitializeSingleSourceMAX(GeometricGraph graph, Vertex s) {
        for (Vertex v : graph.getAdjacencyList().keySet()) {
            v.d = Integer.MIN_VALUE;
            v.parent = null;
        }
        s.d = 0;
        s.parent=null;
    }

    public static void RelaxMAX(Vertex u, Vertex v) {
        if (u.d + 1 > v.d) {
            v.d = u.d + 1;
            v.parent = u;
        }
    }

    static List<Vertex> AStarLSP(GeometricGraph G, Vertex s, Vertex d) {
        InitializeSingleSourceMAX(G, s);
        Set<Vertex> visited = new HashSet<>();

        for (Vertex v : G.getAdjacencyList().keySet()) {
            v.h = distance(v,d);
        }

        Set<Vertex> S = new HashSet<>();
        MaxHeap Q = new MaxHeap();

        for (Vertex u : G.getAdjacencyList().keySet()) {
            Q.insert(u, u.d + u.h);
        }

        while (!Q.isEmpty()) {
            Vertex u = Q.extractMax();
            visited.add(u);
            for (Vertex v : G.getAdjacencyList().get(u)) {
                if (u.parent != v && !visited.contains(v)) {
                    RelaxMAX(u, v);
                    Q.insert(v, v.d + v.h);
                }
            }
        }

        List<Vertex> path=constructPath(d);
        return path;
    }

    public static double distance(Vertex v, Vertex d){
        return Math.sqrt(Math.pow((d.x - v.x),2)  + Math.pow((d.y-v.y),2));
    }


    static class MaxHeap {
        List<Vertex> heap;

        public MaxHeap() {
            heap = new ArrayList<>();
        }

        public void insert(Vertex v, double priority) {
            v.d = priority;
            heap.add(v);
            int index = heap.size() - 1;
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap.get(parentIndex).d >= heap.get(index).d) {
                    break;
                }
                Collections.swap(heap, parentIndex, index);
                index = parentIndex;
            }
        }

        public Vertex extractMax() {
            if (heap.isEmpty()) {
                return null;
            }
            Vertex max = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapify(0);
            return max;
        }

        private void heapify(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;
            if (left < heap.size() && heap.get(left).d > heap.get(largest).d) {
                largest = left;
            }
            if (right < heap.size() && heap.get(right).d > heap.get(largest).d) {
                largest = right;
            }
            if (largest != index) {
                Collections.swap(heap, index, largest);
                heapify(largest);
            }
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }
    }


}
