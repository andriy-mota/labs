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


/*
#include <memory.h>
#include <stdio.h>
const int MAX_VERTICES = 40;

int NUM_VERTICES;           // число вершин в графе
const int INFINITY = 10000; // условное число, обозначающее бесконечность

int f[MAX_VERTICES][MAX_VERTICES];  // f[i][j] - поток, текущий от вершины i к j
int c[MAX_VERTICES][MAX_VERTICES];  // c[i][j] - максимальная величина потока, способная течь по ребру (i,j)

// набор вспомогательных переменных, используемых функцией FindPath - обхода в ширину
int Link[MAX_VERTICES];  // Link[i] хранит номер предыдущей вешины на пути i -> исток
int Flow[MAX_VERTICES];  // Flow - значение потока через данную вершину на данном шаге поиска
int Queue[MAX_VERTICES]; // очередь
int QP, QC;              // QP - указатель начала очереди и QC - число эл-тов в очереди

// поиск пути, по которому возможно пустить поток алгоритмом обхода графа в ширину
// функция ищет путь из истока в сток, по которому еще можно пустить поток,
// считая вместимость ребера (i,j) равной c[i][j] - f[i][j]

int FindPath(int source, int target) // source - исток, target - сток
{
    QP = 0; QC = 1; Queue[0] = source;
    Link[target] = -1;
    int i;
    int CurVertex;
    memset(Flow, 0, sizeof(int)*NUM_VERTICES);
    Flow[source] = INFINITY;
    while (Link[target] == -1 && QP < QC)
    {
        CurVertex = Queue[QP];
        for (i=0; i<NUM_VERTICES; i++)
        if ((c[CurVertex][i] - f[CurVertex][i])>0 && Flow[i] == 0)
        {
            Queue[QC] = i; QC++;
            Link[i] = CurVertex;
            if (c[CurVertex][i]-f[CurVertex][i] < Flow[CurVertex])
               Flow[i] = c[CurVertex][i];
            else
               Flow[i] = Flow[CurVertex];
          }
      QP++;

    }

    if (Link[target] == -1) return 0;
    CurVertex = target;
    while (CurVertex != source)
    {
        f[Link[CurVertex]][CurVertex] +=Flow[target];
        CurVertex = Link[CurVertex];
    }
    return Flow[target];
}

// основная функция поиска максимального потока
int MaxFlow(int source, int target) // source - исток, target - сток
{
    memset(f, 0, sizeof(int)*MAX_VERTICES*MAX_VERTICES);
    int MaxFlow = 0;
    int AddFlow;
    do
    {
        AddFlow = FindPath(source, target);
        MaxFlow += AddFlow;
    } while (AddFlow >0);
    return MaxFlow;
}

int main()
{
    int source, target;
    scanf("%d", &NUM_VERTICES);
    scanf("%d %d", &source, &target);
    int i, j;
    for (i=0; i<NUM_VERTICES; i++)
       for (j=0; j<NUM_VERTICES; j++)
         scanf("%d",&c[i][j]);

    printf("%d", MaxFlow(source, target));
    return 0;
}
*/