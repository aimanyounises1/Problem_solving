package Init_game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Init_file {
    private int n;
    private int m;
    private String algo_name;
    private boolean time;
    private boolean with_open;
    private int [][] mat_start;
    private int [][] mat_goal;
    private HashSet<String> algo_list;
    /**
     * BFS
     * with time
     * no open
     * 3x4
     * 1,2,3,4
     * 5,6,11,7
     * 9,10,8,_
     * Goal state:
     * 1,2,3,4
     * 5,6,7,8
     * 9,10,11,_
     * @param
     */
    public Init_file(){
       algo_list = new HashSet<>();
       algo_list.add("BFS");
       algo_list.add("DFID");
       algo_list.add("A*");
       algo_list.add("IDA*");
       algo_list.add("DFBnB");
    }
    private void read_from_file(String file) throws IOException{
            BufferedReader br = new BufferedReader(new FileReader(file));
            // read the first line and get the desired algorithm
            String line = br.readLine();
            if (!algo_list.contains(line)){
                throw new IOException("Sorry buddy we can't find your algo");
            }
            if (line.equals("BFS")){
                algo_name = line;
            }else if(line.equals("DFID")){
                algo_name = line;
            }else if(line.equals("A*")){
                algo_name = line;
            }else if(line.equals("IDA*")){
                algo_name = line;
            }else{
                algo_name = line;
            }
            while((line = br.readLine())!= null){
                if (line.contains("time")){
                    if (line.contains("no time")){
                        time = false;
                    }else{
                    time = true;}

                }else if(line.contains("no open")){
                    with_open = false;
                }else if(line.contains("x")) {
                    mat_size(line);
                    int k = this.n;
                    while (k > 0) {
                        line = br.readLine();
                        // split the array into numbers and then convert them
                        String[] splitter = line.split(",");
                        // let's check if that is true , now fixed
                        for (int i = 0; i < this.m; i++) {
                            if (splitter[i].equals("_")) {
                                this.mat_start[k][i] = 0;
                            }
                            this.mat_start[k][i] = Integer.valueOf(splitter[i]);
                        }
                        k--;
                    }
                }else {
                    int j = this.n;
                    while (j > 0){
                        line = br.readLine();
                        String[] splitter = line.split(",");
                        // let's check if that is true , now fixed
                        for (int i = 0; i < this.m; i++) {
                            if (splitter[i].equals("_")) {
                                this.mat_start[j][i] = 0;
                            }
                            this.mat_start[j][i] = Integer.valueOf(splitter[i]);
                        }
                        j--;
                    }
                    break;
                }
            }

    }
    private void mat_size(String matSize) {
        String[] cut_x = matSize.split("x");
        this.m = Integer.valueOf(cut_x[1]);
        this.n = Integer.valueOf(cut_x[0]);
        this.mat_start = new int[n][m];
        this.mat_goal = new int[n][m];
    }
}
