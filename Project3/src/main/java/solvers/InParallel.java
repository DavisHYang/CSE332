package solvers;

import cse332.graph.GraphUtil;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;
import paralleltasks.ArrayCopyTask;
import paralleltasks.RelaxInTask;

import java.util.List;
import java.util.Map;

public class InParallel implements BellmanFordSolver {

    public List<Integer> solve(int[][] adjMatrix, int source) {
        // 1. Parse adjacency matrix + initialize data structures
        List<Map<Integer, Integer>> g = Parser.parseInverse(adjMatrix);

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
            RelaxInTask.parallel(g, dist, pred, distCopy); // relax edges in parallel
        }

        // 3. Relax one more time to find negative cost cycles
        return GraphUtil.getCycle(pred);
    }

}