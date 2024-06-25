package paralleltasks;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ArrayCopyTask extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 1;

    public static int[] copy(int[] src) {
        int[] dst = new int[src.length];
        pool.invoke(new ArrayCopyTask(src, dst, 0, src.length));
        return dst;
    }

    private final int[] src, dst;
    private final int lo, hi;

    public static void sequential(int[] src, int[] dst, int lo, int hi) {
        for(int i = lo; i < hi; i++) {
            dst[i] = src[i];
        }
    }

    public ArrayCopyTask(int[] src, int[] dst, int lo, int hi) {
        this.src = src;
        this.dst = dst;
        this.lo = lo;
        this.hi = hi;
    }

    @SuppressWarnings("ManualArrayCopy")
    protected void compute() {
        if(this.hi - this.lo <= CUTOFF) {
            // Do sequentially
            sequential(this.src, this.dst, this.lo, this.hi);
        } else {
            int mid = lo + (hi-lo)/2;

            ArrayCopyTask left = new ArrayCopyTask(this.src, this.dst, this.lo, mid);
            ArrayCopyTask right = new ArrayCopyTask(this.src, this.dst, mid, this.hi);

            left.fork();
            right.compute();
            left.join();
        }
    }
}
