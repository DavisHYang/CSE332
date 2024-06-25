package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeapComparable<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;

    public MinFourHeapComparable() {
        data = (E[]) new Comparable[10];
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
        data = (E[])(new Comparable[10]);
        size = 0;
    }

    private void resize() {
        E[] temp = (E[]) new Comparable[data.length*2];
        for(int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private int percolateUp(int ind) {
        //parents is at (ind-1)/4
        while(ind != 0 && data[ind].compareTo(data[(ind-1)/4]) <= 0) {
            swap(ind, (ind-1)/4);
            ind = (ind-1)/4;
        }
        return ind;
    }

    private int percolateDown(int ind) {
        while(4*ind+1 <= size) {
            //children indicies
            int first = ind*4+1;
            int second = first+1;
            int third = second+1;
            int fourth = third+1;
            int minInd = findMin(first, findMin(second, findMin(third, fourth)));
            if(data[minInd].compareTo(data[ind]) < 0) {
                swap(ind, minInd);
                ind = minInd;
            } else {
                break;
            }
        }
        return ind;
    }

    private int findMin(int ind, int ind2) {
        if(ind2 > size && ind > size) {
            return size-1;
        } 
        if(ind2 > size) {
            return data[ind].compareTo(data[size-1]) < 0 ? ind : size-1;
        }
        if(ind > size) {
            return size-1;
        }
        return data[ind].compareTo(data[ind2]) < 0 ? ind : ind2;
    }

    private void swap(int ind1, int ind2) {
        E temp = data[ind1];
        data[ind1] = data[ind2];
        data[ind2] = temp;
    }
}
