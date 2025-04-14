import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        while (TC-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int[] delays = new int[N+1];
            for (int i = 1; i <= N; i++)
                delays[i] = sc.nextInt();

            List<Node>[] graph = new List[N+1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();
                
            int[] inDegree = new int[N+1];
            for (int i = 0; i < K; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();

                inDegree[to]++;
                graph[from].add(new Node(to, delays[to]));
            }

            Queue<Node> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0)
                    q.add(new Node(i, delays[i]));
            }
            
            boolean[] visited = new boolean[N+1];
            int[] dp = new int[N+1];
            for (int i = 1; i <= N; i++)
                dp[i] = delays[i];
            
            while (!q.isEmpty()) {
                Node cur = q.poll();
                
                if (visited[cur.vertex]) continue;
                visited[cur.vertex] = true;
                
                for (Node node: graph[cur.vertex]) {
                    dp[node.vertex] = Math.max(dp[node.vertex], dp[cur.vertex] + delays[node.vertex]);
                    if (--inDegree[node.vertex] == 0)
                        q.add(node);
                }
            }
            
            int target = sc.nextInt();
            System.out.println(dp[target]);
        }
        
        sc.close();
    }
}

class Node implements Comparable<Node> {
    int vertex, weight;

    public Node (int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node other) {
        return other.weight - this.weight;
    }
}