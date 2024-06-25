package paralleltasks;

import cse332.graph.GraphUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.Lock;

public class RelaxOutTaskLock extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 10;

    private final Lock[] locks;
    private final List<Map<Integer, Integer>> g;
    private final int[] dist, pred, distCopy;
    private final int lo, hi;

    public RelaxOutTaskLock(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy, int lo, int hi, Lock[] locks) {
        this.g = g;
        this.dist = dist;
        this.pred = pred;
        this.distCopy = distCopy;
        this.lo = lo;
        this.hi = hi;
        this.locks = locks;
    }

    protected void compute() {
        if(hi - lo <= CUTOFF) {
            // Acquire locks for this range
            for(int i = lo; i < hi; i++) {
                locks[i].lock();
            }

            sequential(g, dist, pred, distCopy, lo, hi, locks);

            // Free locks for this range
            for(int i = lo; i < hi; i++) {
                locks[i].unlock();
            }
        } else {
            int mid = lo + (hi-lo)/2;

            RelaxOutTaskLock left = new RelaxOutTaskLock(g, dist, pred, distCopy, lo, mid, locks);
            RelaxOutTaskLock right = new RelaxOutTaskLock(g, dist, pred, distCopy, mid, hi, locks);

            left.fork();
            right.compute();
            left.join();
        }
    }

    public static void sequential(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy, int lo, int hi, Lock[] locks) {
        // Acquire locks for this range
        for(int i = lo; i < hi; i++) {
            locks[i].lock();
        }

        for(int i = lo; i < hi; i++) {
            for(int w = lo; w < hi; w++) { // each edge
                for(int key: g.get(w).keySet()) {
                    if(distCopy[w] != GraphUtil.INF && distCopy[w] + g.get(w).get(key) < dist[key]) {
                        dist[key] = distCopy[w] + g.get(w).get(key);
                        distCopy[key] = distCopy[w] + g.get(w).get(key);
                        pred[key] = w;
                    }
                }
            }
        }
        
        // Free locks for this range
        for(int i = lo; i < hi; i++) {
            locks[i].unlock();
        }
    }

    public static void parallel(List<Map<Integer, Integer>> g, int[] dist, int[] pred, int[] distCopy, Lock[] locks) {
        pool.invoke(new RelaxOutTaskLock(g, dist, pred, distCopy, 0, g.size(), locks));
    }
}
