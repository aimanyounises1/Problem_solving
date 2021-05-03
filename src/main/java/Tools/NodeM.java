package Tools;
import Algorithms.Direction;
import javafx.util.Pair;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author  younis aiman
 * Colors to know if node is visited or not in DFID algorithm
 */
public class NodeM {
    int mat[][];
    private color c;
    private NodeM parent;
    private Pair<Integer, Integer> index;
    private Pair<Integer, Integer> index1;
    private int cost;
    private int N;
    private int M;
    private int ID;
    private int f_n;
    private List<Pair<Integer, Integer>> list = new LinkedList<Pair<Integer, Integer>>();

    public NodeM(){}

    public NodeM(int[][] mat) {
        this.mat = new int[N = mat.length][M = mat[0].length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == 0) {
                    Pair <Integer,Integer> in = new Pair<Integer, Integer>(i , j);
                    list.add(in);
                }
                this.mat[i][j] = mat[i][j];
            }
        }
        index = list.get(0);
        if (list.size() == 2) {
            index1 = list.get(1);
        }
        c = color.White;
        this.mat = mat;

    }

    public NodeM(NodeM other) {
        this.mat = new int[N = other.mat.length][M = other.mat[0].length];
        for (int i = 0; i < N; i++) {
            for(int j = 0 ; j < M ; j++){
                if (other.mat[i][j] == 0) {
                    Pair <Integer,Integer> in = new Pair<Integer, Integer>(i , j);
                    this.list.add(in);
                }
                this.mat[i][j] = other.mat[i][j];
            }
        }
        index = list.get(0);
        if (list.size() == 2) {
            index1 = list.get(1);
        }
        this.parent = other.parent;
       // this.mat = other.mat;
        this.c = other.c;
        this.f_n = other.f_n;
    }

    public int[][] getMat() {
        return mat;
    }

    public NodeM(NodeM src, Direction direction) {
        this(src);
        new_state(direction);
    }

    public void swap(int i, int j, int k, int c) {
        // [1][1] = 1 ,  [2] [2] =  2
        // value  = 1
        // [1][1] = 2
        // [2][2] = 1;
        int value = this.mat[i][j];
        this.mat[i][j] = this.mat[k][c];
        this.mat[k][c] = value;
    }

    public void setMat(int[][] mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {
       String s ="";
       for (int i = 0; i < N ;i++){
           s += Arrays.toString(this.mat[i]) + "\n";
       }
       return s;
    }
    public HashMap<Integer , Pair<Integer , Integer>> indexes(){
        HashMap<Integer , Pair<Integer , Integer>> indx = new HashMap<>();
        for (int i = 0 ; i < this.mat.length ; i++){
            for (int j = 0 ; j < this.mat[0].length ; j++){
                indx.put(this.mat[i][j], new Pair<>(i ,j));
            }
        }
        return indx;
    }

    public boolean equals(NodeM other) {
        boolean ans = true;
        for (int i = 0; i < mat.length && ans; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (other.mat[i][j] == this.mat[i][j]) {
                    ans = true;
                } else {

                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }

    public color getC() {
        return c;
    }

    public void setC(color c) {
        this.c = c;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public NodeM getParent() {
        return parent;
    }

    public Pair<Integer, Integer> getIndex() {
        return index;
    }

    public int getF_n() {
        return f_n;
    }

    public void setF_n(int f_n) {
        this.f_n = f_n;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public void setParent(NodeM parent) {
        this.parent = parent;
    }

    public void setIndex(Pair<Integer, Integer> index) {
        this.index = index;
    }

    public int getCost(){
        return cost;
    }


    public void new_state(Direction direction) {
        int i = index.getKey();
        int j = index.getValue();
        int k;
        int c;
        if (direction.equals(Direction.Left)) {
            if (j - 1 >= 0) {
                swap(i, j, i, j - 1);
                this.index = new Pair<Integer, Integer>(i ,j -1);
                cost += 4;
                return;
            }else{
                return;
            }
        }
        /// move the blank node to the right
        if (direction.equals(Direction.Right)) {
            if (j + 1 < this.mat[0].length) {
                swap(i, j, i, j + 1);
                this.index = new Pair<Integer, Integer>(i ,j + 1);
                cost += 4;
                return;
            }else{
                return;
            }
        }
        if (direction.equals(Direction.Up)) {
            if (i - 1 >= 0) {
                swap(i, j, i -1, j);
                cost += 4;
                this.index = new Pair<Integer, Integer>(i - 1 ,j);
                return;
            }else{
                return;
            }
        }
        if (direction.equals(Direction.Down)) {
            if (i + 1 < mat.length) {
                swap(i, j, i + 1, j);
                this.index = new Pair<Integer, Integer>(i + 1 ,j);

                cost += 4;
                return;
            }else{
                return;
            }
        }
        if (direction.equals(Direction.ShiftDown)) {
            if (list.size() == 2) {
                k = index1.getKey();
                c = index1.getValue();
                System.out.println(k + "," + c);
                System.out.println(i +"," + j);
                // if this two indexes is close for each
                if (Math.abs(k - i) == 1) {
                    if (i  +  1 < this.mat.length) {
                        cost += 7;
                        swap(i, j, i + 1 , j);
                        swap(k, c, k + 1, c);
                        this.index1 = new Pair<Integer, Integer>(i + 1, j);
                        this.index = new Pair<Integer, Integer>(k + 1, c);
                        cost += 7;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        /**
         * [2 3]              [0 0]
         *           -->>
         * [0 0]              [2 3]
         */
        if (direction.equals(Direction.ShiftUp)) {
            if (list.size() == 2) {
                k = index1.getKey();
                c = index1.getValue();
                // if this two indexes is close for each
                if (Math.abs(k - i) == 1) {
                    if (i - 1 >=  0) {
                        swap(i, j, i - 1 , j);
                        swap(k, c, k -  1 , c);
                        index1 = new Pair<Integer, Integer>(i - 1 , j);
                        index = new Pair<Integer, Integer>(k - 1 , c);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        /** 0 1 2 3                0->2 , 1->3              length = 3
         * [0 0 4 5]              [4 5 0 0]
         *              -->>
         * [2 3 6 7]              [2 3 6 7]
         */
        if (direction.equals(Direction.ShiftRight)) {
            if (list.size() == 2) {
                k = index1.getKey();
                c = index1.getValue();
                // if this two indexes is close for each
                if (Math.abs(k - i) == 1) {
                    //check of each one of them is in range of the array's rows
                    if (j + 2 < this.mat.length && c + 2 < this.mat.length) {
                        swap(i, j, i, j + 2);
                        swap(k, c, k, c + 2);
                        index1 = new Pair<Integer, Integer>(i, j + 2);
                        index = new Pair<Integer, Integer>(k, c + 2);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        /**
         * [0 0 4 5]              [4 5 0 0]
         *              -->>
         * [2 3 6 7]              [2 3 6 7]
         */
        if (direction.equals(Direction.ShiftLeft)) {
            if (list.size() == 2) {
                k = index1.getKey();
                c = index1.getValue();
                // if this two indexes is close for each
                if (Math.abs(k - i) == 1) {
                    //check of each one of them is in range of the array's rows
                    // calculate the worst of j if it's 2 then the -2 = 0 and that's in our range
                    if ( j  - 2 >= 0 &&  k - 2 >= 0) {
                        swap(i, j, i, j - 2);
                        swap(k, c, k, c - 2);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
}
