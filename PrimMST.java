import java.util.*;

public class PrimMST {
    public void findMST(Graph graph, int startVertex) {
        if (startVertex < 0 || startVertex >= graph.V) {
            System.out.println(" Invalid starting vertex.");
            return;
        }

        System.out.println("\n--- Prim's Algorithm Steps ---");
        boolean[] inMST = new boolean[graph.V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        inMST[startVertex] = true;
        System.out.println("1. Start at Vertex " + startVertex + " and add to MST");
        for (Edge e : graph.adj.get(startVertex)) {
            pq.add(e);
        }

        int step = 2;
        while (!pq.isEmpty() && mst.size() < graph.V - 1) {
            Edge minEdge = pq.poll();

            if (inMST[minEdge.dest]) {
                System.out.println(step + ". Pick Edge(" + minEdge.src + "-" + minEdge.dest + ") Weight: " + minEdge.weight + " -> Skip (Cycle detected)");
                step++;
                continue;
            }

            System.out.println(step + ". Pick Edge(" + minEdge.src + "-" + minEdge.dest + ") Weight: " + minEdge.weight + " ->  Add to MST");
            inMST[minEdge.dest] = true;
            mst.add(minEdge);
            totalWeight += minEdge.weight;

            for (Edge e : graph.adj.get(minEdge.dest)) {
                if (!inMST[e.dest]) {
                    pq.add(e);
                }
            }
            step++;
        }

        System.out.println("\n--- Minimum Spanning Tree Result (Prim) ---");
        for (Edge e : mst) {
            System.out.println("Edge: " + e.src + " - " + e.dest + " | Weight: " + e.weight);
        }
        System.out.println("Total Minimum Weight = " + totalWeight);
    }
}