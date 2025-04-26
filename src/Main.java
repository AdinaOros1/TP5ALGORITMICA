import java.util.ArrayList;
import java.util.List;

public class Main {
    static class TestResult {
        double density;
        long dijkstraSequentialTime;
        long dijkstraBinaryHeapTime;
        long floydWarshallTime;

        TestResult(double density, long dijkstraSequentialTime, long dijkstraBinaryHeapTime, long floydWarshallTime) {
            this.density = density;
            this.dijkstraSequentialTime = dijkstraSequentialTime;
            this.dijkstraBinaryHeapTime = dijkstraBinaryHeapTime;
            this.floydWarshallTime = floydWarshallTime;
        }

        public static void main(String[] args) {

            double[] densities = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
            int numVertices = 100;
            int INF = 100000000;

            List<TestResult> results = new ArrayList<>();

            for (double density : densities) {
                //System.out.println("\n--- Testing Graph with Density: " + (int) (density * 100) + "% ---");

                Graph g = new Graph(numVertices, density);
                //g.printGraph();

                long seqTime = testDijkstraSequentialSearch(g);
                long heapTime = testDijkstraBinaryHeap(g);
                long floydTime = testFloydWarshall(g);

                results.add(new TestResult(density, seqTime, heapTime, floydTime));
            }

            System.out.println("\n---All Tests---");
            System.out.printf("%-10s %-25s %-25s %-25s%n", "Density", "Dijkstra Sequential (µs)", "Dijkstra Heap (µs)", "Floyd-Warshall (µs)");
            for (TestResult result : results) {
                System.out.printf("%-10.1f %-25d %-25d %-25d%n",
                        result.density, result.dijkstraSequentialTime, result.dijkstraBinaryHeapTime, result.floydWarshallTime);
            }
        }

        //---------------------------------------------------------
        private static long testDijkstraSequentialSearch(Graph g) {
            long startTime = System.nanoTime();
            DijkstraSequentialSearch dijkstraSequentialSearch = new DijkstraSequentialSearch(g);
            int[] distances2 = dijkstraSequentialSearch.findShortestPaths(0);

            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime);
            System.out.println("Dijkstra algorithm with sequential search time execution: "
                    + executionTime / 1000 + " microseconds");//microseconds

            System.out.println("Below is performing the dijkstra algorithm with sequential search.");

            System.out.println("\nShortest distances from vertex 0:");
            for (int i = 0; i < distances2.length; i++) {
                System.out.println("To vertex " + i + ": " +
                        (distances2[i] == Integer.MAX_VALUE ? "Unreachable" : distances2[i]));

            }
            return executionTime / 1000;
        }


        //----------------------------------------------------------------------------------
        private static long testDijkstraBinaryHeap(Graph g) {
            long startTime2 = System.nanoTime();
            DijkstraBinaryHeap dijkstra = new DijkstraBinaryHeap(g);
            int[] distances = dijkstra.findShortestPaths(0);
            long endTime2 = System.nanoTime();
            long executionTime2 = (endTime2 - startTime2);
            System.out.println("\nDijkstra algorithm with binary heap time execution: "
                    + executionTime2 / 1000 + " microseconds");//microseconds

            System.out.println("Below is performing the dijkstra algorithm with binary heap.");

            System.out.println("\nShortest distances from vertex 0:");
            for (int i = 0; i < distances.length; i++) {
                System.out.println("To vertex " + i + ": " +
                        (distances[i] == Integer.MAX_VALUE ? "Unreachable" : distances[i]));

            }
            return executionTime2 / 1000;
        }

        //-------------------floyd warshall algorithm---------------------------------
        private static long testFloydWarshall(Graph g) {
            int INF = 100000000;
            int[][] matrix = g.asAdjacencyMatrix(INF);
            long startTime = System.nanoTime();
            FloydWarshallAlgorithm.floydWarshall(matrix, INF);
            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime);


            System.out.println("\nFloyd-Warshall shortest path matrix:");
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print((matrix[i][j] == INF ? "INF" : matrix[i][j]) + " ");
                }
                System.out.println();
            }
            return executionTime / 1000;
        }
    }
}
