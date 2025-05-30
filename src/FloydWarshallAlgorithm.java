public class FloydWarshallAlgorithm {//total time complexity O(V^3)
    static void floydWarshall(int[][] dist, int INF) {
        int V = dist.length;
        //int INF = 100000000;

        for (int k = 0; k < V; k++) {

            for (int i = 0; i < V; i++) {

                for (int j = 0; j < V; j++) {

                    if (dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
