package course4semester2.lb3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSP {

    private int final_res = Integer.MAX_VALUE;

    List<Integer> finalPath;
    private int N;

    public TSP(int adjacencyMatrixSize) {
        N = adjacencyMatrixSize;
        finalPath = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrixSize + 1; ++i) {
            finalPath.add(-1);
        }
    }

    public void tsp(int[][] adjacencyMatrix) {
        double currentBound = 0;
        int[] currentPath = new int[adjacencyMatrix.length];
        Arrays.fill(currentPath, 0);

        boolean[] visited = new boolean[adjacencyMatrix.length];
        Arrays.fill(visited, false);

        for (int i = 0; i < adjacencyMatrix.length; ++i) {
            currentBound += firstMin(adjacencyMatrix, i) + secondMin(adjacencyMatrix, i);
        }

        currentBound = Math.ceil(currentBound / 2.0);

        visited[0] = true;
        currentPath[0] = 0;

        TSPRec(adjacencyMatrix, currentBound, 0, 1, currentPath, visited);

        System.out.println("Minimum cost:" + final_res);
        System.out.print("Path taken: ");
        for (int i = 0; i < N + 1; ++i) {
            System.out.print(finalPath.get(i) + " ");
        }
    }

    void TSPRec(int[][] adj, double currentBound, int currentWeight, int level, int[] currentPath, boolean[] visited) {

        if (level == adj.length && adj[currentPath[level - 1]][currentPath[0]] != 0) {
            int currentRes = currentWeight + adj[currentPath[level - 1]][currentPath[0]];
            if (currentRes < final_res) {
                copyToFinal(currentPath);
                final_res = currentRes;
            }
            return;
        }

        for (int i = 0; i < adj.length; ++i) {
            if (adj[currentPath[level - 1]][i] != 0
                    && !visited[i]) {
                double tmp = currentBound;
                currentWeight += adj[currentPath[level - 1]][i];

                if (level == 1) {
                    currentBound -= ((firstMin(adj, currentPath[level - 1]) + firstMin(adj, i)) / 2.0);
                } else {
                    currentBound -= ((secondMin(adj, currentPath[level - 1]) + firstMin(adj, i)) / 2.0);
                }

                if (currentBound + currentWeight < final_res) {
                    currentPath[level] = i;
                    visited[i] = true;
                    TSPRec(adj, currentBound, currentWeight, level + 1, currentPath, visited);
                }
                currentWeight -= adj[currentPath[level - 1]][i];
                currentBound = tmp;

                Arrays.fill(visited, false);

                for (int j = 0; j < level; ++j) {
                    if (currentPath[j] != -1) {
                        visited[currentPath[j]] = true;
                    }
                }
            }
        }
    }

    void copyToFinal(int[] currPath) {
        List<Integer> currentPath = new ArrayList<>();
        for (int i = 0; i < currPath.length; ++i) {
            currentPath.add(currPath[i]);
        }
        finalPath.addAll(currentPath);
        finalPath.add(N, currPath[0]);
    }

    private int firstMin(int[][] adjacencyMatrix, int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < adjacencyMatrix.length; ++k) {
            if (adjacencyMatrix[i][k] < min && i != k) {
                min = adjacencyMatrix[i][k];
            }
        }
        return min;
    }

    private int secondMin(int[][] adjacencyMatrix, int i) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int j = 0; j < adjacencyMatrix.length; ++j) {
            if (i == j) {
                continue;
            }
            if (adjacencyMatrix[i][j] <= first) {
                second = first;
                first = adjacencyMatrix[i][j];
            }
        }
        return second;
    }
}
