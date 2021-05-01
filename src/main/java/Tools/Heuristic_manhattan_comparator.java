package Tools;

import java.util.Comparator;

public class Heuristic_manhattan_comparator implements Comparator<NodeM> {
    @Override
    public int compare(NodeM o1, NodeM o2) {
        if (o1.getF_n() > o2.getF_n()) { // this comparator will order from the smallest to the largest.
            return 1;
        }
        if (o1.getF_n() < o2.getF_n()) {
            return -1;
        }
        return 0;
    }
}
