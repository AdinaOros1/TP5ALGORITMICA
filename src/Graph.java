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
        for (int i = 0; i < n; i++) {//O(n)
            adjacencyList.put(i, new ArrayList<>());
        }

        int maxEdges = n * (n - 1);
        int possibleEdges = (int) (density* maxEdges);
        while (edges.size() < possibleEdges) {//O(possibleEdges)
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

    public List<Edge> getNeighbors(int v) {//O(1)
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }
    public int size(){//O(1)
        return n;
    }


    public void printGraph() {//O(n)
        for (int i = 0; i < n; i++) {
            System.out.println(i + " -> " + adjacencyList.get(i));
        }
    }
    public int[][] asAdjacencyMatrix(int INF) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], INF);// O(n) per row → O(n²) total
            matrix[i][i] = 0;// O(1) per row → O(n) total
        }

        for (int u = 0; u < n; u++) {// O(n) iterations
            for (Edge e : adjacencyList.get(u)) { // O(deg(u))=E which is the number of edges; per node
                matrix[u][e.dest] = e.weight;// O(1) per edge
            }
        }

        return matrix;
    }
}




