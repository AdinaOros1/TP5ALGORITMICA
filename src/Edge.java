public class Edge {
    public int dest;
    public int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "(dest= " + dest + ", w= " + weight + ")";
    }
}
