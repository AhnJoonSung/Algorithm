package gold.g5_7576_토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final int RIPE = 1;
    private static final int UNRIPE = 0;
    private static int unripeCnt;
    private static int n, m;
    private static int[][] box;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        box = new int[n][m];
        Queue<Tomato> ripe = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                box[i][j] = sc.nextInt();
                if (box[i][j] == UNRIPE)
                    unripeCnt++;
                else if (box[i][j] == RIPE)
                    ripe.add(new Tomato(i, j, 0));
            }
        }

        System.out.println(bfs(0, ripe));
    }

    public static int bfs(int time, Queue<Tomato> ripe) {
        final int IMPOSSIBLE = -1;
        final int[] di = {-1, 1, 0, 0};
        final int[] dj = {0, 0, -1, 1};

        while (!ripe.isEmpty()) {
            Tomato cur = ripe.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                int newTime = cur.time + 1;

                if (isOut(i, j) || box[i][j] != UNRIPE)
                    continue;

                box[i][j] = RIPE;
                time = newTime;
                ripe.add(new Tomato(i, j, newTime));
                unripeCnt--;
            }
        }

        if (unripeCnt != 0)
            return IMPOSSIBLE;
        return time;
    }

    public static boolean isOut(int i, int j) {
        if (i < 0 || i >= n)
            return true;
        if (j < 0 || j >= m)
            return true;
        return false;
    }
}

class Tomato {
    int i, j, time;

    public Tomato(int i, int j, int time) {
        this.i = i;
        this.j = j;
        this.time = time;
    }
}
