package bfs.g4_2573_빙산;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int WATER = 0;
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int year = 1;
        while (true) {
            melt(map);
            int remain = getRemain(map);
            if (remain > 0) {
                System.out.println(year);
                return;
            }
            if (remain < 0) {
                System.out.println(0);
                return ;
            }
            year++;
        }
    }

    public static int getRemain(int[][] map) {
        int n = map.length;
        int m = map[0].length;

        int cnt = 0;
        Node ice = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != WATER) {
                    if (cnt == 0)
                        ice = new Node(i, j);
                    cnt++;
                }
            }
        }

        if (ice == null)
            return (-1);
        Queue<Node> ices = new LinkedList<>();
        ices.add(ice);
        boolean[][] visited = new boolean[n][m];
        while (!ices.isEmpty()) {
            Node cur = ices.poll();
            cnt--;
            visited[cur.i][cur.j] = true;
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= n || j < 0 || j >= m)
                    continue;
                if (visited[i][j] || map[i][j] == WATER)
                    continue;
                visited[i][j] = true;
                ices.add(new Node(i, j));
            }
        }
        return (cnt);
    }

    public static void melt(int[][] map) {
        int n = map.length;
        int m = map[0].length;

        Queue<Node> ocean = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == WATER)
                    ocean.add(new Node(i, j));
            }
        }

        while (!ocean.isEmpty()) {
            Node cur = ocean.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= n || j < 0 || j >= m)
                    continue;
                if (map[i][j] == WATER)
                    continue;
                map[i][j]--;
            }
        }
    }
}

class Node {
    int i, j;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
