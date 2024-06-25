package solvers;

import cse332.graph.GraphUtil;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;

import java.util.List;
import java.util.Map;

public class OutSequential implements BellmanFordSolver {

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
            int[] distCopy = copy(dist); // copy for later
            for(int w = 0; w < g.size(); w++) { // each edge
                for(int key: g.get(w).keySet()) {
                    if(dist[w] != GraphUtil.INF && distCopy[w] + g.get(w).get(key) < dist[key]) {
                        dist[key] = distCopy[w] + g.get(w).get(key);
                        distCopy[key] = dist[key];
                        pred[key] = w;
                    }
                }
            }
        }

        // 3. Relax one more time to find negative cost cycles
        return GraphUtil.getCycle(pred);
    }

    private int[] copy(int[] arr) {
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

}
