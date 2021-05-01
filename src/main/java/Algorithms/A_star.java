package Algorithms;

import Tools.Heuristic_manhattan_comparator;
import Tools.NodeM;
import Tools.Solver;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class A_star implements Solver {
    /**
     * First we will start build the constructor and the heuristic function
     * And then we will use priority queue to run the algorithm.
     * we will think about implementation about how to implement the Manhttan's distance.
     * we will use the f(n) = g(n) + h(n) , we will check every time which node we are going to generate.
     */
    private  NodeM goal ;
    private NodeM start ;
    private Direction [] d;
    private HashMap<String, NodeM> close_list;
    private HashMap<String, NodeM> open_list;
    private PriorityQueue<NodeM> q;
    private Heuristic_manhattan_comparator heuristic;

    public A_star(){
        open_list = new HashMap<>();
        close_list = new HashMap<>();
        d = Direction.values();
    }


    public A_star(NodeM goal , NodeM start){
        this.goal = goal;
        this.start = start;
        open_list = new HashMap<>();
        close_list = new HashMap<>();
        d = Direction.values();
        heuristic = new Heuristic_manhattan_comparator();
        q = new PriorityQueue<>(heuristic);
    }


    @Override
    public List<NodeM> solve() {
        return null;
    }
}
