import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int n, m;
    static int[][] dist;
    static int[][] next;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        
        dist = new int[n+1][n+1];
        next = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = INF;
                next[i][j] = -1;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            if (dist[from][to] > cost) {
                dist[from][to] = cost;
                next[from][to] = to;
            }
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int from = 1; from <= n; from++) {
            for (int to = 1; to <= n; to++) {
                sb.append((dist[from][to] == INF ? 0 : dist[from][to]) + " ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                List<Integer> path = getPath(i, j);
                if (path.isEmpty()) {
                    sb.append("0\n");
                } else {
                    sb.append(path.size()).append(" ");
                    for (int city : path) {
                        sb.append((city)).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb);
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int from = 1; from <= n; from++) {
                if (dist[from][k] == INF) continue;

                for (int to = 1; to <= n; to++) {
                    if (dist[k][to] == INF) continue;

                    if (dist[from][to] > dist[from][k] + dist[k][to]) {
                        dist[from][to] = dist[from][k] + dist[k][to];
                        next[from][to] = next[from][k];
                    }
                }
            }
        }
    }

    static List<Integer> getPath(int from, int to) {
        if (next[from][to] == -1)
            return Collections.emptyList();

        List<Integer> path = new ArrayList<>();
        while (from != to) {
            path.add(from);
            from = next[from][to];
        }
        path.add(to);

        return path;
    }
}