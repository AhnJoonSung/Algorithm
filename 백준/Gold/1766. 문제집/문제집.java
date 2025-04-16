import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer>[] graph = new List[n+1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        int[] inDegree = new int[n+1];
        
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            graph[from].add(to);
            inDegree[to]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                pq.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");

            for (int next: graph[cur]) {
                if (--inDegree[next] == 0)
                    pq.add(next);
            }
        }

        System.out.print(sb);
    }
}