package Tools;

import Algorithms.Direction;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.prefs.NodeChangeEvent;

/**
 * Colors to know if node is visited or not
 */


public class NodeM {
    int mat[][];
    private color c;
    private NodeM parent;
    private Pair <Integer, Integer> index;
    private int ID;
    public NodeM(int [][]mat){
        for (int i  = 0 ; i < mat.length;i++){
            for (int j = 0 ; j< mat[0].length; j++){
                if (mat[i][j] == 0)
                    index = new Pair<Integer, Integer>(i,j);
                    this.mat[i][j] = mat[i][j];
            }
        }
        c = color.White;
        this.mat = mat;
    }
    public NodeM(NodeM other){
        for (int  i = 0;i<this.mat.length;i++){
            this.mat[i] = other.mat[i].clone();
        }
        this.mat = other.mat;
        this.c = other.c;
    }
    public int[][] getMat() {
        return mat;
    }
    public NodeM(NodeM src , Direction direction){
        this(src);
        int i,j;
        boolean ans = (index.getKey() < mat.length - 1) && (index.getValue() < mat[0].length - 1)
                && (index.getKey() > 0) && (index.getValue() > 0);
       if (direction.equals(Direction.Left) && ans){
            i = index.getKey();
            j = index.getValue();
           swap(i,j,i-1,j);
       }
    }
    public void swap(int i , int j , int k ,int c){
        int value = mat[i][j];
        mat[i][j] = mat[k][c];
        mat[k][c] = value;
    }

    public void setMat(int[][] mat) {
        this.mat = mat;
    }

    @Override
    public String toString() {
        return "NodeM{" +
                "mat=" + Arrays.toString(mat) +
                '}';
    }
    public boolean equals(int mat [][] ){
        boolean ans = false;
        for (int i  = 0  ; i < mat.length && ans; i++){
            for (int j  = 0 ; j <mat[0].length; j++){
                if (mat[i][j] ==this.mat[i][j]) {
                    ans = true;
                }else{
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

    public void setParent(NodeM parent) {
        this.parent = parent;
    }

    public void setIndex(Pair<Integer, Integer> index) {
        this.index = index;
    }
}
