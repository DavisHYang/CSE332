package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E> extends PriorityWorkList<E>{
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;
    private Comparator<E> c;

    public MinFourHeap(Comparator<E> c) {
        this.c = c;
        data = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public boolean hasWork() {
        return size > 0;
    }

    @Override
    public void add(E work) {
        if(size == data.length) {
            resize();
        }
        data[size] = work;
        percolateUp(size);
        size++;
    }

    @Override
    public E peek() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        return data[0];
    }

    @Override
    public E next() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        E result = data[0];
        size--;
        data[0] = data[size];
        percolateDown(0);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = (E[])(new Object[10]);
        size = 0;
    }

    private void resize() {
        E[] temp = (E[]) new Object[data.length*2];
        for(int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private void percolateUp(int ind) {
        //parents is at (ind-1)/4
        int parent = (ind-1)/4;
        while(ind != 0 && c.compare(data[ind], (data[parent])) < 0) {
            swap(ind, parent);
            ind = parent;
            parent = (parent-1)/4;
        }
    }

    private void percolateDown(int ind) {
        int minInd = findMin(ind);
        while(minInd >= 0) {
            //children indicies
            if(c.compare(data[minInd], (data[ind])) < 0) {
                swap(ind, minInd);
                ind = minInd;
            } else {
                break;
            }
            minInd = findMin(ind);
        }
    }

    private int findMin(int ind) {
        int min = -1;
        int first = ind*4+1;
        int last = Math.min(first + 3, size-1);
        for(int i = first; i <= last; i++) {
            if (min == -1 || c.compare(data[i], data[min]) < 0) {
                min = i;
            }
        }
        return min;
    }

    private void swap(int ind1, int ind2) {
        E temp = data[ind1];
        data[ind1] = data[ind2];
        data[ind2] = temp;
    }

}
