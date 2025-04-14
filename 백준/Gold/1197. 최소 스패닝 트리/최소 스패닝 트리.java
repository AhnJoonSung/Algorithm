import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        List<Edge>[] graph = new List[V+1];
        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();

            graph[v1].add(new Edge(v1, v2, weight));
            graph[v2].add(new Edge(v2, v1, weight));
        }

        boolean[] visited = new boolean[V+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 1, 0));
        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (visited[cur.v2]) continue;
            visited[cur.v2] = true;
            mstWeight += cur.weight;

            for (Edge e: graph[cur.v2]) {
                pq.add(e);
            }
        }

        System.out.println(mstWeight);
        
        sc.close();
    }
}

class Edge implements Comparable<Edge>{
    int v1, v2, weight;

    public Edge (int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;   
    }
}