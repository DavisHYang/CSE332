package datastructures.dictionaries;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.trie.TrieMap;
import cse332.types.AlphabeticString;
import cse332.types.BString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode temp = (HashTrieNode)this.root;
        for(A s: key) {
            if(temp.pointers.containsKey(s)) {
                temp = temp.pointers.get(s);
            } else {
                temp.pointers.put(s, new HashTrieNode());
                temp = temp.pointers.get(s);
            }
        }
        V prevVal = temp.value;
        if(prevVal == null) {
            size++;
        }
        temp.value = value;
        return prevVal;
    }

    @Override
    public V find(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        if(isEmpty()) {
            return null;
        }
        HashTrieNode temp = (HashTrieNode)this.root;
        for(A s: key) {
            if(temp.pointers.containsKey(s)) {
                temp = temp.pointers.get(s);
            } else {
                return null;
            }
        }
        return temp.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        if(isEmpty()) {
            return false;
        }
        HashTrieNode temp = (HashTrieNode)this.root;
        for(A s: key) {
            if(temp.pointers.containsKey(s)) {
                temp = temp.pointers.get(s);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode temp = (HashTrieNode)this.root;
        HashTrieNode lastSplit = temp;
        for(A s: key) {
            if(temp.pointers.containsKey(s)) {
                temp = temp.pointers.get(s);
                if(temp.pointers.size() > 1 || (temp.value != null && temp.pointers.size() != 0)) {
                    lastSplit = temp;
                }
            } else {
                return;
            }
        }
        if(temp.value != null) {
            if(temp.pointers.size() == 0) {
                temp = (HashTrieNode)this.root;
                for(A s: key) {
                    if(temp.equals(lastSplit)) {
                        temp.pointers.remove(s);
                        break;
                    }
                    temp = temp.pointers.get(s);
                }
            } else {
                temp.value = null;
            }
            size--;
        }
    }

    @Override
    public void clear() {
        this.root = new HashTrieNode();
        this.size = 0;
    }
}
