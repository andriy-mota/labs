package utils;

import cource4semester1.lab2.Lab2;
import course4semester2.lb1.Edge;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    @Deprecated
    public static Integer[][] readLab1Data() {
        Integer[][] res = new Integer[3][3];
        String[] inputData = getResourceAsString("../lb_1_input.txt").replaceAll("\n", "").split(";");
        int z = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                res[i][j] = Integer.parseInt(inputData[z++]);
            }
        }
        return res;
    }

    @Deprecated
    public static Double[] readLab2Data() {
        Double[] res = new Double[14];
        String[] inputData = getResourceAsString("../lb_2_input.txt").split(";");
        int z = 0;
        for (int i = 0; i < 14; ++i) {
            res[i] = Double.parseDouble(inputData[z++]);
        }
        return res;
    }

    @Deprecated
    public static Integer[] readLab3PointsData() {
        Integer[] points = new Integer[5];
        String[] inputData = getResourceAsString("../lb_3_points.txt").split(";");
        for (int i = 0; i < 5; ++i) {
            points[i] = Integer.parseInt(inputData[i]);
        }
        return points;
    }

    @Deprecated
    public static Character[][] readLab3ComparisonData() {
        Character[][] comparison = new Character[3][5];
        String[] inputData = getResourceAsString("../lb_3_comparison.txt").split(";");

        int z = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 5; ++j) {
                comparison[i][j] = inputData[z++].toCharArray()[0];
            }
        }
        return comparison;
    }

    public static double[] readLab4Weights() {
        String[] inputData = getResourceAsString("../lb_4_weight.txt").split(";");
        double[] weight = new double[inputData.length];
        for (int i = 0; i < inputData.length; ++i) {
            weight[i] = Double.parseDouble(inputData[i]);
        }
        return weight;
    }

    @Deprecated
    public static double[][] readLab4Ranks() {
        String[] lines = getResourceAsString("../lb_4_ranks.txt").split("\n");
        double[][] ranks = new double[5][5];
        for (int i = 0; i < 5; ++i) {
            String[] inputData = lines[i].split(";");
            for (int j = 0; j < 5; ++j) {
                ranks[i][j] = Double.parseDouble(inputData[j]);
            }
        }
        return ranks;
    }

    @Deprecated
    public static int[][] readLab5Data() {
        String[] lines = getResourceAsString("../lb_4_ranks.txt").split("\n");
        int[][] price = new int[5][5];
        for (int i = 0; i < 5; ++i) {
            String[] inputData = lines[i].split(";");
            for (int j = 0; j < 5; ++j) {
                price[i][j] = Integer.parseInt(inputData[j]);
            }
        }
        return price;
    }

    public static List<Edge> readAdjacencyMatrix(String lbName, String fileName) {
        String[] allLines = getResourceAsString("/course4semester2/" + lbName + "/" + fileName).split("\n");
        int verticesCount = Integer.parseInt(allLines[0]);
        String[] edgesLines = new String[verticesCount];
        System.arraycopy(allLines, 1, edgesLines, 0, verticesCount);
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < verticesCount; ++i) {
            for (int j = i; j < verticesCount; ++j) {
                int weight = Integer.parseInt(getStringFromLinesByPosition(edgesLines, i, j));
                if (weight != 0) {
                    edges.add(new Edge(i, j, weight));
                }
            }
        }
        return edges;
    }

    public static void fillMatrix(int[][] matrix, int number) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = number;
            }
        }
    }

    public static int[][] readIntMatrixFromSuppliedPath(String path) {
        String[] allLines = getResourceAsString(path).split("\n");
        int numberOfVertices = Integer.parseInt(allLines[0]);
        String[] matrixLines = new String[allLines.length - 1];
        System.arraycopy(allLines, 1, matrixLines, 0, matrixLines.length);
        int[][] matrix = new int[numberOfVertices][numberOfVertices];
        for (int i = 0; i < numberOfVertices; ++i) {
            String[] inputData = matrixLines[i].split(" ");
            for (int j = 0; j < numberOfVertices; ++j) {
                matrix[i][j] = Integer.parseInt(inputData[j]);
            }
        }
        return matrix;
    }

    private static String getStringFromLinesByPosition(String[] lines, int row, int column) {
        return lines[row].split(" ")[column];
    }

    public static int[] swap(int data[], int left, int right) {

        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;

        return data;
    }

    public static int[] reverse(int data[], int left, int right) {

        while (left < right) {
            int temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }

        return data;
    }

    public static boolean findNextPermutation(int data[]) {
        if (data.length <= 1)
            return false;

        int last = data.length - 2;

        while (last >= 0) {
            if (data[last] < data[last + 1]) {
                break;
            }
            last--;
        }

        if (last < 0)
            return false;

        int nextGreater = data.length - 1;

        for (int i = data.length - 1; i > last; i--) {
            if (data[i] > data[last]) {
                nextGreater = i;
                break;
            }
        }

        data = swap(data, nextGreater, last);
        data = reverse(data, last + 1, data.length - 1);
        return true;
    }


    private static String getResourceAsString(String fileName) {
        try (InputStream is = Lab2.class.getResourceAsStream(fileName)) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
