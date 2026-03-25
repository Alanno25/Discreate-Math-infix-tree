import java.util.*;
public class Graph {
    public int V;
    public List<Edge> edges;
    public List<List<Edge>> adj;
    public Map<String, Integer> nameToIndex;
    public String[] indexToName;

    public Graph(int V, String[] vertices) {
        this.V = V;
        edges = new ArrayList<>();
        adj = new ArrayList<>(V);
        nameToIndex = new HashMap<>();
        indexToName = new String[V];

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            String vName = vertices[i].trim();
            nameToIndex.put(vName, i);
            indexToName[i] = vName;
        }
    }

    public void addEdge(String srcName, String destName, int weight, String edgeName) {
        int src = nameToIndex.get(srcName);
        int dest = nameToIndex.get(destName);
        
        Edge edge = new Edge(src, dest, weight, srcName, destName, edgeName);
        edges.add(edge);
        adj.get(src).add(new Edge(src, dest, weight, srcName, destName, edgeName));
        adj.get(dest).add(new Edge(dest, src, weight, destName, srcName, edgeName));
    }
    public boolean isConnected() {
        if (V == 0) return true;
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge e : adj.get(u)) {
                if (!visited[e.dest]) {
                    visited[e.dest] = true;
                    queue.add(e.dest);
                    count++;
                }
            }
        }
        return count == V;
    }
    public boolean isSpanningTree() {
        return isConnected() && edges.size() == V - 1;
    }
}