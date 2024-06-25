package p2.sorts;

import java.util.Comparator;

public class QuickSort {
    private static final int CUTOFF = 10;

    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        sort(array, comparator, 0, array.length);
    }

    private static <E> void sort(E[] arr, Comparator<E> c, int lo, int hi) {
        if(hi - lo < CUTOFF) {
            insertionSort(arr, lo, hi, c);
        } else {
            int pivotIndex = partition(arr, lo, hi, c);
    
            sort(arr, c, lo, pivotIndex); //sort left side
            sort(arr, c, pivotIndex, hi); //sort right side
        }
    }

    private static <E> int partition(E[] arr, int lo, int hi, Comparator<E> c) {
        int pivot = findMed(lo, hi-1, arr, c);
        swap(arr, lo, pivot);
        int i = lo+1;
        int j = hi-1;
        while(i < j) {
            if(c.compare(arr[j], arr[lo]) > 0) { //move left if greater than pivot on right side
                j--;
            } else if(c.compare(arr[i], arr[lo]) <= 0) { //move right if less than pivot on left side
                i++;
            } else { //swap if both are misplaced
                swap(arr, i, j);
            }
        }
        swap(arr, lo, i); //move pivot to correct position
        return i;
    }

    private static <E> void insertionSort(E[] arr, int lo, int hi, Comparator<E> c) {
        for (int i = lo+1; i < hi; i++) {
            E x = arr[i];
            int j;
            for (j=i-1; j>=0; j--) {
                if (c.compare(x, arr[j]) >= 0) { break; }
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = x;
        }
    }

    private static <E> void swap(E[] array, int ind1, int ind2) {
        E temp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = temp;
    }

    private static <E> int findMed(int start, int end, E[] arr, Comparator<E> c) {
        int midIndex = start + (end - start)/2;
        E first = arr[start];
        E last = arr[end];
        E mid = arr[midIndex];
        if ((c.compare(first, mid) <= 0 && c.compare(mid, last) <= 0) ||
            (c.compare(last, mid) <= 0 && c.compare(mid, first) <= 0)) {
                return midIndex;
        } else if ((c.compare(mid, first) <= 0 && c.compare(first, last) <= 0) ||
                    (c.compare(last, first) <= 0 && c.compare(first, mid) <= 0)) {
                return start;
        } else {
            return end;
        }
    }

    public static <E> void printArr(E[] arr){
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
