package course4semester2.lb4;

import utils.Utils;

import java.util.Arrays;

public class MaxFlow {

    private static final int INFINITY = 10000;

    private int f[][];
    private int queueStartPointer, numberOfElementsInQueue;
    private int queue[];
    private int previousVerticeIdex[];
    private int particularVerticeFlow[];

    public int calculateMaxFlow(int source, int target, int[][] c) {
        int maxVertices = c.length;
        f = new int[maxVertices][maxVertices];
        Utils.fillMatrix(f, 0);

        queue = new int[maxVertices];
        previousVerticeIdex = new int[maxVertices];
        particularVerticeFlow = new int[maxVertices];

        int MaxFlow = 0;
        int AddFlow;
        int n = 1;
        do {
            AddFlow = findPath(source, target, c);
            System.out.println("Step #" + n + ": " + AddFlow);
            MaxFlow += AddFlow;
            ++n;
        } while (AddFlow > 0);
        return MaxFlow;
    }

    private int findPath(int source, int target, int[][] c) {

        final int NUM_VERTICES = c.length;

        queueStartPointer = 0;
        numberOfElementsInQueue = 1;
        queue[0] = source;
        previousVerticeIdex[target] = -1;
        int i;
        int currentVertex;
        Arrays.fill(particularVerticeFlow, 0);
        particularVerticeFlow[source] = INFINITY;
        while (previousVerticeIdex[target] == -1 && queueStartPointer < numberOfElementsInQueue) {
            currentVertex = queue[queueStartPointer];
            for (i = 0; i < NUM_VERTICES; i++)
                if ((c[currentVertex][i] - f[currentVertex][i]) > 0 && particularVerticeFlow[i] == 0) {
                    queue[numberOfElementsInQueue] = i;
                    numberOfElementsInQueue++;
                    previousVerticeIdex[i] = currentVertex;
                    if (c[currentVertex][i] - f[currentVertex][i] < particularVerticeFlow[currentVertex])
                        particularVerticeFlow[i] = c[currentVertex][i];
                    else
                        particularVerticeFlow[i] = particularVerticeFlow[currentVertex];
                }
            queueStartPointer++;

        }

        if (previousVerticeIdex[target] == -1) return 0;
        currentVertex = target;
        while (currentVertex != source) {
            f[previousVerticeIdex[currentVertex]][currentVertex] += particularVerticeFlow[target];
            currentVertex = previousVerticeIdex[currentVertex];
        }
        return particularVerticeFlow[target];
    }

}