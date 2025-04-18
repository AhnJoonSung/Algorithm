import java.util.*;

class Main {
    static final int INF = 200000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        // distance가 MAX_VALUE면 가는 경로가 없는 것
        int[] fromFirst = getDistances(graph, 1);
        int[] fromV1 = getDistances(graph, v1);
        int[] fromV2 = getDistances(graph, v2);

        int dist1 = fromFirst[v1] + fromV1[v2] + fromV2[n];
        int dist2 = fromFirst[v2] + fromV2[v1] + fromV1[n];

        int answer = Math.min(dist1, dist2);
        System.out.println(answer >= INF? -1 : answer);
        sc.close();
    }

    static int[] getDistances(List<List<Edge>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
        }

        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.vertex])
                continue;

            for (Edge e: graph.get(cur.vertex)) {
                int newDist = dist[cur.vertex] + e.weight;
                if (newDist < dist[e.to]) {
                    dist[e.to] = newDist;
                    pq.add(new Node(e.to, newDist));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node> {
    int vertex, dist;

    public Node(int vertex, int dist) {
        this.vertex = vertex;
        this.dist = dist;
    }

    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}

class Edge {
    int to, weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}