package silver.s2_1012_유기농배추;

import java.io.IOException;;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int cols, rows;
    static int[][] map;
    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            cols = sc.nextInt();
            rows = sc.nextInt();
            int n = sc.nextInt();

            map = new int[rows][cols];
            boolean[][] visited = new boolean[rows][cols];
            while (n-- > 0) {
                int j = sc.nextInt();
                int i = sc.nextInt();
                map[i][j] = 1;
            }

            int ans = 0;
            Queue<Position> q = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (isValid(i, j) && !visited[i][j]) {
                        visited[i][j] = true;
                        q.add(new Position(i, j));
                        ans++;
                        while (!q.isEmpty()) {
                            Position cur = q.poll();
                            int curi = cur.i;
                            int curj = cur.j;
                            visited[curi][curj] = true;
                            for (int dir = 0; dir < 4; dir++) {
                                int newi = curi + di[dir];
                                int newj = curj + dj[dir];
                                if (isValid(newi, newj) && !visited[newi][newj]) {
                                    visited[newi][newj] = true;
                                    q.add(new Position(newi, newj));
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }

    public static boolean isValid(int i, int j) {
        if (i < 0 || rows <= i)
            return false;
        if (j < 0 || cols <= j)
            return false;
        if (map[i][j] == 0)
            return false;
        return true;
    }
}

class Position {
    int i, j;
    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
