package gold.g4_14499_주사위굴리기;

import java.util.Scanner;

public class Main {
    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        Dice dice = new Dice();
        while (k-- > 0) {
            int cmd = sc.nextInt();

            int nx = x + dx[cmd];
            int ny = y + dy[cmd];

            if (isOut(nx, ny, n, m))
                continue;

            dice.roll(cmd);
            x = nx;
            y = ny;

            if (map[x][y] == 0) {
                map[x][y] = dice.getBottom();
            } else {
                dice.setBottom(map[x][y]);
                map[x][y] = 0;
            }

            System.out.println(dice.getTop());
        }
    }

    public static boolean isOut(int x, int y, int n, int m) {
        if (x < 0 || x >= n)
            return true;
        if (y < 0 || y >= m)
            return true;
        return false;
    }
}

class Dice {
    private final int TOP = 2;
    private final int BOTTOM = 5;
    private final int LEFT = 1;
    private final int RIGHT = 3;
    private final int FRONT = 0;
    private final int BACK = 4;
    private int[] dice = new int[6];

    public void roll(int cmd) {
        if (cmd == 1) {
            int left = dice[LEFT];
            dice[LEFT] = dice[BOTTOM];
            dice[BOTTOM] = dice[RIGHT];
            dice[RIGHT] = dice[TOP];
            dice[TOP] = left;
        }
        else if (cmd == 2) {
            int left = dice[LEFT];
            dice[LEFT] = dice[TOP];
            dice[TOP] = dice[RIGHT];
            dice[RIGHT] = dice[BOTTOM];
            dice[BOTTOM] = left;
        }
        else if (cmd == 3) {
            int front = dice[FRONT];
            dice[FRONT] = dice[TOP];
            dice[TOP] = dice[BACK];
            dice[BACK] = dice[BOTTOM];
            dice[BOTTOM] = front;
        }
        else if (cmd == 4) {
            int front = dice[FRONT];
            dice[FRONT] = dice[BOTTOM];
            dice[BOTTOM] = dice[BACK];
            dice[BACK] = dice[TOP];
            dice[TOP] = front;
        }
    }

    public void setBottom(int newBottom) {
        dice[BOTTOM] = newBottom;
    }

    public int getBottom() {
        return dice[BOTTOM];
    }

    public int getTop() {
        return dice[TOP];
    }
}