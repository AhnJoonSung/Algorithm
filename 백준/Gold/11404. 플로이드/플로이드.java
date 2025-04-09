import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int n, m;
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            int cost = sc.nextInt();

            dist[from][to] = Math.min(dist[from][to], cost);
        }

        for (int k = 0; k < n; k++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (dist[from][k] == INF || dist[k][to] == INF) continue;

                    dist[from][to] = Math.min(dist[from][to], dist[from][k] + dist[k][to]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                sb.append((dist[from][to] == INF ? 0 : dist[from][to]) + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}