

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
