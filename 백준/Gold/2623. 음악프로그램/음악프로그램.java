import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] inDegrees = new int[n + 1];

        List<Integer>[] graph = new List[n+1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            int from = sc.nextInt();
            for (int j = 0; j < k-1; j++) {
                int to = sc.nextInt();
                graph[from].add(to);
                inDegrees[to]++;
                from = to;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0)
                q.add(i);
        }

        boolean[] visited = new boolean[n+1];
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append("\n");

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int next: graph[cur]) {
                if (--inDegrees[next] == 0)
                    q.add(next);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] != 0) {
                System.out.println(0);
                return;
            }
        }

        System.out.print(sb);

        sc.close();
    }
}