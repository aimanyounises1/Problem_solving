package Algorithms;

import Tools.*;

import java.util.*;

public class A_star implements Solver {
    /**
     * First we will start build the constructor and the heuristic function
     * And then we will use priority queue to run the algorithm.
     * we will think about implementation about how to implement the Manhttan's distance.
     * we will use the f(n) = g(n) + h(n) , we will check every time which node we are going to generate.
     */
    private  NodeM goal ;
    private NodeM start ;
    private Direction[] d;
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
        int cost;
        q.add(start);
        open_list.put(start.toString() , start);
        while(!q.isEmpty()){
            NodeM current_state = q.poll();
            if (current_state.equals(goal)){
                System.out.println("I'm in goal");
                return Path(current_state);
            }
            for (Direction dir : this.d){
                NodeM sib = new NodeM(current_state,dir);
                sib.setParent(current_state);
                //  H(n)
                cost = new Heuristic_manhattan(goal ,sib).getCost() + sib.getCost() + sib.getF_n();
                // initialize the f(n) to be g(n) + h(n)
                sib.setF_n(cost);
                if (!close_list.containsKey(sib.toString()) && !open_list.containsKey(sib.toString())) {
                    q.add(sib);
                    open_list.put(sib.toString(), sib);

                    // if we have a current  the sib with min distance so we replace that
                    // so similar to Dijkstra algorithm.
                }else if(open_list.get(sib.toString()).getF_n() > cost){
                    // now F(n) is the new weight of this node .
                        sib.setF_n(cost);
                        sib.setParent(current_state);
                        NodeM m = open_list.get(sib.toString());
                        q.remove(m);
                        q.add(sib);
                        open_list.remove(m.toString() , m);
                }

            }
            open_list.remove(current_state);
        }
        return null;
    }
    public List<NodeM> Path(NodeM g){
        Stack<NodeM> st = new Stack<NodeM>();
        List<NodeM> list = new LinkedList<NodeM>();
        NodeM m = g;
        list.add(m);
        while (m.getParent() != null) {
            NodeM n = m.getParent();
            list.add(n);
            m = m.getParent();
        }
        list.add(goal);
        Collections.reverse(list);
        return list;
    }
}
