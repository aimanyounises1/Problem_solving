package Algorithms;

import Tools.Direction;
import Tools.NodeM;
import Tools.Solver;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

// reminder : now u need to generate all sons and add them to the queue.
// tomorrow finish the bfs algorithm.
// use stack to store the path and then print it.
public class BFS implements Solver {
    private Queue<NodeM> q;
    private int moves_num;
    private NodeM S_goal;
    private NodeM Start_s;
    private Direction d;

    public BFS(NodeM start, NodeM goal) {
        this.Start_s = start;
        this.S_goal = goal;
        q = new ArrayBlockingQueue<NodeM>(200000);
        q.add(Start_s);
    }
    public List<NodeM> solve() {
        Direction[] d_arr = Direction.values();
        int count_iter = 0;
        q.add(Start_s);
        // to save the path of the open_list save it by Node Id
        Map<String, NodeM> open_list = new HashMap<String, NodeM>();
        // closed list to know which nodes we finished visiting
        Map<String,NodeM> closed_list = new HashMap<String,NodeM>();
        open_list.put(Start_s.toString(), Start_s);
        while (!q.isEmpty()) {
            // poll the value
            NodeM current_state = q.poll();
            count_iter++;
            closed_list.put(current_state.toString(),current_state);
            // remove from closed list
            open_list.remove(current_state);
            // add to closed list
            // for all possible direction
            for (Direction d : d_arr) {
                // make all possible movies
                 NodeM sib = new NodeM(current_state ,d);
                sib.setParent(current_state);
                // check if it's possible first if it's not then it's equal to null
                if (sib != null) {
                    // check if the new sibling of this node is exit in the queue
                    //how to check if node is exit with id
                    // exit !(true true) = false closed = true open false
                    if (!closed_list.containsKey(sib.toString()) && !open_list.containsKey(sib.toString())) {
                        q.add(sib);
                        System.out.println("In Direction");
                        System.out.println(sib.toString());
                        open_list.put(sib.toString(), sib);
                    }
                    if (sib.equals(S_goal)) {
                        System.out.println("I'm in goal state");
                        return Path(sib);
                    }
                }

            }
            open_list.remove(current_state.toString());

        }
        System.out.println("count iter is = " +  count_iter);
        // means that we didn't find a solution for our problem
        return null;
    }

    // path implementation
    public List<NodeM> Path(NodeM g) {
        Stack<NodeM> st = new Stack<NodeM>();
        List<NodeM> list = new LinkedList<NodeM>();
        NodeM m = g;
        list.add(m);
        while (m.getParent() != null) {
            NodeM n = m.getParent();
            list.add(n);
            m = m.getParent();
        }
        list.add(S_goal);
        Collections.reverse(list);

        return list;
    }
    //[1  2 3 4]
    //[5   6   ]
    //[9 10 7 8]

}




