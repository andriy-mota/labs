package course4semester2.lb5;


import utils.Utils;

import java.util.stream.IntStream;

public class Solution {

    private int P[];
    private int N;
    private int[][] A;
    private int[][] B;

    public Solution() {
        initData();
    }

    private void initData() {
        A = Utils.readIntMatrixFromSuppliedPath("/course4semester2/lb5/l5_a.txt");
        B = Utils.readIntMatrixFromSuppliedPath("/course4semester2/lb5/l5_b.txt");
        N = A.length;
        P = IntStream.range(0, N).toArray();
    }

    public boolean match() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (A[i][j] != B[P[i]][P[j]]) {
                    return false;
                }
        return true;
    }

    public static void main(String[] args) {
        new Solution().solve();
    }

    private void solve() {
        do {
            if (match()) {
                for (int i = 0; i < N; i++)
                    System.out.print(String.format("{%d: %d}; ", i + 1, P[i] + 1));
                break;
            }
        } while (Utils.findNextPermutation(P));
    }

}