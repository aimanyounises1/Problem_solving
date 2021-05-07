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
        System.out.println(result.getCost());
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
