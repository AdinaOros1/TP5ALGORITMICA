import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraBinaryHeap {//O((n+E)*log n)
    private Graph graph;

    public DijkstraBinaryHeap(Graph graph) {
        this.graph = graph;
    }

    public int[] findShortestPaths(int start) {
        int n = graph.size();
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        // Initialize distances
        Arrays.fill(distances, Integer.MAX_VALUE);//O(n)
        distances[start] = 0;

        // Priority queue to get the next minimum distance node
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));//O(log n)

        while (!pq.isEmpty()) {
            Node current = pq.poll();//O(log n)
            int u = current.id;

            if (visited[u]) continue;
            visited[u] = true;

            // Check all neighbors
            for (Edge edge : graph.getNeighbors(u)) {int v = edge.dest;
                int newDist = distances[u] + edge.weight;

                if (newDist < distances[v]) {
                    distances[v] = newDist;
                    pq.add(new Node(v, newDist));//O(E*log n)
                }
            }
        }

        return distances;
    }

    // Helper class for priority queue
    private static class Node implements Comparable<Node> {//O(1)
        int id;
        int dist;

        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}

