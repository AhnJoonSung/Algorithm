package bfs.g5_10026_적록색약;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        char[][] weakMap = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = map[i][j];
                if (c == 'G')
                    c = 'R';
                weakMap[i][j] = c;
            }
        }

        System.out.println(getRegion(map) + " " + getRegion(weakMap));
    }

    public static int getRegion(char[][] map) {
        int region = 0;
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j])
                    continue;
                char c = map[i][j];
                region++;
                visited[i][j] = true;
                q.add(new Position(i,j));
                while (!q.isEmpty()) {
                    Position cur = q.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int newi = cur.i + di[dir];
                        int newj = cur.j + dj[dir];
                        if (!isValid(newi, newj) || visited[newi][newj])
                            continue;
                        if (map[newi][newj] != c)
                            continue;
                        visited[newi][newj] = true;
                        q.add(new Position(newi, newj));
                    }
                }
            }
        }
        return (region);
    }

    public static boolean isValid(int i, int j) {
        if (i < 0 || i >= n)
            return false;
        if (j < 0 || j >= n)
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
