package paralleltasks;

import cse332.graph.GraphUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RelaxInTask extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 10;

    private final List<Map<Integer, Integer>> g;
    private final int[] dist, pred, distCopy;
    private final int lo, hi;

    public RelaxInTask(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy, int lo, int hi) {
        this.g = g;
        this.dist = dist;
        this.pred = pred;
        this.distCopy = distCopy;
        this.lo = lo;
        this.hi = hi;
    }

    protected void compute() {
        if(hi - lo <= CUTOFF) {
            sequential(g, dist, pred, distCopy, lo, hi);
        } else {
            int mid = lo + (hi-lo)/2;

            RelaxInTask left = new RelaxInTask(g, dist, pred, distCopy, lo, mid);
            RelaxInTask right = new RelaxInTask(g, dist, pred, distCopy, mid, hi);

            left.fork();
            right.compute();
            left.join();
        }
    }

    public static void sequential(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy, int lo, int hi) {
        for(int i = lo; i < hi; i++) {
            for(int w = lo; w < hi; w++) { // each edge in range
                for(int key: g.get(w).keySet()) { // incoming edges (key)
                    if(distCopy[key] != GraphUtil.INF && distCopy[key] + g.get(w).get(key) < dist[w]) {
                        dist[w] = distCopy[key] + g.get(w).get(key);
                        pred[w] = key;
                    }
                }
            }
        }
    }

    public static void parallel(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy) {
        pool.invoke(new RelaxInTask(g, dist, pred, distCopy, 0, g.size()));
    }

}
