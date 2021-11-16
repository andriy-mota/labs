package lab4;

import utils.Utils;

public class Lab4 {

    public static void main(String[] args) {
        String[] criteria = new String[]{"Price", "Fuel", "Oil", "1-100", "Max speed"};
        String[] cars = new String[]{"Skoda", "VW", "BMW", "Mercedes", "Audi"};
        double[] weight = Utils.readLab4Weights();
        double[][] ranks = Utils.readLab4Ranks();

        printInputDataAsTable(criteria, cars, weight, ranks);
        double[][] multiplicationResult = multiplyWeightOnRank(weight, ranks, cars);
        getMaxForEach(multiplicationResult);
        result(multiplicationResult, cars);
    }

    private static void printInputDataAsTable(String[] criteria,
                                              String[] cars,
                                              double[] weight,
                                              double[][] ranks) {
        for (String s : criteria) {
            System.out.print(String.format("%15s", s));
        }
        System.out.println();
        for (double w : weight) {
            System.out.print(String.format("%15f", w));
        }

        System.out.println("\n");
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                System.out.print(String.format("%15f", ranks[i][j]));
            }
            System.out.println(String.format("\t\t[%s]", cars[i]));
        }

        System.out.println("\n");
    }

    private static double[][] multiplyWeightOnRank(double[] weight, double[][] ranks, String[] cars) {
        System.out.println("\t\tMultiplying ranks with weights, where 'MAX' - max for each criteria:\n");
        double[][] multiplicationResult = new double[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                multiplicationResult[i][j] = weight[j] * ranks[i][j];
                System.out.print(String.format("%15f", multiplicationResult[i][j]));
            }
            System.out.println(String.format("\t\t[%s]", cars[i]));
        }
        return multiplicationResult;
    }

    private static void getMaxForEach(double[][] multiplicationResult) {
        double[] max = new double[]{0, 0, 0, 0, 0};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (max[j] < multiplicationResult[i][j]) {
                    max[j] = multiplicationResult[i][j];
                }
            }
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print(String.format("%15f|", max[i]));
        }

        System.out.println("\n");
    }

    private static void result(double[][] multiplicationResult, String[] cars) {
        System.out.println("Sum for each criteria");
        System.out.println();
        double[] sum = new double[]{0, 0, 0, 0, 0};
        double max = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sum[i] = sum[i] + multiplicationResult[i][j];
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(String.format("%15f|", sum[i]));
        }

        System.out.println();
        for (String s : cars) {
            System.out.print(String.format("%15s|", s));
        }
        for (int i = 0; i < 5; i++) {
            if (max < sum[i]) {
                max = sum[i];
            }
        }
        System.out.println("\n");
        System.out.println("Max is: " + max);
    }


}
