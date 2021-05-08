package Algorithms;

import Tools.Direction;
import Tools.NodeM;

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
         * 1,_,4
         * 3,5,6
         * 2,_,7
         */
        int[][] two_zeros = {{1, 8, 4}, {3, 5, 6}, {2, 0, 7}};
        /**
         *
         * 1,2,3
         * 4,5,6
         * 7,_,_
         *
         */
        int[][] two_zeros_out = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        NodeM two = new NodeM(two_zeros);
        NodeM two_goal = new NodeM(two_zeros_out);
        /**
         * 1,2,3,4
         * 5,6,7,8
         * 9,10,11,_
         */
        int[][] s_g = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 0}};
        NodeM s_sate = new NodeM(s_s);
//        NodeM ss = new NodeM(s_sate, Direction.Left);
//        // System.out.println(s_sate);
//        // System.out.println(ss);
        NodeM g_state = new NodeM(s_g);
//        NodeM new_s = new NodeM(s_g);
//        System.out.println(new_s.equals(g_state));
        BFS bfs = new BFS(two, two_goal);
        List<NodeM> Path1 = bfs.solve();
        if (Path1 != null)
            for (NodeM state : Path1) {
                System.out.println(state.toString());
            }

        System.out.println("--------------------- IDA* -------------------------------");

        //  DFID dfid = new DFID(s_sate, g_state,false,false);
        // List<NodeM> path = dfid.solver();
        //if (path != null)
        //for (NodeM m : path) {
        //System.out.println(m.toString());
        //}
//        IDA_Star IDA = new IDA_Star(g_state, s_sate);
//
//      A_star a = new A_star(g_state, s_sate);
//        List < NodeM> path = a.solve();
//        for (NodeM m : path) {
//         System.out.println(m);
//        }
//    DFBnB df = new DFBnB(g_state , s_sate);
//        List <NodeM> Path = df.solve();
//        for (NodeM m : Path){
//            System.out.println(m);
//        }
//    }
    }
}
