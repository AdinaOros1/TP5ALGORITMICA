import java.util.*;


public class Graph {
    int n;//number of vertices/nodes
    double density;//density value(it will later help to see the possible number of edges)
    private Map<Integer, List<Edge>> adjacencyList; //map where each node point to a set of its neighbours
    private Random random = new Random();
    private Set<String> edges;



    public Graph(int n, double density) {
        this.n = n;
        this.density = density;
        this.adjacencyList = new HashMap<>();
        this.edges = new HashSet<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }

        int maxEdges = n * (n - 1);
        int possibleEdges = (int) (density* maxEdges);
        while (edges.size() < possibleEdges) {
            int u = random.nextInt(n);
            int v = random.nextInt(n);


            if (u != v) {
                String edge = u + "," + v;
                if (!edges.contains(edge)) {
                    edges.add(edge);
                    int weight = 1 + random.nextInt(10);
                    adjacencyList.get(u).add(new Edge(v, weight));
                }
            }
        }
    }

    public List<Edge> getNeighbors(int v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }
    public int size(){
        return n;
    }


    public void printGraph() {
        for (int i = 0; i < n; i++) {
            System.out.println(i + " -> " + adjacencyList.get(i));
        }
    }
}




