# Problem_solving
### Motivation
![](https://github.com/aimanyounises1/Problem_solving/blob/master/tile_puzzle.jpg)
# 8-tile puzzle project with AI's algorithms :
### Description :
Our goal is to find the cheapest path from start state to the goal state. (the figure bellow explain that).
- BFS
We can perform a Breadth-first search on the state space tree. This always finds a goal state nearest to the root. But no matter what the initial state is, the algorithm attempts the same sequence of moves like DFS.
- DFID
Here we are implemented a combination between DFS and BFS , this algorithm will find the optimal path because he simulates the bfs algorithm on each level.
- A*
Here we move on , and define a new family of algorithms which called informed algorithms which will use heuristic function in order to minimize the time and space complexity.
- IDA*
The iterative algorithm of A* , using a stack and loop avoidance.
- DFBNB
![](https://github.com/aimanyounises1/Problem_solving/blob/master/src/out.png)
