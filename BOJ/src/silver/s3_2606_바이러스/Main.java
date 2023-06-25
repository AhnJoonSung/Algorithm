package silver.s3_2606_바이러스;

import java.util.Scanner;

public class Main {
    private static int map[][];
    private static boolean visited[];
    private static int n, m;
    private static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            map[from][to] = 1;
            map[to][from] = 1;
        }

        dfs(1);
        System.out.println(cnt);
    }

    private static void dfs(int i) {
        for (int j = 2; j <= n; j++) {
            if (!visited[j] && map[i][j] == 1) {
                visited[j] = true;
                cnt++;
                dfs(j);
            }
        }
    }
}
