package lab1;

import utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab1 {

    public static void main(String[] args) {
        Integer[][] data = Utils.readLab1Data();
        valda(data);
        max(data);
        gurvica(data);
        laplasa(data);
        bayesaLaplasa(data);

    }

    public static void valda(Integer[][] data) {
        List<Integer> minList = Arrays.stream(data)
                .map(array -> Arrays.stream(array)
                        .mapToInt(i -> i)
                        .min().getAsInt())
                .collect(Collectors.toList());

        Integer result = minList.stream().max(Comparator.comparingInt(i -> i)).get();

        printResultAndBestResult(minList.toArray(new Integer[0]), result, "Valda method: ");
    }

    public static void max(Integer[][] data) {
        List<Integer> maxList = Arrays.stream(data)
                .map(array -> Arrays.stream(array)
                        .mapToInt(i -> i)
                        .max().getAsInt())
                .collect(Collectors.toList());
        Integer result = maxList.stream().max(Comparator.comparingInt(i -> i)).get();

        printResultAndBestResult(maxList.toArray(new Integer[0]), result, "Valda method: ");
    }

    public static void gurvica(Integer[][] data) {
        int[] min = new int[3];
        int[] max_a = new int[3];
        double[] gur = new double[3];
        double max = 0;
        double cof = 0.6;

        for (int i = 0; i < 3; i++) {
            min[i] = data[i][0];
            max_a[i] = data[i][0];
            for (int j = 0; j < 3; j++) {
                if (min[i] >= data[i][j]) {
                    min[i] = data[i][j];
                }
                if (max_a[i] <= data[i][j]) {
                    max_a[i] = data[i][j];
                }
            }
            gur[i] = cof * min[i] + (1 - cof) * max_a[i];
        }

        printResultAndBestResult(gur, max, "Gurvic method:");
    }

    public static void laplasa(Integer[][] x) {
        double[] lap = new double[3];
        double max = 0;

        for (int i = 0; i < 3; i++) {
            lap[i] = 0;
            for (int j = 0; j < 3; j++) {
                lap[i] += x[i][j];
            }
            lap[i] /= 3;
        }
        printResultAndBestResult(lap, max, "Laplasa method:");
    }

    public static void bayesaLaplasa(Integer[][] data) {
        double p1 = 0.55, p2 = 0.3, p3 = 0.15;
        double[] probability = {0.55, 0.3, 0.15};
        double[] bl = new double[3];
        double max = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bl[i] += (data[i][j] * probability[i]);
            }
        }
        printResultAndBestResult(bl, max, "Bayesa_Laplasa method:");
    }

    private static void printResultAndBestResult(Integer[] bl, Integer max, String message) {
        System.out.println(message);
        for (int i = 0; i < 3; i++) {
            System.out.println("\t" + bl[i]);
        }
        for (int i = 0; i < 3; i++) {
            if (max < bl[i])
                max = bl[i];
        }
        System.out.println("Best solution is: ");
        System.out.println(max);
    }

    private static void printResultAndBestResult(double[] bl, double max, String message) {
        System.out.println(message);
        for (int i = 0; i < 3; i++) {
            System.out.println("\t" + BigDecimal.valueOf(bl[i]).setScale(2, RoundingMode.HALF_UP));
        }
        for (int i = 0; i < 3; i++) {
            if (max < bl[i])
                max = bl[i];
        }
        System.out.println("Best solution is: ");
        System.out.println(BigDecimal.valueOf(max).setScale(2, RoundingMode.HALF_UP));
    }
}