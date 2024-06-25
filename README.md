# CSE332 - Data Structures and Parallelism
## Course Description
Covers abstract data types and structures including dictionaries, balanced trees, hash tables, priority queues, and graphs; sorting; asymptotic analysis; fundamental graph algorithms including graph search, shortest path, and minimum spanning trees; multithreading and parallel algorithms; P and NP complexity classes.

## Projects
- [Project 1](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-1)
- [Project 2](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-2)
- [Project 3](https://github.com/DavisHYang/CSE332/blob/main/README.md#project-3)

## Project 1

Implementing various different types of WorkLists and a basic trie.

### ListFIFOQueue
- An implementation of a Queue using a LinkedList
- All WorkList operations *O(1)*
### ArrayStack
- An implementation of a Stack using an Array
- Doubles in size when capacity is reached
- All WorkList operations *O(1)* amortized
### CircularArrayFIFOQueue
- An implementation of a Queue using a fixed-length Array
- Uses circular structure to loop
- All WorkList operations *O(1)*
### HashTrieMap
- An implementation of a Map using a Trie
- Uses a HashMap to store pointers
- insert, find, findPrefix, and delete operations *O(d)* where d is the number of letters in the key
### HashTrieSet
- An implementation of a Set using a HashTrieMap
- Same performance as HashTrieMap

## Project 2


## Project 3
