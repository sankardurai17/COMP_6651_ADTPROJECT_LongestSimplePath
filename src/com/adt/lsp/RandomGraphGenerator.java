package com.adt.lsp;

import com.adt.lsp.model.Edge;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RandomGraphGenerator {

    /*
     * Class to generate Random Graphs
     * */
    public static GeometricGraph generate(String fileName, int n, double lbExp, double ubExp){

        double lowerBoundLimit = 0.0;
        double upperBoundLimit = 1.0;
        double TOLERANCE = 0.000001;
        List<Vertex> lcc = new ArrayList<>();
        GeometricGraph graph=null;
        while (true) {
            double mid = (lowerBoundLimit + upperBoundLimit) / 2;
            List<Edge> edges=generateGeometricGraph(n,mid);
            graph=convertEdgesToGraphs(edges,n);
            lcc = LongestConnectedComponent.getLargestConnectedComponent(graph);
            int lccSize=lcc.size();

            double lowerExpected = lbExp * n;
            double upperExpected = ubExp * n;

            if (lccSize >= lowerExpected && lccSize <= upperExpected) {
                // Output the graph and its edges
                writeEdgesToFile(fileName,edges);
                break;
            } else if (lccSize < lowerExpected) {
                lowerBoundLimit = mid + TOLERANCE;
            } else {
                upperBoundLimit = mid - TOLERANCE;
            }

            if (Math.abs(upperBoundLimit - lowerBoundLimit) < TOLERANCE) {
                System.out.println("Convergence reached."+"LCC SIZE:  "+lccSize);
                break;
            }
        }
        return graph;
    }

    public static GeometricGraph convertEdgesToGraphs(List<Edge> edges, int n){
        Map<Vertex, List<Vertex>> adjList=new HashMap<>();
        for(Edge edge : edges){
            Vertex u= edge.u;
            Vertex v= edge.v;
            if(adjList.containsKey(u)){
                List<Vertex> neighboru=adjList.get(u);
                neighboru.add(v);
                adjList.put(u,neighboru);
                if(adjList.containsKey(v)){
                    List<Vertex> neighborsva=adjList.get(v);
                    neighborsva.add(u);
                    adjList.put(v,neighborsva);
                }
                else{
                    List<Vertex> neighborsv=new ArrayList<>();
                    neighborsv.add(u);
                    adjList.put(v,neighborsv);
                }

            }
            else{
                List<Vertex> neighbors=new ArrayList<>();
                neighbors.add(v);
                adjList.put(u,neighbors);
                if(adjList.containsKey(v)){
                    List<Vertex> neighborsva=adjList.get(v);
                    neighborsva.add(u);
                    adjList.put(v,neighborsva);
                }
                else{
                    List<Vertex> neighborsv=new ArrayList<>();
                    neighborsv.add(u);
                    adjList.put(v,neighborsv);
                }
            }
        }
        /**/
        GeometricGraph graph=new GeometricGraph(n,adjList);
        return graph;
    }

    public static List<Edge> generateGeometricGraph(int n, double r) {
        Vertex[] vertices=new Vertex[n];
        List<Edge> edges=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<n;i++){
            double x=random.nextDouble();
            double y=random.nextDouble();
            vertices[i]=new Vertex(i+1,x,y);
        }


        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(!vertices[i].equals(vertices[j])){
                    Vertex u=vertices[i];
                    Vertex v=vertices[j];
                    double distance=Math.pow((u.x-v.x),2)+Math.pow((u.y-v.y),2);
                    if(distance<=Math.pow(r,2)){

                        edges.add(new Edge(u,v)); //add the edges (i+1, j+1)
                    }
                }
            }
        }
        return edges;
    }

    public static void writeEdgesToFile(String fileName,List<Edge> edges){
        // Write to file in EDGES format
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Edge edge : edges) {
                writer.write(edge.u.id + " "+edge.u.x+" "+edge.u.y+" "+edge.v.id+" "+edge.v.x+" "+edge.v.y + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

