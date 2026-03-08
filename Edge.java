public class Edge implements Comparable<Edge> {
    public int src, dest, weight;
    public String srcName, destName, edgeName; // เพิ่มตัวแปรเก็บ String

    public Edge(int src, int dest, int weight, String srcName, String destName, String edgeName) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.srcName = srcName;
        this.destName = destName;
        this.edgeName = edgeName;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}