package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;

import java.util.Iterator;

/**
 * 1. The list is typically not sorted.
 * 2. Add new items to the front of the list.
 * 3. Whenever find or insert is called on an existing key, move it
 * to the front of the list. This means you remove the node from its
 * current position and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 * elements to the front.  The iterator should return elements in
 * the order they are stored in the list, starting with the first
 * element in the list. When implementing your iterator, you should
 * NOT copy every item to another dictionary/list and return that
 * dictionary/list's iterator.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    private Node<K, V> head;
    private int size = 0;

    @Override
    public V insert(K key, V value) {
        if(head == null) {
            head = new Node<K,V>(key, value);
            size++;
            return null;
        }
        V prevVal = find(key);
        Node<K, V> temp;
        if(prevVal != null) {
            temp = head;
            if(temp.key.equals(key)) {
                temp.value = value;
            } else {
                while(!temp.key.equals(key)) {
                    temp = temp.next;
                }
                temp.value = value;
            }
        } else {
            temp = new Node<>(key, value);
            temp.next = head;
            head = temp;
            size++;
        }
        return prevVal;
    }

    @Override
    public V find(K key) {
        if(head == null) {
            return null;
        }
        if(head.key.equals(key)) {
            return head.value;
        }
        Node<K, V> cur = head;
        Node<K, V> prev = null;
        while(cur != null) {
            if(cur.key.equals(key)) {
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        if(cur != null && cur.key.equals(key)) {
            cur.next = head;
            head = cur;
            return head.value;
        }
        return null;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new SimpleIterator<Item<K, V>>() {
            Node<K, V> temp = head;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Item<K, V> next() {
                Node<K, V> result = temp;
                temp = temp.next;
                return new Item(result.key, result.value);
            }
        };
    }

    @Override
    public int size() {
        return this.size;
    }
    
    public class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
