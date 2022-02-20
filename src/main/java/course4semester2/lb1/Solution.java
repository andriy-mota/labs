package course4semester2.lb1;

import utils.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private KruskalAlgImpl algorithm;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.doResolve();
    }

    private void doResolve() {
        List<Edge> edges = Utils.readAdjacencyMatrix("lb1", "l1_2.txt");
        this.algorithm = new KruskalAlgImpl(edges.size());
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        List<Edge> tree = edges.stream().filter(this::kruskalApprove).collect(Collectors.toList());
        System.out.println("Vertices in the tree: " + tree);
        System.out.println("Sum: " + tree.stream().mapToInt(Edge::getWeight).sum());
    }

    private boolean kruskalApprove(Edge edge) {
        return algorithm.union(edge.getStartVertex(), edge.getEndVertex());
    }

}
