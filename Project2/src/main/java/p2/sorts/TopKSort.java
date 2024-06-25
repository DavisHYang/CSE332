package p2.sorts;

import datastructures.worklists.MinFourHeap;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }


    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> kHeap = new MinFourHeap<E>(comparator);
        if(k > array.length) {
            k = array.length;
        }
        for(int i = 0; i < array.length; i++) {
            kHeap.add(array[i]);
            array[i] = null;
            while(kHeap.size() > k) {
                kHeap.next();
            }
        }
        for(int i = 0; kHeap.hasWork(); i++) {
            array[i] = kHeap.next();
        }
    }
}
