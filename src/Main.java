public class Main {
    public static void main(String[] args) {

        Graph g = new Graph(100, 0.1); // 100 vertices, 10% density
        g.printGraph();

        //----------------------------- graphs for testing
        Graph g2 = new Graph(100, 0.2); // 100 vertices, 20% density
        g2.printGraph();

        Graph g3 = new Graph(100, 0.3); // 100 vertices, 30% density
        g3.printGraph();

        Graph g4 = new Graph(100, 0.4); // 100 vertices, 40% density
        g4.printGraph();

        Graph g5 = new Graph(100, 0.5); // 100 vertices, 50% density
        g5.printGraph();

        Graph g6 = new Graph(100, 0.6); // 100 vertices, 60% density
        g6.printGraph();

        Graph g7 = new Graph(100, 0.7); // 100 vertices, 70% density
        g7.printGraph();

        Graph g8 = new Graph(100, 0.8); // 100 vertices, 80% density
        g8.printGraph();

        Graph g9 = new Graph(100, 0.9); // 100 vertices, 90% density
        g9.printGraph();

        Graph g10 = new Graph(100, 1); // 100 vertices, 100% density
        g10.printGraph();
        //---------------------------------------------------------

        long startTime = System.nanoTime();
        DijkstraSequentialSearch dijkstraSequentialSearch = new DijkstraSequentialSearch(g);
        int[] distances2 = dijkstraSequentialSearch.findShortestPaths(0);

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime);
        System.out.println("Dijkstra algorithm with sequential search time execution: "
                + executionTime/1000 + " microseconds");//microseconds

        System.out.println("Below is performing the dijkstra algorithm with sequential search.");

        System.out.println("\nShortest distances from vertex 0:");
        for (int i = 0; i < distances2.length; i++) {
            System.out.println("To vertex " + i + ": " +
                    (distances2[i] == Integer.MAX_VALUE ? "Unreachable" : distances2[i]));

        }

        //----------------------------------------------------------------------------------
        long startTime2 = System.nanoTime();
        DijkstraBinaryHeap dijkstra = new DijkstraBinaryHeap(g);
        int[] distances = dijkstra.findShortestPaths(0);
        long endTime2 = System.nanoTime();
        long executionTime2 = (endTime2 - startTime2);
        System.out.println("\nDijkstra algorithm with binary heap time execution: "
                + executionTime2/1000 + " microseconds");//microseconds

        System.out.println("Below is performing the dijkstra algorithm with binary heap.");

        System.out.println("\nShortest distances from vertex 0:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To vertex " + i + ": " +
                    (distances[i] == Integer.MAX_VALUE ? "Unreachable" : distances[i]));

        }
        //-------------------floyd warshall algorithm---------------------------------
        int INF = 100000000;
        int[][] matrix = g.asAdjacencyMatrix(INF);
        FloydWarshallAlgorithm.floydWarshall(matrix, INF);


        System.out.println("\nFloyd-Warshall shortest path matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print((matrix[i][j] == INF ? "INF" : matrix[i][j]) + " ");
            }
            System.out.println();
        }








        /*int[][] dist = {
                { 0, 4, INF, 5, INF },
                { INF, 0, 1, INF, 6 },
                { 2, INF, 0, 3, INF },
                { INF, INF, 1, 0, 2 },
                { 1, INF, INF, 4, 0 }
        };

        FloydWarshallAlgorithm.floydWarshall(dist);

        System.out.println("\nFloyd-Warshall shortest path matrix:");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + " ");
            }
            System.out.println();
        }*/

    }
}