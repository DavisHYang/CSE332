package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {

    private E[] stack;
    private int top;
    private int size;

    public ArrayStack() {
        stack = (E[])(new Object[10]);
        size = 0;
        top = 0;
    }

    @Override
    public void add(E work) {
        if(top >= stack.length) {
            addHelper(); //transfer contents to larger array
        }
        stack[top] = work;
        top++;
        size++;
    }

    private void addHelper() {
        E[] temp = (E[])(new Object[stack.length*2]);
        for(int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    @Override
    public E peek() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        return stack[top-1];
    }

    @Override
    public E next() {
        if(!hasWork()) {
            throw new NoSuchElementException();
        }
        E data = stack[top-1];
        stack[top-1] = null;
        top--;
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        stack = (E[])(new Object[10]);
        size = 0;
        top = 0;
    }
}
