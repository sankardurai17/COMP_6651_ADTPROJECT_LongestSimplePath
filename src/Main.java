import com.adt.lsp.*;
import com.adt.lsp.model.GeometricGraph;
import com.adt.lsp.model.LongestConnectedComponent;
import com.adt.lsp.model.Vertex;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("\n-------Random Graph 1----------");
        calculateMetricsForRandomGraph("temp1.edges",300,0.9,0.95);

        System.out.println("\n-------Random Graph 2-----------");
        calculateMetricsForRandomGraph("temp2.edges",400,0.8,0.9);

        System.out.println("\n--------Random Graph 3-----------");
        calculateMetricsForRandomGraph("temp3.edges",500,0.7,0.8);

        System.out.println("\n----------Online Graph 1------------");
        calculateMetricsForOnlineGraph("DSJC500-5.mtx",500);

        System.out.println("\n----------Online Graph 2-------------");
        calculateMetricsForOnlineGraph("inf-euroroad.edges",1200);

        System.out.println("\n------------Online Graph 3------------");
        calculateMetricsForOnlineGraph("inf-power.mtx",5000);

    }

    public static void calculateMetricsForRandomGraph(String fileName, int n, double lowerBound, double upperBound){
        GeometricGraph graph= RandomGraphGenerator.generate(fileName,n,lowerBound,upperBound);
        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        Metrics.calculateMetrics(graph,lcc);

        int lmaxDFS= DFS.lspWithDFS(graph);
        System.out.println("Length of lsp of DFS:"+ lmaxDFS);

        int lmaxDijkstra=Dijkstra.findLSPUsingDijkstra(graph);
        System.out.println("Length of lsp of Dijkstra:"+lmaxDijkstra);

        int lmaxAstar= AStarAlgorithm.findLongestSimplePath(graph);
        System.out.println("Length of lsp of A*"+lmaxAstar);

        int lmaxBFSWithHeuristic= BFSWithHeuristic.findLongestSimplePath(graph);
        System.out.println("Length of lsp of BFSWithHeuristic: "+lmaxBFSWithHeuristic);

    }


    public static void calculateMetricsForOnlineGraph(String fileName, int n){
        GeometricGraph graph=Utility.onlineFileToGraphConverter(fileName,n);
        List<Vertex> lcc= LongestConnectedComponent.getLargestConnectedComponent(graph);
        Metrics.calculateMetrics(graph,lcc);
        int lmaxDFS= DFS.lspWithDFS(graph);
        System.out.println("Length of lsp ofDFS:"+ lmaxDFS);

        int maxDepthDijkstra=Dijkstra.findLSPUsingDijkstra(graph);
        System.out.println("Length of lsp of Dijkstra:"+maxDepthDijkstra);
    }
}