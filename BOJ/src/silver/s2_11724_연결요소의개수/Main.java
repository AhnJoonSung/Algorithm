package silver.s2_11724_연결요소의개수;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        while (m-- > 0) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        check = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (check[i])
                continue;
            dfs(i);
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void dfs(int i) {
        if (check[i])
            return;

        check[i] = true;
        for (int j : graph.get(i)) {
            if (!check[j])
                dfs(j);
        }
    }
}
