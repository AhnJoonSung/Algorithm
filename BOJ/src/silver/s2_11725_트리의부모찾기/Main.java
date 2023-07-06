package silver.s2_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        int[] parent = new int[n + 1];

        dfs(1, parent, visited);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++)
            sb.append(parent[i]).append("\n");
        System.out.print(sb);
    }

    public static void dfs(int u, int[] parent, boolean[] visited) {
        visited[u] = true;

        for (int v : tree.get(u)) {
            if (visited[v])
                continue;
            parent[v] = u;
            dfs(v, parent, visited);
        }
    }
}
