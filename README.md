# CSE332 - Data Structures and Parallelism
## Course Description
Covers abstract data types and structures including dictionaries, balanced trees, hash tables, priority queues, and graphs; sorting; asymptotic analysis; fundamental graph algorithms including graph search, shortest path, and minimum spanning trees; multithreading and parallel algorithms; P and NP complexity classes.

## Projects
- [Project 1](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-1) - Implementing different types of WorkLists and a basic trie.
- [Project 2](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-2) - Implementing different types of Dictionaries and sorting algorithms.
- [Project 3](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-3) - Implementing sequential and parallel versions of the Bellman-Ford Algorithm

## Project 1

Implementing different types of WorkLists and a basic trie.

### ListFIFOQueue
- An implementation of a Queue using a LinkedList
- All WorkList operations *O(1)*
### ArrayStack
- An implementation of a Stack using a dynamic Array
- Doubles in size when capacity is reached
- All WorkList operations *O(1)* amortized
### CircularArrayFIFOQueue
- An implementation of a Queue using a fixed-length Array
- Uses circular structure to loop around
- All WorkList operations *O(1)*
### HashTrieMap
- An implementation of a Map using a Trie
- Uses a Node-based Trie implementation
- Uses a HashMap to store pointers
- insert, find, findPrefix, and delete operations *O(d)* where d is the number of letters in the key
### HashTrieSet
- An implementation of a Set using a HashTrieMap
- Same performance as HashTrieMap

## Project 2

Implementing different types of Dictionaries and sorting algorithms.

All of these implementations were used to drive word suggestion, spelling correction, and autocompletion in a chat application called uMessage.

### MinFourHeapComparable
- An implementation of a Priority Queue using a min Four-Heap
- Uses an Array under the hood
- Compares elements using compareTo
- *O(1)* peekMin, *O(log n)* insert & deleteMin
### MoveToFrontList
- An implementation of a Dictionary using a MoveToFrontList
- Uses a LinkedList under the hood
- Recently referenced items moved to front and inserts to front
- Poor worst-case runtime, good amortized runtime
### CircularArrayFIFOQueue update
- Implemented a compareTo and equals methods for CircularArrayFIFOQueue
### AVLTree
- An implementation of a Dictionary using an AVLTree
- Uses a Node-based tree under the hood
- *O(log n)* runtimes for insert & find
### ChainingHashTable
- An implementation of a Dictionary using a ChainingHashTable
- Uses an dynamic Array and generic Dictionary under the hood
- Uses a custom hashing algorithm
### MinFourHeap
- An implementation of a Priority Queue using a min Four-Heap
- Uses an Array under the hood
- Compares elements using a Comparator
### HeapSort
- An implementation of HeapSort
- Uses a MinFourHeap under the hood
### QuickSort
- An implementation of QuickSort
- Compares elements using a Comparator
### TopKSort
- An implementation of TopKSort
- Uses a MinFourHeap under the hood
- *O(n log k)* runtime
## Project 3

Implementing sequential and parallel versions of the Bellman-Ford Algorithm

### Parser parse()
- Parses an input adjacency matrix to an adjacency list
- Lists contain vertices reached through outgoing edges
### OutSequential
- A sequential implementation of the Bellman-Ford Algorithm using outgoing edges
- Uses parse() to parse graph data
- *O(VE)* runtime
### OutParallelBad
- A parallel implementation of the Bellman-Ford Algorithm
- Attempts to improve performance by copying and relaxing edges in parallel
- Causes a write-write concurrency issue
- *O(V^2 + VE/P)* runtime
### OutParallelLock
- A parallel implementation of the Bellman-Ford Algorithm using locking
- Uses Java's ReentrantLock to eliminate concurrency issues
- Uses a fine-grained locking system
- *O(V^2 + VE/P)* runtime
### Parser parseInverse
- Parses an input adjacency matrix to an adjacency list
- Lists contain vertices reached through incoming edges
### InParallel
- A parallel implementation of the Bellman-Ford Algorithm using incoming edges
- Uses parseInverse() to parse graph data
- Eliminates race conditions by using incoming edges rather than locking
- *O(V^2 + VE/P)* runtime
