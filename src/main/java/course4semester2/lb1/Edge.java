package course4semester2.lb1;

public class Edge {

    public Edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    private Integer startVertex;
    private Integer endVertex;
    private Integer weight;

    public int getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + startVertex + "; " + endVertex + "]: " + weight;
    }
}
