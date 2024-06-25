package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {

    private int size;
    private E[] q;
    private int head;
    private int tail;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.head = 0;
        this.tail = 0;
        this.q = (E[])new Comparable[capacity];
        this.size = 0;
    }

    @Override
    public void add(E work) {
        if(tail == head && size != 0) {
            throw new IllegalStateException();
        }
        q[tail] = work;
        tail = (tail + 1) % capacity();
        size++;
    }

    @Override
    public E peek() {
        if(hasWork()){
            return q[head];
        }
        throw new NoSuchElementException();
    }

    @Override
    public E peek(int i) {
        if(hasWork()) {
            if((i >= 0 && i < size)) {
                return q[(head+i)%capacity()];
            }
            throw new IndexOutOfBoundsException();
        }
        throw new NoSuchElementException();
    }

    @Override
    public E next() {
        if(hasWork()) {
            E result = q[head];
            head = (head+1) % capacity();
            size--;
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void update(int i, E value) {
        if(hasWork()) {
            if((i >= 0 && i < size)) {
                q[(head+i)%capacity()] = value;
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.q = (E[])new Comparable[capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        int thisInd = 0;
        int otherInd = 0;
        while(thisInd < this.size && otherInd < other.size()) {
            int comparison = this.peek(thisInd).compareTo(other.peek(otherInd));
            if(comparison > 0) {
                return 1;
            } else if(comparison < 0) {
                return -1;
            }
            thisInd++;
            otherInd++;
        }
        int sizeDiff = this.size - other.size();
        if(sizeDiff == 0) {
            return 0;
        } else if(sizeDiff < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            // Uncomment the line below for p2 when you implement equals
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here
            return this.hashCode() == other.hashCode();
        }
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(size);
        int c;
        for(int i = 0; i < size; i++) {
            c = this.peek(i).hashCode();
            result = result * 31 + c;
        }
        result = result * 31 + Integer.hashCode(head);
        result = result * 31 + Integer.hashCode(tail);
        return result;
    }
}
