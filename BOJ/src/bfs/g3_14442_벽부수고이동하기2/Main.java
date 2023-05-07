package bfs.g3_14442_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int IMPOSSIBLE = -1;
    public static final int[] di = {-1, 1, 0, 0};
    public static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        int k = Integer.parseInt(inputs[2]);
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        boolean[][][] visited = new boolean[n][m][k + 1];
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0, 0, 1, k));
        visited[0][0][k] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.i == n - 1 && cur.j == m - 1) {
                System.out.println(cur.dist);
                return;
            }
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                int dist = cur.dist + 1;
                if (i < 0 || i >= n || j < 0 || j >= m)
                    continue;
                if (visited[i][j][cur.remain])
                    continue;
                if (map[i][j] == WALL && cur.remain < 1)
                    continue;

                if (map[i][j] == WALL) {
                    q.add(new Node(i, j, dist, cur.remain - 1));
                    visited[i][j][cur.remain - 1] = true;
                }
                else {
                    q.add(new Node(i, j, dist, cur.remain));
                    visited[i][j][cur.remain] = true;
                }
            }
        }
        System.out.println(IMPOSSIBLE);
    }
}

class Node {
    int i, j, dist, remain;

    public Node(int i, int j, int dist, int remain) {
        this.i = i;
        this.j = j;
        this.dist = dist;
        this.remain = remain;
    }
}
