package solvers;

import cse332.graph.GraphUtil;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;
import paralleltasks.ArrayCopyTask;
import paralleltasks.RelaxOutTaskBad;

import java.util.List;
import java.util.Map;


public class OutParallelBad implements BellmanFordSolver {

    public List<Integer> solve(int[][] adjMatrix, int source) {
        // 1. Parse adjacency matrix + initialize data structures
        List<Map<Integer, Integer>> g = Parser.parse(adjMatrix);

        int[] dist = new int[adjMatrix.length];
        int[] pred = new int[adjMatrix.length];

        // set dists to infinity (max)
        // set pred to -1 (none)
        for(int i = 0; i < dist.length; i++) {
            dist[i] = GraphUtil.INF;
            pred[i] = -1;
        }
        // starting node
        dist[source] = 0;

        // 2. Run the Bellman-Ford algorithm
        int v = g.size();
        for(int i = 0; i < v; i++) { // for each vertex-1
            int[] distCopy = ArrayCopyTask.copy(dist); // parallel copy
            RelaxOutTaskBad.parallel(g, dist, pred, distCopy); // relax edges in parallel
        }

        // 3. Relax one more time to find negative cost cycles
        return GraphUtil.getCycle(pred);
    }

    private static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}
