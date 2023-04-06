package bfs.g4_5427_불;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final char WALL = '#';
    static final char START = '@';
    static final char FIRE = '*';
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    static int w, h;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            w = sc.nextInt();
            h = sc.nextInt();

            map = new char[h][w];

            int[][] fired = new int[h][w];
            for (int i = 0; i < h; i++) {
                Arrays.fill(fired[i], Integer.MAX_VALUE);
            }
            boolean[][] visit = new boolean[h][w];
            Queue<Fire> fq = new LinkedList<>();
            Queue<Sang> sq = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                String s = sc.next();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == FIRE) {
                        fired[i][j] = 0;
                        fq.add(new Fire(i, j));
                    }
                    else if (map[i][j] == START) {
                        visit[i][j] = true;
                        sq.add(new Sang(i, j, 0));
                    }
                }
            }
            while (!fq.isEmpty()) {
                Fire cur = fq.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int i = cur.i + di[dir];
                    int j = cur.j + dj[dir];
                    if (i < 0 || i >= h || j < 0 || j >= w)
                        continue;
                    if (map[i][j] == WALL || map[i][j] == FIRE)
                        continue;
                    fired[i][j] = fired[cur.i][cur.j] + 1;
                    map[i][j] = FIRE;
                    fq.add(new Fire(i, j));
                }
            }
            boolean escape = false;
            while (!sq.isEmpty() && !escape) {
                Sang cur = sq.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int i = cur.i + di[dir];
                    int j = cur.j + dj[dir];
                    if (i < 0 || i >= h || j < 0 || j >= w) {
                        System.out.println(cur.time + 1);
                        escape = true;
                        break;
                    }
                    if (fired[i][j] <= cur.time + 1)
                        continue;
                    if (map[i][j] == WALL || visit[i][j])
                        continue;
                    visit[i][j] = true;
                    sq.add(new Sang(i, j, cur.time + 1));
                }
            }
            if (!escape)
                System.out.println("IMPOSSIBLE");
        }
    }
}

class Fire {
    int i, j;

    public Fire(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Sang {
    int i, j, time;

    public Sang(int i, int j, int time) {
        this.i = i;
        this.j = j;
        this.time = time;
    }
}