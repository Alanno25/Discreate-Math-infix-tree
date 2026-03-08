import java.util.Scanner;

public class main {
    private static Graph graph;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Graph & Minimum Spanning Tree Program ===");

        // บังคับให้ผู้ใช้กรอกข้อมูลกราฟตั้งแต่เริ่มโปรแกรม
        inputGraph();

        // หลังจากสร้างกราฟเสร็จสมบูรณ์ ค่อยเข้าสู่ลูปเมนูหลัก
        while (true) {
            System.out.println("\n--- Minimum Spanning Tree Menu ---");
            System.out.println("1. Show All Possible Spanning Trees");
            System.out.println("2. Find MST using Kruskal's Algorithm");
            System.out.println("3. Find MST using Prim's Algorithm");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // เคลียร์ขึ้นบรรทัดใหม่

            switch (choice) {
                case 1:
                    // ย้ายการแสดง Spanning Tree ทั้งหมดมาไว้ที่เมนู 1
                    SpanningTreeFinder finder = new SpanningTreeFinder();
                    finder.findAll(graph);
                    break;
                case 2:
                    KruskalMST kruskal = new KruskalMST();
                    kruskal.findMST(graph);
                    break;
                case 3:
                    System.out.print("\nEnter starting vertex (e.g., v1): ");
                    String startNode = scanner.nextLine().trim();

                    if (!graph.nameToIndex.containsKey(startNode)) {
                        System.out.println(" Error: Vertex not found!");
                    } else {
                        int startVertex = graph.nameToIndex.get(startNode);
                        PrimMST prim = new PrimMST();
                        prim.findMST(graph, startVertex);
                    }
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void inputGraph() {
        while (true) {
            System.out.println("\n--- Please Initialize the Graph ---");
            System.out.println("--- Step 1: Enter Vertices ---");
            System.out.print("Enter Vertices (e.g., v1,v2,v3,v4): ");
            String vInput = scanner.nextLine();
            String[] vertices = vInput.split(",");

            System.out.println("\n--- Step 2: Enter Number of Edges ---");
            System.out.print("Enter number of Edges: ");
            int E = scanner.nextInt();
            scanner.nextLine();

            Graph tempGraph = new Graph(vertices.length, vertices);

            String[] edgeNames = new String[E];
            String[] srcNames = new String[E];
            String[] destNames = new String[E];

            System.out.println("\n--- Step 3: Enter Edge Connections ---");
            System.out.println("Format: [EdgeName]:[SourceNode],[DestinationNode]");
            System.out.println("Example: e1:v1,v2 (Means edge e1 connects v1 and v2)");

            for (int i = 0; i < E; i++) {
                System.out.print("Enter Edge " + (i + 1) + ": ");
                String edgeStr = scanner.nextLine();

                String[] parts = edgeStr.split(":");
                edgeNames[i] = parts[0].trim();

                String[] nodes = parts[1].split(",");
                srcNames[i] = nodes[0].trim();
                destNames[i] = nodes[1].trim();
            }

            System.out.println("\n--- Step 4: Enter Edge Weights (Distances) ---");
            for (int i = 0; i < E; i++) {
                System.out.print("Enter distance of Edge " + edgeNames[i] + " = : ");
                int weight = scanner.nextInt();
                tempGraph.addEdge(srcNames[i], destNames[i], weight, edgeNames[i]);
            }
            scanner.nextLine();

            // ตรวจสอบความถูกต้องของกราฟ
            if (tempGraph.isConnected()) {
                graph = tempGraph;
                System.out.println("\n Graph is successfully connected and initialized.");
                break; // หลุดออกจากลูปเพื่อไปที่เมนูหลัก
            } else {
                System.out.println("\n Error: The graph is disconnected. Please enter a connected graph.\n");
            }
        }
    }
}