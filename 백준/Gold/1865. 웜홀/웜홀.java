import java.util.*;

public class Main {
    static final String YES = "YES", NO = "NO";
    static final int INF = Integer.MAX_VALUE;

    static int n, m, w;
    static int[][] dist;

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        int TC = sc.nextInt();
        while (TC-- > 0) {
            testcase();
        }
    }

    static void testcase() {
        n = sc.nextInt();
        m = sc.nextInt();
        w = sc.nextInt();

        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            int time = sc.nextInt();

            dist[start][end] = Math.min(dist[start][end], time);
            dist[end][start] = Math.min(dist[end][start], time);
        }

        for (int i = 0; i < w; i++) {
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            int time = sc.nextInt() * -1;

            dist[start][end] = Math.min(dist[start][end], time);
        }

        for (int k = 0; k < n; k++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (dist[start][k] == INF || dist[k][end] == INF) continue;

                    dist[start][end] = Math.min(dist[start][end], dist[start][k] + dist[k][end]);
                }
            }
        }

        for (int start = 0; start < n; start++) {
            if (dist[start][start] < 0) {
                System.out.println(YES);
                return;
            }
        }

        System.out.println(NO);
    }
}