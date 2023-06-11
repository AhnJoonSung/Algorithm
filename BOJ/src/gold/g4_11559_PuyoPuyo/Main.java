package gold.g4_11559_PuyoPuyo;

import java.util.*;

public class Main {
    public static final char EMPTY = '.';
    public static final int row = 12;
    public static final int col = 6;
    public static final int TRIGGER = 4;
    public static final int[] di = {-1, 1, 0, 0};
    public static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] map = new char[row][col];

        for (int i = 0; i < row; i++) {
            String str = sc.next();
            for (int j = 0; j < col; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int ans = 0;
        while (bomb(map)) {
            ans++;
            pull_down(map);
        }
        System.out.println(ans);
    }

    public static boolean bomb(char[][] map) {
        boolean is_bomb = false;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || map[i][j] == EMPTY)
                    continue;
                is_bomb = (is_bomb | bfs(map, new Point(i, j), map[i][j], visited));
            }
        }
        return (is_bomb);
    }

    public static boolean bfs(char[][] map, Point p, char c, boolean[][] visited) {
        boolean is_bomb = false;
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> temp = new ArrayList<>();

        q.add(p);
        temp.add(p);
        visited[p.i][p.j] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur.i + di[dir];
                int j = cur.j + dj[dir];
                if (i < 0 || i >= row || j < 0 || j >= col)
                    continue;
                if (map[i][j] != c || visited[i][j])
                    continue;
                q.add(new Point(i, j));
                temp.add(new Point(i, j));
                visited[i][j] = true;
            }
        }
        if (temp.size() >= TRIGGER) {
            for (Point target : temp) {
                map[target.i][target.j] = EMPTY;
            }
            is_bomb = true;
        }
        return (is_bomb);
    }

    public static void pull_down(char[][] map) {
        for (int j = 0; j < col; j++) {
            char[] temp = new char[row];
            Arrays.fill(temp, EMPTY);
            int t_idx = row - 1;

            for (int i = row - 1; i >= 0; i--) {
                if (map[i][j] != EMPTY) {
                    temp[t_idx] = map[i][j];
                    t_idx--;
                }
            }

            for (int i = 0; i < row; i++)
                map[i][j] = temp[i];
        }
    }
}

class Point {
    int i, j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}