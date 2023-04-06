package bfs.g3_2206_벽부수고이동하기;

import java.util.ArrayList;
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
    static ArrayList<Position> walls;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        walls = new ArrayList<>();
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] temp = sc.next().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                if (map[i][j] == WALL)
                    walls.add(new Position(i, j, -1));
            }
        }

        int min_distance = n * m + 1;
        for (Position wall : walls) {
            map[wall.i][wall.j] = EMPTY;

            boolean[][] visited = new boolean[n][m];
            Queue<Position> q = new LinkedList<>();
            visited[0][0] = true;
            q.add(new Position(0, 0, 1));

            boolean arrive = false;
            while (!q.isEmpty() && !arrive) {
                Position cur = q.poll();
                int dist = cur.dist + 1;
                for (int dir = 0; dir < 4; dir++) {
                    int i = cur.i + di[dir];
                    int j = cur.j + dj[dir];
                    if (i == (n - 1) && j == (m - 1)) {
                        arrive = true;
                        if (dist < min_distance)
                            min_distance = dist;
                        break;
                    }
                    if (i < 0 || i >= n || j < 0 || j >= m)
                        continue;
                    if (visited[i][j] || map[i][j] == WALL)
                        continue;
                    q.add(new Position(i, j, dist));
                    visited[i][j] = true;
                }
            }
            if (min_distance == n + m -1)
                break;
            map[wall.i][wall.j] = WALL;
        }
        if (min_distance > n * m) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(min_distance);
        }
    }
}

class Position {
    int i, j, dist;

    public Position(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }
}
