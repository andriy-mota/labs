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

    public static List<Edge> readAdjacencyMatrix(final String fileName) {
        String[] allLines = getResourceAsString("/course4semester2/lb1/" + fileName).split("\n");
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

    private static String getStringFromLinesByPosition(String[] lines, int row, int column) {
        return lines[row].split(" ")[column];
    }

    private static String getResourceAsString(String fileName) {
        try (InputStream is = Lab2.class.getResourceAsStream(fileName)) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
