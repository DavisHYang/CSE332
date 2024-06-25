package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private LinkedNode<E> head;
    private LinkedNode<E> tail;
    private int size;

    public ListFIFOQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(E work) {
        if(!hasWork()) {
            head = new LinkedNode<E>(work);
            tail = head;
        } else {
            tail.next = new LinkedNode<E>(work);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E peek() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    @Override
    public E next() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }

    private class LinkedNode<E> {
        E data;
        LinkedNode<E> next;

        LinkedNode(E data) {
            this.data = data;
            this.next = null;
        }

    }
}
