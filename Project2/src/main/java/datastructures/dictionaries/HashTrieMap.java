package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;
import cse332.interfaces.trie.TrieMap;
import cse332.types.AlphabeticString;
import cse332.types.BString;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Dictionary<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<A, HashTrieNode>(AVLTree::new);
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return new SimpleIterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>>() {
                Iterator<Item<A, HashTrieNode>> it = pointers.iterator();
                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public Entry<A, HashTrieMap<A, K, V>.HashTrieNode> next() {
                    Item<K, V> cur = (Item<K,V>)it.next();
                    return new SimpleEntry(cur.key, cur.value);
                }
            };
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
            if(temp.pointers.find(s) != null) {
                temp = temp.pointers.find(s);
            } else {
                temp.pointers.insert(s, new HashTrieNode());
                temp = temp.pointers.find(s);
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
            if(temp.pointers.find(s) != null) {
                temp = temp.pointers.find(s);
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
            if(temp.pointers.find(s) != null) {
                temp = temp.pointers.find(s);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
