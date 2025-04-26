import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraSequentialSearch {//O(n^2)
    private Graph graph;

    public DijkstraSequentialSearch(Graph graph) {
        this.graph = graph;
    }

    //it returns a matrix with all the shortest distances from the source node to all the nodes
    public int[] findShortestPaths(int start) {
        int n = graph.size();
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);//O(n)
        distances[start] = 0;

        // F implemented as a list of all nodes
        List<Integer> F = new ArrayList<>();
        for (int i = 0; i < n; i++) F.add(i);

        while (!F.isEmpty()) {
            int u = extractMin(F, distances, visited);//O(n^2)
            if (u == -1) break;

            visited[u] = true;//it marks the unvisited node as visited

            for (Edge edge : graph.getNeighbors(u)) {//O(E)
                int v = edge.dest;
                int newDist = distances[u] + edge.weight;

                if (newDist < distances[v]) {
                    distances[v] = newDist;
                }
            }
        }

        return distances;
    }

    //returns the node that hasn't been visited with the smallest distance from source
    private int extractMin(List<Integer> F, int[] dist, boolean[] visited) {//O(n^2)
        int minDist = Integer.MAX_VALUE;
        int minNode = -1;
        for (int v : F) {
            if (!visited[v] && dist[v] < minDist) {
                minDist = dist[v];
                minNode = v;
            }
        }
        F.remove((Integer) minNode);
        return minNode;
    }
}

