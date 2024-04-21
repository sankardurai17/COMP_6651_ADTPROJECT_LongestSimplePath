package com.adt.lsp;

import com.adt.lsp.model.Edge;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utility {
    public static GeometricGraph onlineFileToGraphConverter(String fileName, int n){
        Map<Vertex, List<Vertex>> adjList;
        try{
            BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)));
            String line;
            adjList=new HashMap<>();
            int i=0;
            while((line=reader.readLine())!=null){
                String[] parts=line.trim().split("\\s+");
                if(parts.length!=2){
                    continue;
                }
                Vertex u = new Vertex(Integer.parseInt(parts[0]));
                Vertex v = new Vertex(Integer.parseInt(parts[1]));
                converter(adjList,u,v);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new GeometricGraph(n,adjList);
    }
    public static void converter(Map<Vertex, List<Vertex>> adjList, Vertex u, Vertex v){
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

}
