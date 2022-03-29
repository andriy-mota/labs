package course4semester2.lb4;

import org.apache.commons.math3.analysis.function.Max;
import utils.Utils;

public class Solution {

    private static int[][] c = {
{0, 20, 20, 40, 0, 0, 0, 0},
    {0, 0, 10, 0, 10, 0, 0, 0},
    {0, 0, 0, 20, 20, 0, 0, 0},
    {0, 0, 0, 0, 0, 20, 20, 0},
    {0, 0, 0, 0, 0, 0, 0, 30},
    {0, 0, 10, 0, 20, 0, 0, 20},
    {0, 0, 0, 0, 0, 10, 0, 20},
    {0, 0, 0, 0, 0, 0, 0, 0}
    };


    public static void main(String[] args) {
        MaxFlow maxFlow = new MaxFlow();
        int res = maxFlow.calculateMaxFlow( 0, 7, Utils.readLab4Matrix());
        System.out.println("Result: " + res);
    }

}
