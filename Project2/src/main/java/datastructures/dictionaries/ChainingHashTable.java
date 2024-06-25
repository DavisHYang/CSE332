package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * - You must implement a generic chaining hashtable. You may not
 *   restrict the size of the input domain (i.e., it must accept
 *   any key) or the number of inputs (i.e., it must grow as necessary).
 *
 * - ChainingHashTable should rehash as appropriate (use load factor as shown in lecture!).
 *
 * - ChainingHashTable must resize its capacity into prime numbers via given PRIME_SIZES list.
 *   Past this, it should continue to resize using some other mechanism (primes not necessary).
 *
 * - When implementing your iterator, you should NOT copy every item to another
 *   dictionary/list and return that dictionary/list's iterator.
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K, V>[] arr;
    private double load;
    private int curPrime;

    static final int[] PRIME_SIZES =
            {11, 23, 47, 97, 193, 389, 773, 1549, 3089, 6173, 12347, 24697, 49393, 98779, 197573, 395147};

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        curPrime = 0;
        arr = new Dictionary[nextPrime(curPrime)];
        load = 0;
    }

    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if(load > 1) {
            resize();
        }
        V prev = find(key);
        int ind = Math.abs(key.hashCode()) % arr.length;
        if(arr[ind] == null) {
            arr[ind] = newChain.get();
        }
        arr[ind].insert(key, value);

        if(prev == null) {
            size++;
            load = 1.0*(size)/arr.length;
        }
        return prev;
    }

    private void resize() {
        curPrime++;

        Dictionary<K, V>[] resize = new Dictionary[nextPrime(curPrime)];
        Dictionary<K, V>[] oldArr = arr;
        arr = resize;
        int prevSize = size;

        Iterator<Item<K, V>> it;
        Item<K, V> temp;
        for(int i = 0; i < oldArr.length; i++) {
            if(oldArr[i] != null) {
                it = oldArr[i].iterator();
                while(it.hasNext()) {
                    temp = it.next();
                    insert(temp.key, temp.value);
                }
            }
        }
        size = prevSize;
        load = (1.0*size)/arr.length;
    }

    @Override
    public V find(K key) {
        int ind = Math.abs(key.hashCode()) % arr.length;
        if(arr[ind] == null) {
            return null;
        }
        for(Item<K, V> k : arr[ind]) {
            if(k.key.equals(key)) {
                return k.value;
            }
        }
        return null;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new SimpleIterator<Item<K,V>>() {
            private Iterator<Item<K,V>> it = arr[0] == null ? null : arr[0].iterator();
            private int curind = 1;

            @Override
            public Item<K, V> next() {
                if(!hasNext()) {
                    return null;
                }
                return it.next();
            }

            @Override
            public boolean hasNext() {
                if(it != null && it.hasNext()) {
                    return true;
                }
                while(curind < arr.length && (it == null || !it.hasNext())) {
                    if(arr[curind] != null) {
                        it = arr[curind].iterator();
                        if(it != null && it.hasNext()) {
                            curind++;
                            return true;
                        }
                    }
                    curind++;
                }
                return false;
            }

        };
    }

    /**
     * Temporary fix so that you can debug on IntelliJ properly despite a broken iterator
     * Remove to see proper String representation (inherited from Dictionary)
     */
    @Override
    public String toString() {
        String result = "";
        for(Item<K, V> d : this) {
            result = result + d + ", ";
        }
        return "{" + result + "}";
    }

    private int nextPrime(int n) {
        if(n < PRIME_SIZES.length) {
            return PRIME_SIZES[n];
        } 
        int i = arr.length*2+1;
 
        while (!isPrime(i)) {
            i+=2;
        }
        return i;
    }

    private boolean isPrime(int k)
    {
      // Using concept of prime number can be represented
      // in form of (6*k + 1) or(6*k - 1)
      for (int i = 5; i * i <= k; i = i + 6)
        if (k % i == 0 || k % (i + 2) == 0)
          return false;
   
      return true;
    }
}
