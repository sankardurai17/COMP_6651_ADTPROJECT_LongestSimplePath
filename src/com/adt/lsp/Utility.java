package com.adt.lsp;

import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utility {
//TODO we can have different methods that would deal with different cases
    //TODO one method for Converting online File to Graph
    public static GeometricGraph onlineFileToGraphConverter(String fileName, int n){
        Map<Vertex, List<Vertex>> adjList;
        try{
            BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)));
            String line;
            adjList=new LinkedHashMap<>();
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


    //TODO one method for Converting random File with edges along with its cocordinates to Graph
    public static GeometricGraph randomFileToGraphConverter(String fileName,int n){
        Map<Vertex, List<Vertex>> adjList;
        try{
            BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)));
            String line;
            adjList=new LinkedHashMap<>();
            int i=0;
            while((line=reader.readLine())!=null){
                String[] parts=line.trim().split("\\s+");
                Vertex u = new Vertex(Integer.parseInt(parts[0]),Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
                Vertex v = new Vertex(Integer.parseInt(parts[3]),Double.parseDouble(parts[4]),Double.parseDouble(parts[5]));
                converter(adjList,u,v);

                i++;
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new GeometricGraph(n,adjList);
    }

    public static void converter(Map<Vertex, List<Vertex>> adjList, Vertex u, Vertex v){
        if(adjList.containsKey(u)){
            List<Vertex> neighbors= adjList.get(u);
            neighbors.add(v);
            adjList.put(u,neighbors);
            if(adjList.containsKey(v)){
                List<Vertex> neighbors1=adjList.get(v);
                neighbors1.add(u);
                adjList.put(v,neighbors1);
            }
            else{
                List<Vertex> neighbors1=new ArrayList<>();
                neighbors1.add(u);
                adjList.put(v,neighbors1);
            }
        }
        else {
            List<Vertex> neighbors = new ArrayList<>();
            neighbors.add(v);
            adjList.put(u, neighbors);
            List<Vertex> neighbors2=new ArrayList<>();
            neighbors2.add(u);
            adjList.put(v,neighbors2);
        }
    }
}
