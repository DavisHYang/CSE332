package p2.sorts;

import datastructures.worklists.MinFourHeap;

import java.util.Comparator;

public class HeapSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        MinFourHeap<E> sorter = new MinFourHeap<E>(comparator);
        for(E i: array) {
            sorter.add(i);
        }

        for(int i = 0; sorter.hasWork(); i++) {
            array[i] = sorter.next();
        }
    }
}
