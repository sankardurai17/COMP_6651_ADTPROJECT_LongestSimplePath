import com.adt.lsp.*;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        calculateMetricsForRandomGraph1();
    }

    public static void calculateMetricsForRandomGraph1(){
        GeometricGraph graph= RandomGraphGenerator.generate("temp1.edges",300,0.9,0.95);
        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        //System.out.println("LCCSize: "+lcc.size());
        Metrics.calculateMetrics(graph,lcc);
        int lmaxDFS= DFS.LongestSimplePath(graph);
        System.out.println("Maximum Depth DFS:"+ lmaxDFS);

        int maxDepthDijkstra=Dijkstra.findLSPUsingDijkstra(graph);
        System.out.println("Maximum Depth Dijkstra:"+maxDepthDijkstra);

        List<Vertex> verts= AStarAlgorithm.findLongestSimplePath(graph);
        System.out.println("No of Edges for A*"+(verts.size()-1));
    }

    //TODO create a utility to loadEdgesFromGraphFile.
    //TODO That Utility should be more generic way of loading geometric as well as online graph!
    public static void calculateMetricsForOnlineGraph1(){
        //GeometricGraph graph= loadGraphFile("fileName");
        GeometricGraph graph=new GeometricGraph();

        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        //System.out.println("LCCSize: "+lcc.size());
        Metrics.calculateMetrics(graph,lcc);
        int lmaxDFS= DFS.LongestSimplePath(graph);
        System.out.println("Maximum Depth DFS:"+ lmaxDFS);

        int maxDepthDijkstra=Dijkstra.findLSPUsingDijkstra(graph);
        System.out.println("Maximum Depth Dijkstra:"+maxDepthDijkstra);

        List<Vertex> verts= AStarAlgorithm.findLongestSimplePath(graph);
        System.out.println("No of Edges for A*"+(verts.size()-1));
    }
}