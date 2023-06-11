package silver.s1_7562_나이트의이동;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[] di = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dj = {-2, -1 , 1, 2, 2, 1, -1, -2};

    static int n, row, col, tarR, tarC;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            n = sc.nextInt();
            row = sc.nextInt();
            col = sc.nextInt();
            tarR = sc.nextInt();
            tarC = sc.nextInt();

            boolean[][] visit = new boolean[n][n];
            Queue<Knight> q = new LinkedList<>();

            visit[row][col] = true;
            q.add(new Knight(row, col, 0));
            while (!q.isEmpty()) {
                Knight cur = q.poll();
                if (cur.i == tarR && cur.j == tarC) {
                    System.out.println(cur.movCnt);
                    break;
                }
                for (int dir = 0; dir < 8; dir++) {
                    int i = cur.i + di[dir];
                    int j = cur.j + dj[dir];

                    if (isValid(i, j) && !visit[i][j]) {
                        visit[i][j] = true;
                        q.add(new Knight(i, j, cur.movCnt + 1));
                    }
                }
            }
        }
    }

    public static boolean isValid(int i, int j) {
        if (i < 0 || i >= n)
            return false;
        if (j < 0 || j >= n)
            return false;
        return true;
    }
}

class Knight {
    int i, j, movCnt;

    public Knight(int i, int j, int movCnt) {
        this.i = i;
        this.j = j;
        this.movCnt = movCnt;
    }
}
