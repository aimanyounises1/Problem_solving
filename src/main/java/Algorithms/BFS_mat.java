package Algorithms;

import Tools.NodeM;
import Tools.color;
import javafx.scene.control.skin.TextInputControlSkin;
import org.w3c.dom.Node;

import java.security.CodeSigner;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
// reminder : now u need to generate all sons and add them to the queue.
// tomorrow finish the bfs algorithm.
// use stack to store the path and then print it.
public class BFS_mat {
private Queue <NodeM> q;
private int moves_num;
private NodeM S_goal;
private NodeM Start_s;
private Direction d;

public BFS_mat(NodeM start,NodeM goal){
    this.Start_s = start;
    this.S_goal = goal;
    q = new ArrayBlockingQueue<NodeM>(10);
    q.add(Start_s);
}
public List<String> Bfs_algo(){
    Direction []d_arr = Direction.values();
    List<NodeM> list = new LinkedList<NodeM>();
    while(!q.isEmpty()){
        NodeM current_state  =  q.poll();
        // generating the son of this color
        current_state.setC(color.Yellow);
        for (Direction d : d_arr){
            NodeM sib = new NodeM(Start_s,d);
            if (!q.contains(sib)){
                q.add(sib);
            }
        }
        if (current_state.equals(S_goal.getMat())){
           break;
        }
        // let's generate all childer states of current state

    }
    return null;
}

}
// we want to start bfs --> generate all nodes in q



