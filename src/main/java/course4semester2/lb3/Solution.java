package course4semester2.lb3;

import utils.Utils;

public class Solution {

    public static void main(String[] args) {
        int[][] matrix = Utils.readIntMatrixFromSuppliedPath("/course4semester2/lb3/l3.txt");
        new TSP(matrix.length).tsp(matrix);
    }

}
