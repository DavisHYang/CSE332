package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {

    private int size;
    private E[] q;
    private int head;
    private int tail;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.head = 0;
        this.tail = 0;
        this.q = (E[])new Object[capacity];
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
        this.q = (E[])new Object[capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
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
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
