import java.util.*;

public class SpanningTreeFinder {
    
    public void findAll(Graph graph) {
        System.out.println("\n--- All Possible Spanning Trees ---");
        List<List<Edge>> allSpanningTrees = new ArrayList<>();
        List<Edge> currentCombination = new ArrayList<>();
        
        int targetEdges = graph.V - 1;
        
        findCombinations(graph.edges, targetEdges, 0, currentCombination, allSpanningTrees, graph.V);
        
        if (allSpanningTrees.isEmpty()) {
            System.out.println(" No Spanning Tree found in this graph (The graph might be disconnected).");
        } else {
            System.out.println(" Found a total of " + allSpanningTrees.size() + " Spanning Trees:");
            for (int i = 0; i < allSpanningTrees.size(); i++) {
                System.out.println("\nTree " + (i + 1) + ":");
                int totalWeight = 0;
                for (Edge e : allSpanningTrees.get(i)) {
                    System.out.println("  - Edge " + e.edgeName + " (" + e.srcName + "-" + e.destName + ") | Weight: " + e.weight);
                    totalWeight += e.weight;
                }
                System.out.println("  >> Total Weight = " + totalWeight);
            }
        }
    }

    private void findCombinations(List<Edge> edges, int k, int start, List<Edge> current, List<List<Edge>> result, int V) {
        if (current.size() == k) {
            if (isValidSpanningTree(current, V)) {
                result.add(new ArrayList<>(current)); 
            }
            return;
        }
        
        for (int i = start; i < edges.size(); i++) {
            current.add(edges.get(i));
            findCombinations(edges, k, i + 1, current, result, V);
            current.remove(current.size() - 1); 
        }
    }

    private boolean isValidSpanningTree(List<Edge> edges, int V) {
        DisjointSet ds = new DisjointSet(V);
        for (Edge e : edges) {
            int rootSrc = ds.find(e.src);
            int rootDest = ds.find(e.dest);
            
            if (rootSrc == rootDest) {
                return false; 
            }
            ds.union(rootSrc, rootDest);
        }
        return true; 
    }
}