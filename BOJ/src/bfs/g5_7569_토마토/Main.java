package bfs.g5_7569_토마토;

import java.util.Scanner;

public class Main {
    static final int[] di = {1, -1, 0, 0, 0, 0};
    static final int[] dj = {0, 0, 0, 0, 1, -1};
    static final int[] dk = {0, 0, -1, 1, 0, 0};

    static int m, n, h;

    public static void main(String[] args) {
        // 6개 방향
        // 1만 들어가서 bfs 돌리기, 전체 0 개수와 같아 지면 바로 리턴
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();

        int[][][]box = new int[h][n][m];
        int need_change = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = sc.nextInt();
                    if (box[i][j][k] == 0)
                        need_change++;
                }
            }
        }

        if (need_change == 0) {
            System.out.println(0);
            return ;
        }
        int day = 1;
        int change_cnt = 0;
        while (true) {
            int temp = bfs(box);
            change_cnt += temp;
            if (change_cnt == need_change) {
                System.out.println(day);
                return ;
            }
            if (temp == 0) {
                System.out.println(-1);
                return ;
            }
            day++;
        }

    }

    public static int bfs(int[][][] box) {
        int change_cnt = 0;
        boolean[][][] visit = new boolean[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (!isValid(i, j, k)) continue;
                    if (box[i][j][k] != 1) continue;
                    if (visit[i][j][k]) continue;

                    visit[i][j][k] = true;
                    Position cur = new Position(i, j, k);
                    for (int dir = 0; dir < 6; dir++) {
                        int ni = cur.i + di[dir];
                        int nj = cur.j + dj[dir];
                        int nk = cur.k + dk[dir];

                        if (!isValid(ni, nj, nk)) continue;
                        if (box[ni][nj][nk] != 0) continue;
                        box[ni][nj][nk] = 1;
                        visit[ni][nj][nk] = true;
                        change_cnt++;
                    }
                }
            }
        }
        return (change_cnt);
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

class Position {
    int i, j, k;

    public Position(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }
}
