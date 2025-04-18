import java.util.*;

class Main {
    static final int INF = 1_000_000_000;

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
        }

        int st = sc.nextInt();
        int end = sc.nextInt();

        Result result = dijkstra(graph, st, end);
        Deque<Integer> path = result.getPath(st, end);

        System.out.println(result.dist);
        System.out.println(path.size());
        for (int p: path)
            System.out.print(p + " ");
        sc.close();
    }

    static Result dijkstra(List<List<Edge>> graph, int st, int end) {
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

        return new Result(prv, dist[end]);
    }
}

class Result {
    int[] prv;
    int dist;

    public Result(int[] prv, int dist) {
        this.prv = prv;
        this.dist = dist;
    }

    public Deque<Integer> getPath(int st, int end) {
        Deque<Integer> path = new ArrayDeque<>();
        for (int i = end; i != -1; i = prv[i]) {
            path.addFirst(i);
        }

        return path;
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