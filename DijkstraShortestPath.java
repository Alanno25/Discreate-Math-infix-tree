import java.util.*;

public class DijkstraShortestPath {
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public void findPath(Graph graph, int startVertex, int endVertex) {
        int V = graph.V;
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[startVertex] = 0;
        pq.add(new Node(startVertex, 0));

        System.out.println("\n--- Dijkstra's Algorithm Steps ---");
        System.out.println(
                "Start Node: " + graph.indexToName[startVertex] + " | End Node: " + graph.indexToName[endVertex]);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            if (u == endVertex)
                break;
            if (current.distance > dist[u])
                continue;
            for (Edge edge : graph.adj.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
                    System.out.println("  Relaxing: " + graph.indexToName[u] + " -> " + graph.indexToName[v]
                            + " (New shortest distance to " + graph.indexToName[v] + " = " + dist[v] + ")");
                }
            }
        }
        System.out.println("\n--- Shortest Path Result (Dijkstra) ---");
        if (dist[endVertex] == Integer.MAX_VALUE) {
            System.out.println("No path exists between " + graph.indexToName[startVertex] + " and "
                    + graph.indexToName[endVertex]);
        } else {
            List<String> path = new ArrayList<>();
            int curr = endVertex;
            while (curr != -1) {
                path.add(graph.indexToName[curr]);
                curr = parent[curr];
            }
            Collections.reverse(path);

            System.out.println("Path: " + String.join(" -> ", path));
            System.out.println("Total Minimum Distance = " + dist[endVertex]);
        }
    }
}