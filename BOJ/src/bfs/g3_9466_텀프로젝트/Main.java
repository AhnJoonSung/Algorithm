package bfs.g3_9466_텀프로젝트;

import java.util.Scanner;

public class Main {
    static final int GROUP = 100001;
    static final int OUT = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            int[] students = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                students[i] = sc.nextInt();
            }
            int ans = n;
            int[] visited = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (visited[i] != 0)
                    continue;
                int cur = i;
                while (visited[cur] == 0) {
                    visited[cur] = i;
                    cur = students[cur];
                }
                while (visited[cur] == i) {
                    ans--;
                    visited[cur] = GROUP;
                    cur = students[cur];
                }
                cur = i;
                while (visited[cur] != GROUP && visited[cur] != OUT) {
                    visited[cur] = OUT;
                    cur = students[cur];
                }
            }
            System.out.println(ans);
        }
    }
}
