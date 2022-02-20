package course4semester2.lb2;

import course4semester2.lb1.Edge;
import utils.Utils;

import java.util.List;

public class Solution {

    private FloryAlgImpl floryAlg;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.doResolve();
    }

    private void doResolve() {
        List<Edge> edges = Utils.readAdjacencyMatrix("lb2", "l2_2_loop.txt");
        this.floryAlg = new FloryAlgImpl(8);
        addEdgesToAlgorithm(edges);
        List<Edge> eulerPath = floryAlg.buildEulerTour();
        System.out.println("Euler path consists of: " + eulerPath);
    }

    private void addEdgesToAlgorithm(List<Edge> edges) {
        edges.forEach(e -> floryAlg.addEdge(e.getStartVertex(), e.getEndVertex()));
    }
}
