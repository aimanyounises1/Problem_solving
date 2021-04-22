package Algorithms;

import Tools.NodeM;
import javafx.scene.control.skin.TextInputControlSkin;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.List;

public class Bug_And_Fix {
    public static void main(String[] args) {
        /**
         * 1,2,3,4
         * 5,6,11,7
         * 9,10,8,_
         */
        int[][] s_s = {{1, 2, 3, 4}, {5, 6, 11, 7}, {9, 10, 8, 0}};
        System.out.println(Arrays.toString(s_s));
        /**
         * 1,2,3,4
         * 5,6,7,8
         * 9,10,11,_
         */
        int[][] s_g = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 0}};
        NodeM s_sate = new NodeM(s_s);
        NodeM ss = new NodeM(s_sate, Direction.Left);
        // System.out.println(s_sate);
        // System.out.println(ss);
        NodeM g_state = new NodeM(s_g);
        NodeM new_s = new NodeM(s_g);
        System.out.println(new_s.equals(g_state));
      /*  BFS bfs = new BFS(s_sate, g_state);
        List<NodeM> Path = bfs.solve();
        if (Path != null)
            for (NodeM state : Path) {
                System.out.println(state.toString());
            }*/
        System.out.println("--------------------- DFS -------------------------------");
        DFID dfid = new DFID(s_sate, g_state,false,false);
        List<NodeM> path = dfid.solver();
        if (path != null)
            for (NodeM m : path) {
                System.out.println(m.toString());
            }


    }

}
