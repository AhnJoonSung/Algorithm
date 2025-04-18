import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int n, m;
    static int[][] dist;
    static int[][] next;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        readInput(sc);
        floydWarshall();
        printDistanceMatrix();
        printAllPaths();
    }

    static void readInput(Scanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();

        dist = new int[n + 1][n + 1];
        next = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(next[i], -1);
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
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    static void printDistanceMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void printAllPaths() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                List<Integer> path = getPath(i, j);
                if (path.isEmpty()) {
                    sb.append("0\n");
                } else {
                    sb.append(path.size()).append(" ");
                    for (int city : path) {
                        sb.append(city).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb);
    }

    static List<Integer> getPath(int from, int to) {
        if (next[from][to] == -1) return Collections.emptyList();

        List<Integer> path = new ArrayList<>();
        while (from != to) {
            path.add(from);
            from = next[from][to];
        }
        path.add(to);
        return path;
    }
}
