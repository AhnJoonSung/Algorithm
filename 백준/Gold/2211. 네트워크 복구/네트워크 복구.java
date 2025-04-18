import java.util.*;

class Main {
    static final int INF = 1_000_000 * 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int[] prv = dijkstra(graph, 1);

        System.out.println(n-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(prv[i] + " " + i).append("\n");
        }
        System.out.print(sb);
        sc.close();
    }

    static int[] dijkstra(List<List<Edge>> graph, int st) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] prv = new int[n];

        for (int i = 0; i <n; i++) {
            dist[i] = INF;
        }
        
        prv[st] = -1;
        dist[st] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(st, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.vertex])
                continue;

            for (Edge e: graph.get(cur.vertex)) {
                int newDist = dist[cur.vertex] + e.weight;
                if (dist[e.to] <= newDist)
                    continue;

                dist[e.to] = newDist;
                prv[e.to] = cur.vertex;
                pq.add(new Node(e.to, newDist));
            }
        }

        return prv;
    }
}

class Edge {
    int to, weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int vertex, dist;

    public Node(int vertex, int dist) {
        this.vertex = vertex;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}