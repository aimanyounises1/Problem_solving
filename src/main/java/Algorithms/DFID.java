package Algorithms;

import Tools.Direction;
import Tools.NodeM;
import Tools.color;

import java.util.*;

public class DFID {
    private NodeM Start_s;
    private NodeM Goal_s;
    private List<NodeM> goals;
    private HashSet<String> H;
    private boolean with_time;
    private boolean with_open;

    public DFID() {
        H = new HashSet<String>();
    }
    public DFID(NodeM start, NodeM goal,boolean with_t, boolean with_open) {
        this.Goal_s = goal;
        this.Start_s = start;
        this.with_time = with_t;
        goals = new LinkedList<NodeM>();
        this.with_time = with_open;
    }

    public List<NodeM> solver() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            // declare new hashset
            H = new HashSet<>();
            // use limited_dfs recursive function
        NodeM result = Limited_DFS(Start_s, i, H);
            // check if we had cutoff
            if (result.getC() != color.Black)// means we don't have  cutoff
                return Path(result);
        }
        return null;
    }

    private NodeM Limited_DFS(NodeM n, int limit, HashSet<String> H) {
        // white --> False , Black --> True , Yellow - > Fail
        NodeM cutoff = new NodeM(this.Start_s);
        cutoff.setC(color.Black);
        if (n.equals(Goal_s)) {
            System.out.println("Reached the goal");
            return n;
        } else if (limit == 0) {
            // return the cutoff
            return cutoff;
        } else {
            H.add(n.toString());
            // cutoff is equal to false
            cutoff.setC(color.White);
            for (Direction d : Direction.values()) {
                // generate new nodes
                NodeM to_add = new NodeM(n, d);
                System.out.println(to_add);
                //set the parent of them
                to_add.setParent(cutoff);
                if (H.contains(to_add.toString())) {
                    continue;
                } else {
                    NodeM result = Limited_DFS(to_add, (limit - 1), H);
                    // means we had cutoff
                    if (result.getC() == color.Black) {
                        cutoff.setC(color.Black);
                    } else if (result.getC() != color.Yellow) {
                        return result;
                    }
                }
            }
            H.remove(n.toString());
            // means we had cutoff
            if (cutoff.getC() == color.Black) {
                return cutoff;
            } else {
                // failed
                cutoff.setC(color.Yellow);
                return cutoff;
            }
        }
    }

    public List<NodeM> Path(NodeM goal_s) {
        List<NodeM> list = new LinkedList<NodeM>();
        list.add(goal_s);
        NodeM iter = goal_s.getParent();
        while (iter != null) {
            list.add(iter);
            iter = iter.getParent();
        }
        return list;
    }

    public NodeM getGoal_s() {
        return Goal_s;
    }

    public NodeM getStart_s() {
        return Start_s;
    }
}
