package bfs.g3_1600_말이되고픈원숭이;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static final int[] h_di = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static final int[] h_dj = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static final int[] di = {-1, 1, 0, 0};
    public static final int[] dj = {0, 0, -1, 1};
    public static final int OBSTACLE = 1;
    public static final int IMPOSSIBLE = -1;

    public static int k, w, h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        w = sc.nextInt();
        h = sc.nextInt();
        int[][] map = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        boolean[][][] visited = new boolean[h][w][k + 1];
        visited[0][0][k] = true;
        Queue<Monkey> monkeys = new LinkedList<>();
        monkeys.add(new Monkey(0, 0, k, 0));
        while (!monkeys.isEmpty()) {
            Monkey cur = monkeys.poll();
            if (arrived(cur.i, cur.j)) {
                System.out.println(cur.move);
                return ;
            }
            if (cur.k > 0) {
                for (int dir = 0; dir < 8; dir++) {
                    int i = cur.i + h_di[dir];
                    int j = cur.j + h_dj[dir];
                    if (isOut(i, j) || visited[i][j][cur.k - 1])
                        continue;
                    if (map[i][j] == OBSTACLE)
                        continue;
                    visited[i][j][cur.k - 1] = true;
                    monkeys.add(new Monkey(i, j, cur.k - 1, cur.move + 1));
                }
            }
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (isOut(i, j) || visited[i][j][cur.k])
                    continue;
                if (map[i][j] == OBSTACLE)
                    continue;
                visited[i][j][cur.k] = true;
                monkeys.add(new Monkey(i, j, cur.k, cur.move + 1));
            }
        }
        System.out.println(IMPOSSIBLE);
    }

    public static boolean arrived(int i, int j) {
        if (i == h - 1 && j == w - 1)
            return (true);
        return (false);
    }

    public static boolean isOut(int i, int j) {
        if (i < 0 || i >= h || j < 0 || j >= w)
            return (true);
        return (false);
    }
}

class Monkey {
    int i, j, k;
    int move;

    public Monkey(int i, int j, int k, int move) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.move = move;
    }
}