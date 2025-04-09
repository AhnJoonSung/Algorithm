import java.util.*;

public class Main {
    static int v, maxDist = -1, farthestNode = -1;
    static List<int[]>[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        tree = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            int from = sc.nextInt() - 1;
            while (true) {
                int input = sc.nextInt();
                if (input == -1)
                    break;
                int to = input - 1;
                int cost = sc.nextInt();
                tree[from].add(new int[]{to, cost});
            }
        }

        dfs(new boolean[v], 0, 0);
        dfs(new boolean[v], farthestNode, 0);
        System.out.println(maxDist);
    }

    static void dfs(boolean[] visited, int node, int dist) {
        if (visited[node]) return;

        visited[node] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        for (int[] data: tree[node]) {
            dfs(visited, data[0], dist + data[1]);
        }
    }
}