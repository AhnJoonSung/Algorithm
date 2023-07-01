package silver.s1_14940_쉬운최단거리;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final int WALL = 0;
    private static final int TARGET = 2;
    private static final int[] di = {-1, 1, 0, 0};
    private static final int[] dj = {0, 0, -1, 1};
    private static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        map = new int[n][m];
        int[][] distance = new int[n][m];
        Node target = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == TARGET) {
                    target = new Node(i, j, 0);
                    distance[i][j] = 0;
                } else
                    distance[i][j] = -1 * map[i][j];
            }
        }

        bfs(target, distance);
        printMap(distance);
    }

    public static void bfs(Node target, int[][] distance) {
        Queue<Node> q = new LinkedList<>();
        q.add(target);
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[target.i][target.j] = true;
        distance[target.i][target.j] = target.distance;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                int newDist = cur.distance + 1;

                if (isOut(i, j) || visited[i][j])
                    continue;

                visited[i][j] = true;
                distance[i][j] = newDist;
                q.add(new Node(i, j, newDist));
            }
        }
    }

    public static void printMap(int[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int[] row : map) {
            for (int dist : row) {
                sb.append(dist).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static boolean isOut(int i, int j) {
        if (i < 0 || i >= map.length)
            return true;
        if (j < 0 || j >= map[i].length)
            return true;
        if (map[i][j] == WALL)
            return true;
        return false;
    }
}

class Node {
    int i, j, distance;

    public Node(int i, int j, int distance) {
        this.i = i;
        this.j = j;
        this.distance = distance;
    }
}
