import java.util.*;

public class KruskalMST {
    public void findMST(Graph graph) {
        System.out.println("\n--- Kruskal's Algorithm Steps ---");
        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);
        
        System.out.println("1. Sort edges by weight in ascending order:");
        for (Edge e : edges) {
            System.out.println("   Edge(" + e.src + "-" + e.dest + ") Weight: " + e.weight);
        }

        DisjointSet ds = new DisjointSet(graph.V);
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        System.out.println("\n2. Process edges one by one:");
        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            if (rootSrc != rootDest) {
                System.out.println(" Select Edge(" + edge.src + "-" + edge.dest + ") Weight: " + edge.weight + " -> Add to MST");
                mst.add(edge);
                totalWeight += edge.weight;
                ds.union(rootSrc, rootDest);
            } else {
                System.out.println(" Skip Edge(" + edge.src + "-" + edge.dest + ") Weight: " + edge.weight + " -> Cycle detected");
            }
        }

        System.out.println("\n--- Minimum Spanning Tree Result (Kruskal) ---");
        for (Edge e : mst) {
            System.out.println("Edge: " + e.src + " - " + e.dest + " | Weight: " + e.weight);
        }
        System.out.println("Total Minimum Weight = " + totalWeight);
    }
}