package Tools;

import javafx.util.Pair;
import org.w3c.dom.Node;

/**
 * This is the manhattan function , to compute the h(n) part of f(n) which will evaluate the best next
 * step.
 */
public class Heuristic_manhattan {

    public Heuristic_manhattan(){

    }

    public Heuristic_manhattan (NodeM father , NodeM successor) {
        int cost = 0,value_key,dx,dy,value_v;
        Pair p;
        for (int i = 0 ;i < father.getN() ; i++){
            for (int j =0 ; j < father.getM() ; j++){
                // retrieve the pair of indexes
                p = father.indexes().get(father.getMat()[i][j]);
                if (father.getMat()[i][j] != 0 && i != (int)p.getKey() && j !=(int)p.getValue()) {
                    value_key = (int)p.getKey();
                    value_v = (int)p.getValue();
                    dx = (int) Math.pow(i - value_key , 2);
                    dy = (int) Math.pow(j - value_v ,2);
                    cost += (int)Math.sqrt(dx+dy);
              }
            }
        }
    }

}
