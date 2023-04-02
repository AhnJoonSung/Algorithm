package bfs.g5_7569_토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[] di = {1, -1, 0, 0, 0, 0};
    static final int[] dj = {0, 0, 0, 0, 1, -1};
    static final int[] dk = {0, 0, -1, 1, 0, 0};

    static int m, n, h;
    static int[][][] box;
    static Queue<Tomato> q;

    public static void main(String[] args) {
        // 6개 방향
        // 1만 들어가서 bfs 돌리기, 전체 0 개수와 같아 지면 바로 리턴
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();

        box = new int[h][n][m];
        q = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = sc.nextInt();
                    if (box[i][j][k] == 1)
                        q.add(new Tomato(i,j,k,0));
                }
            }
        }
        int day = bfs();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k] == 0) {
                        System.out.println(-1);
                        return ;
                    }
                }
            }
        }
        System.out.println(day);
    }

    public static int bfs() {
        int max_day = 0;
        while (!q.isEmpty()) {
            Tomato cur = q.poll();
            if (cur.day > max_day)
                max_day = cur.day;
            for (int dir = 0; dir < 6; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                int k = cur.k + dk[dir];

                if (!isValid(i, j, k)) continue;
                if (box[i][j][k] != 0) continue;
                box[i][j][k] = 1;
                q.add(new Tomato(i, j, k, cur.day + 1));
            }
        }
        return (max_day);
    }

    public static boolean isValid(int i, int j, int k) {
        if (i < 0 || i >= h)
            return false;
        if (j < 0 || j >= n)
            return false;
        if (k < 0 || k >= m)
            return false;
        return true;
    }
}

class Tomato {
    int i, j, k;
    int day;

    public Tomato(int i, int j, int k, int day) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.day = day;
    }
}
