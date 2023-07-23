package gold.g4_1753_최단경로;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    public int index, distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int compareTo(Node n) {
        return this.distance - n.distance;
    }
}

public class Main {
    public static final int INF = (int)1e9;
    public static int v, e, start;
    public static int[] dp;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dp[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int idx = node.index;
            int dist = node.distance;

            if (dp[idx] < dist) continue;

            for (int i = 0; i < graph.get(idx).size(); i++) {
                int cost = dp[idx] + graph.get(idx).get(i).distance;

                if (cost < dp[graph.get(idx).get(i).index]) {
                    dp[graph.get(idx).get(i).index] = cost;
                    pq.offer(new Node(graph.get(idx).get(i).index, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        dp = new int[v+1];
        Arrays.fill(dp, INF);

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        dijkstra(start);

        for (int i = 1; i <= v; i++) {
            if (dp[i] == INF)
                System.out.println("INF");
            else
                System.out.println(dp[i]);
        }
    }
}
