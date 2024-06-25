
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
