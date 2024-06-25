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
