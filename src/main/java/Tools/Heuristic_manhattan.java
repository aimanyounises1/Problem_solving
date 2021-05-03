package Tools;

import javafx.util.Pair;
import org.w3c.dom.Node;

/**
 * This is the manhattan function , to compute the h(n) part of f(n) which will evaluate the best next
 * step.
 */
public class Heuristic_manhattan {
    private int cost;

    public Heuristic_manhattan(){

    }
    public Heuristic_manhattan (NodeM father , NodeM successor) {
         cost = 0;
         int value_key,dx,dy,value_v;

        Pair p;
        for (int i = 0 ; i < father.getN() ; i++){
            for (int j = 0 ; j < father.getM() ; j++){
                // retrieve the pair of indexes from successor
                p = successor.indexes().get(father.getMat()[i][j]);
            //    System.out.println( "i = " + i + " j = " + j);
              ///  System.out.println("p key  =" + p.getKey() + " p value = " +p.getValue());
                if ((i != (int)p.getKey() || j !=(int)p.getValue())) {
                    value_key = (int)p.getKey();
                    value_v = (int)p.getValue();
                    dx = (int) Math.pow(i - value_key , 2);
                    dy = (int) Math.pow(j - value_v ,2);
                    cost += (int)Math.sqrt(dx+dy);
              }
            }
        }
    }

    public int getCost() {
        return cost;
    }
}
