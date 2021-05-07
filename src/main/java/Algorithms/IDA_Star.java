package Algorithms;

import Tools.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class IDA_Star implements Solver {
    private NodeM goal;
    private NodeM start;
    private Direction[] d;
    private Stack<NodeM> st;
    private HashMap<String, NodeM> loop_avoidance;
    private Heuristic_manhattan heu;

    public IDA_Star() {
        st = new Stack<>();
    }

    public IDA_Star(NodeM goal, NodeM start) {
        this.goal = goal;
        this.start = start;
        this.loop_avoidance = new HashMap<>();
        this.heu = new Heuristic_manhattan();
        this.st = new Stack<>();

    }
    @Override
    public List<NodeM> solve() {
        // this threshold
        int iteration = 0;
        int t = new Heuristic_manhattan(start, goal).getCost();
        start.setF_n(t);
        while (t != Integer.MAX_VALUE) {
            int minf = Integer.MAX_VALUE;
            iteration ++;
            st.push(start);
            start.setC(color.White);
            loop_avoidance.put(start.toString(), start);
            while (!st.isEmpty()) {
                NodeM m = st.pop();
                // black means marked as out
                if (m.getC() == color.Black) {
                    loop_avoidance.remove(m);
                    continue;
                }
                    // mark as out
                    m.setC(color.Black);
                    st.push(m);
                    for (Direction d : Direction.values()) {
                        NodeM sib = new NodeM(m, d);
                        int cost = new Heuristic_manhattan(goal, sib).getCost() + sib.getCost() + sib.getF_n();
                        // set the f(n)
                        sib.setF_n(cost);
                      //  sib.setCost(cost);
                        if (cost > t) {
                            // min between minf and the f(n)
                            minf = Math.min(minf, cost);
                            continue;

                        }
                        if (loop_avoidance.containsKey(sib.toString()) && loop_avoidance.get(sib.toString()).getC() != color.Black) {
                            NodeM to_update = loop_avoidance.get(sib.toString());

                            if (to_update.getF_n() > cost) {
                                loop_avoidance.remove(to_update.toString());
                            } else {
                                continue;
                            }
                        }
                        if (sib.equals(goal)) {
                            System.out.println("Reached the goal");
                            return Path(m);
                        }
                        st.push(sib);
                        loop_avoidance.put(sib.toString(), sib);
                    }


            }
            t = minf;

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
