package course4semester2.lb2;

import course4semester2.lb1.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloryAlgImpl {

    private int vertices;
    private ArrayList<Integer>[] adj;
    private List<Edge> eulerPath = new ArrayList<>();

    FloryAlgImpl(int numOfVertices) {
        this.vertices = numOfVertices;

        initGraph();
    }

    private void initGraph() {
        adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(Integer u, Integer v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    private void removeEdge(Integer u, Integer v) {
        adj[u].remove(v);
        adj[v].remove(u);
    }

    public List<Edge> buildEulerTour() {
        if (noEulerPath()) {
            return Collections.emptyList();
        }
        Integer startVertex = pickStartVertex(0);
        return printEulerUtil(startVertex);
    }

    private boolean noEulerPath() {
        Integer oddVerticesCount = 0;
        for (int i = 0; i < vertices; i++) {
            if (adj[i].size() % 2 == 1) {
                oddVerticesCount += 1;
            }
        }
        return oddVerticesCount > 2;
    }

    private Integer pickStartVertex(Integer u) {
        for (int i = 0; i < vertices; i++) {
            if (adj[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }
        return u;
    }

    private List<Edge> printEulerUtil(Integer u) {
        for (int i = 0; i < adj[u].size(); i++) {
            Integer v = adj[u].get(i);
            if (isBridge(u, v)) {
                eulerPath.add(new Edge(u, v));
                removeEdge(u, v);
                printEulerUtil(v);
            }
        }
        return eulerPath;
    }

    private boolean isBridge(Integer u, Integer v) {

        if (adj[u].size() == 1) {
            return true;
        }

        boolean[] isVisited = new boolean[this.vertices];
        int count1 = dfsCount(u, isVisited);

        removeEdge(u, v);
        isVisited = new boolean[this.vertices];
        int count2 = dfsCount(u, isVisited);

        addEdge(u, v);
        return (count1 > count2) ? false : true;
    }

    private int dfsCount(Integer v, boolean[] isVisited) {
        isVisited[v] = true;
        int count = 1;
        for (int adj : adj[v]) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }

}
