package bfs.g3_2206_벽부수고이동하기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int IMPOSSIBLE = -1;
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] temp = sc.next().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int min = n * m + 1;
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        q.add(new Node(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.i == n - 1 && cur.j == m - 1) {
                min = cur.dist;
                break;
            }
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                int dist = cur.dist + 1;
                if (i < 0 || i >= n || j < 0 || j >= m)
                    continue;
                if (cur.crash) {
                    if (map[i][j] == EMPTY && !visited[i][j][1]) {
                        visited[i][j][1] = true;
                        q.add(new Node(i, j, dist, true));
                    }
                }
                else {
                    if (map[i][j] == WALL && !visited[i][j][1]) {
                        visited[i][j][1] = true;
                        q.add(new Node(i, j, dist, true));
                    }
                    else if (map[i][j] == EMPTY && !visited[i][j][0]) {
                        visited[i][j][0] = true;
                        q.add(new Node(i, j, dist, false));
                    }
                }
            }
        }
        if (min == n * m + 1)
            System.out.println(IMPOSSIBLE);
        else
            System.out.println(min);
    }
}

class Node {
    int i, j, dist;
    boolean crash;

    public Node(int i, int j, int dist, boolean crash) {
        this.i = i;
        this.j = j;
        this.dist = dist;
        this.crash = crash;
    }
}
