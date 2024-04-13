import com.adt.lsp.*;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //one time activity run only once and after that comment this line!
        //generateRandomGraphs();
        calculateMetricsForRandomGraph1();
        //calculateMetricsForOnlineGraph1();
    }

    public static void generateRandomGraphs(){
        RandomGraphGenerator.generate("temp1.edges",300,0.9,0.95);
        RandomGraphGenerator.generate("temp2.edges",400,0.8,0.9);
        RandomGraphGenerator.generate("temp3.edges",500,0.7,0.8);

    }



    public static void calculateMetricsForRandomGraph1(){
        //RandomGraphGenerator.generate("temp1.edges",300,0.9,0.95);
        GeometricGraph graph=Utility.randomFileToGraphConverter("temp2.edges",400);
        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        //System.out.println("LCCSize: "+lcc.size());
        Metrics.calculateMetrics(graph,lcc);
        int lmaxDFS= DFS.LongestSimplePath(graph,lcc);
        System.out.println("Maximum Depth DFS:"+ lmaxDFS);

        //int maxDepthDijkstra=Dijkstra.findLSPUsingDijkstra(graph);
        //System.out.println("Maximum Depth Dijkstra:"+maxDepthDijkstra);

        List<Vertex> verts= AStarAlgorithm.findLongestSimplePath(graph,lcc);
        System.out.println("No of Edges for A*"+(verts.size()-1));
    }

    //TODO create a utility to loadEdgesFromGraphFile.
    //TODO That Utility should be more generic way of loading geometric as well as online graph!
    public static void calculateMetricsForOnlineGraph1(){
        GeometricGraph graph=Utility.onlineFileToGraphConverter("DSJC500-5.mtx",500);

        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        //System.out.println("LCCSize: "+lcc.size());
        Metrics.calculateMetrics(graph,lcc);
        int lmaxDFS= DFS.LongestSimplePath(graph,lcc);
        System.out.println("Maximum Depth DFS:"+ lmaxDFS);

        int maxDepthDijkstra=Dijkstra.findLSPUsingDijkstra(graph,lcc);
        System.out.println("Maximum Depth Dijkstra:"+maxDepthDijkstra);

    }
}