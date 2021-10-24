package lab3;

import utils.Utils;

import java.util.Arrays;

public class Lab3 {

    public static void main(String[] args) {

        Integer[] points = Utils.readLab3PointsData();
        Character[][] comparison = Utils.readLab3ComparisonData();

        Arrays.stream(points).forEach(point -> System.out.print(point + "\t\t"));
        System.out.println("\n");
        Arrays.stream(comparison).forEach(comparisonArray -> {
                    Arrays.stream(comparisonArray).forEach(comparisonidate -> System.out.print(comparisonidate + "\t\t"));
                    System.out.println();
                }
        );

        boardMethod(points, comparison);
        condorseMethod(points, comparison);
    }

    private static void boardMethod(Integer[] points, Character[][] comparison) {

        int sum[] = new int[3];
        int max = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                switch (comparison[i][j]) {
                    case 'A':
                        switch (i) {
                            case 0:
                                sum[0] = sum[0] + 2 * points[j];
                                break;
                            case 1:
                                sum[0] += points[j];
                                break;
                        }
                        break;
                    case 'B':
                        switch (i) {
                            case 0:
                                sum[1] += 2 * points[j];
                                break;
                            case 1:
                                sum[1] += points[j];
                                break;
                        }
                        break;
                    case 'C':
                        switch (i) {
                            case 0:
                                sum[2] += 2 * points[j];
                                break;
                            case 1:
                                sum[2] += points[j];
                                break;
                        }
                        break;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (max < sum[i]) {
                max = sum[i];
            }
        }
        System.out.println("\t\t\tBorda's method");
        System.out.println("\tCandidate A has: " + sum[0] + " points");
        System.out.println("\tCandidate B has: " + sum[1] + " points");
        System.out.println("\tCandidate C has: " + sum[2] + " points");
        System.out.println("Winner is candidate " + getWinnerCandidateName(sum));
    }

    private static String getWinnerCandidateName(int[] sum) {
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < 3; ++i) {
            if (max < sum[i]) {
                max = sum[i];
                maxIndex = i;
            }
        }
        return (char) (65 + maxIndex) + "";
    }

    private static boolean charIsFirst(char charToBeFirst, char charToBeSecond, char c1, char c2, char c3) {
        if (c1 == charToBeFirst && (c2 == charToBeSecond || c3 == charToBeSecond)) {
            return true;
        } else if (c2 == charToBeFirst && c3 == charToBeSecond) {
            return true;
        }
        return false;
    }

    private static void condorseMethod(Integer[] points, Character[][] comparison) {
        int sum[] = {0, 0}, max[] = {0, 0, 0};
        char[] c = new char[3];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 1; j++) {
                if (charIsFirst('A', 'B', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[0] += points[i];
                } else if (charIsFirst('B', 'A', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[1] += points[i];
                }
            }
        }

        int n = findMaxGetIndex(sum, max, 0);
        switch (n) {
            case 0:
                System.out.println("A > B\t A = " + sum[n]);
                c[0] = 'A';
                break;
            case 1:
                System.out.println("B > A\t B = " + sum[n]);
                c[0] = 'B';
                break;
        }
        sum[0] = 0;
        sum[1] = 0;


        System.out.println("\tB > C OR C > B");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 1; j++) {
                if (charIsFirst('B', 'C', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[0] += points[i];
                } else if (charIsFirst('C', 'B', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[1] += points[i];
                }
            }
        }

        n = findMaxGetIndex(sum, max, 1);
        switch (n) {
            case 0:
                System.out.println("B > C\t B = " + sum[n]);
                c[1] = 'B';
                break;
            case 1:
                System.out.println("C > B\t C = " + sum[n]);
                c[1] = 'C';
                break;
        }
        sum[0] = 0;
        sum[1] = 0;

        System.out.println("\tA > C OR C > A");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 1; j++) {
                if (charIsFirst('A', 'C', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[0] += points[i];
                } else if (charIsFirst('C', 'A', comparison[j][i], comparison[j + 1][i], comparison[j + 2][i])) {
                    sum[1] += points[i];
                }
            }
        }

        n = findMaxGetIndex(sum, max, 2);
        switch (n) {
            case 0:
                System.out.println("A > C\t A = " + sum[n]);
                c[2] = 'A';
                break;
            case 1:
                System.out.println("C > A\t C = " + sum[n]);
                c[2] = 'C';
                break;
        }
    }


    private static int findMaxGetIndex(int[] sum, int max[], int pos) {
        int m = -1;
        int maxIndex = -1;
        for (int i = 0; i < sum.length; ++i) {
            if (m < sum[i]) {
                m = sum[i];
                maxIndex = i;
            }
        }
        max[pos] = m;
        return maxIndex;
    }

}
