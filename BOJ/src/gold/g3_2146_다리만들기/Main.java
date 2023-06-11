package gold.g3_2146_다리만들기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static final int SEA = 0;
    public static final int LAND = 1;
    public static final int[] di = {-1, 1, 0, 0};
    public static final int[] dj = {0, 0, -1, 1};

    public static int n, min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        min = n * n;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int country = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == SEA)
                    continue;
                if (map[i][j] == LAND) {
                    setCountry(map, new Node(i, j, 0), country);
                    country++;
                }
            }
        }

        int now = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == now) {
                    bfs(map, now);
                    now++;
                }
            }
        }
        System.out.println(min);
    }

    public static void bfs(int[][] map, int country) {
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == country)
                    q.add(new Node(i, j, 0));
            }
        }

        boolean[][] visited = new boolean[n][n];
        while (!q.isEmpty()) {
            Node cur = q.poll();
            visited[cur.i][cur.j] = true;
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= n || j < 0 || j >= n)
                    continue;
                if (map[i][j] == country || visited[i][j])
                    continue;
                if (map[i][j] > 1) {
                    min = Math.min(min, cur.dist);
                    return ;
                }
                visited[i][j] = true;
                q.add(new Node(i, j, cur.dist + 1));
            }
        }
    }

    public static void setCountry(int[][] map, Node node, int country) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        boolean[][] visited = new boolean[n][n];
        map[node.i][node.j] = country;
        visited[node.i][node.j] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= n || j < 0 || j >= n)
                    continue;
                if (visited[i][j] || map[i][j] == SEA)
                    continue;
                visited[i][j] = true;
                map[i][j] = country;
                q.add(new Node(i, j, 0));
            }
        }
    }
}

class Node {
    int i, j, dist;

    public Node(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }
}
