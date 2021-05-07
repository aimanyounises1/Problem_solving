package Algorithms;

import Tools.*;

import java.util.*;

public class DFBnB implements Solver {

    private NodeM goal;
    private NodeM start;
    private Direction[] d;
    private Stack<NodeM> st;
    private HashMap<String, NodeM> loop_avoidance;
    public DFBnB(){
        st = new Stack<>();
    }

    public DFBnB(NodeM goal , NodeM start){
        this.goal = goal;
        this.start = start;
        this.loop_avoidance = new HashMap<>();
        this.st = new Stack<>();
    }

    /**
     *
     * DFBnB(Node start, Vector Goals)
     * 1. L  make_stack(start) and H  make_hash_table(start)
     * 2. result  null, t  ∞ // should be set to a strict upper bound in an infinite graph
     * 3. While L is not empty
     *
     * 1. n  L.remove_front()
     * 2. If n is marked as “out”
     * 1. H.remove(n)
     * 3. Else
     * 1. mark n as “out” and L.insert(n)
     * 2. N  apply all of the allowed operators on n
     * 3. sort the nodes in N according to their f values (increasing order)
     * 4. For each node g from N according to the order of N
     * 1. If f(g) >= t
     * 1. remove g and all the nodes after it from N
     * 2. Else If H contains g’=g and g’ is marked as “out”
     * 1. remove g from N
     * 3. Else If H contains g’=g and g’ is not marked as “out”
     * 1. If f(g’)<=f(g)
     * 1. remove g from N
     * 2. Else
     * 1. remove g’ from L and from H
     * 4. Else If goal(g) // if we reached here, f(g) < t
     * 1. t  f(g)
     * 2. result  path(g) // all the “out” nodes in L
     * 3. remove g and all the nodes after it from N
     *
     * 5. insert N in a reverse order to L and H
     *
     * 4. Return result
     *
     * Solution
     * @return
     */
    @Override
    public List<NodeM> solve() {
        st.push(start);
        loop_avoidance.put(start.toString() , start);
        int t = Integer.MAX_VALUE;
        NodeM result = null;
        while(!st.isEmpty()){
            NodeM m = st.pop();
            // marked out
            if (m.getC() == color.Black){
                loop_avoidance.remove(m.toString());
            }else{
                // mark as out
                m.setC(color.Black);
                List <NodeM> list = new LinkedList<>();
               for (Direction d : Direction.values()){
                   NodeM sib = new NodeM(m,d);
                   int cost = new Heuristic_manhattan(goal, sib).getCost() + sib.getCost() + sib.getF_n();
                   sib.setF_n(cost);
                 list.add(sib);
               }
               Collections.sort(list, new Heuristic_manhattan_comparator());
               for (int i = 0 ; i < list.size() ; i++){
                   NodeM node = list.get(i);
                   if(node.getF_n() >= t){
                       list.subList(i , list.size()).clear();
                       continue;
                   }
                   if (loop_avoidance.containsKey(node.toString()) && loop_avoidance.get(node.toString()).getC() == color.Black){
                        list.remove(i--);
                   }
                   if (loop_avoidance.containsKey(node.toString()) && loop_avoidance.get(node.toString()).getC() != color.Black){
                       NodeM g_tag = loop_avoidance.get(i);
                       if (node.getF_n() >= g_tag.getF_n()){
                            list.remove(i--);
                       }else{
                           // i will continue tomorrow , I'm tired
                           g_tag.setC(color.Black);
                           loop_avoidance.remove(g_tag.toString());
                           st.remove(i);
                       }
                   }
                   if (node.equals(goal)){
                       System.out.println("Reached the goal");
                       t = node.getF_n();
                       result = node;
                       list.subList(i , list.size()).clear();
                   }
               }
               Collections.reverse(list);
               for (NodeM s : list){
                   loop_avoidance.put(s.toString() , s);
                   st.push(s);
               }
            }
        }
        return Path(result);
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
