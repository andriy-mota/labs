package course4semester2.lb4;

import utils.Utils;

public class Solution {

    public static void main(String[] args) {
        MaxFlow maxFlow = new MaxFlow();
        int res = maxFlow.calculateMaxFlow(0, 7, Utils.readIntMatrixFromSuppliedPath("/course4semester2/lb4/l4_2.txt"));
        System.out.println("Result: " + res);
    }

}
