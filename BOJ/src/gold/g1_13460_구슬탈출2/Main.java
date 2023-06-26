package gold.g1_13460_구슬탈출2;

import java.util.Scanner;

public class Main {
    public static final int MAX = 10;

    public static final char WALL = '#';
    public static final char BLUE = 'B';
    public static final char RED = 'R';
    public static final char EMPTY = '.';
    public static final char HOLE = 'O';
    public static final int IMPOSSIBLE = -1;

    public static int ans = MAX + 1;
    public static int n, m;
    public static char[][] board;
    public static boolean redIn = false;
    public static boolean blueIn = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        permutation(1);
        if (ans > MAX)
            System.out.println(IMPOSSIBLE);
        else
            System.out.println(ans);
    }

    private static void permutation(int cnt) {
        if (cnt > MAX)
            return;

        for (int i = 0; i < 4; i++) {
            char[][] temp = copyBoard();
            tilt(i);
            if (blueIn) {
                board = temp;
                continue;
            }
            if (redIn) {
                ans = Math.min(ans, cnt);
                return ;
            }
            permutation(cnt + 1);
            board = temp;
        }
    }

    private static char[][] copyBoard() {
        char[][] copied = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copied[i][j] = board[i][j];
            }
        }
        return copied;
    }

    private static void tilt(int dir) {
        blueIn = false;
        redIn = false;

        // 돌리기
        for (int i = 0; i < dir; i++)
            rotate();

        tiltLeft();

        // 되돌리기
        for (int i = 0; i < (4 - dir) % 4; i++)
            rotate();
    }

    private static void tiltLeft() {
        char[][] tilted = new char[n][m];
        initTilted(tilted);

        int nj;
        for (int i = 1; i < n - 1; i++) {
            nj = 1;
            for (int j = 1; j < m - 1; j++) {
                char cur = board[i][j];
                if (cur == WALL) {
                    nj = j + 1;
                    continue;
                }
                if (cur == HOLE) {
                    nj = j;
                    continue;
                }
                if (cur == RED) {
                    if (tilted[i][nj] == HOLE)
                        redIn = true;
                    else {
                        tilted[i][nj] = cur;
                        nj++;
                    }
                } else if (cur == BLUE) {
                    if (tilted[i][nj] == HOLE)
                        blueIn = true;
                    else {
                        tilted[i][nj] = cur;
                        nj++;
                    }
                }
            }
        }
        if (redIn || blueIn)
            return ;
        board = tilted;
    }

    private static void initTilted(char[][] tilted) {
        for (int i = 0; i < tilted.length; i++) {
            for (int j = 0; j < tilted[i].length; j++) {
                if (board[i][j] == RED || board[i][j] == BLUE)
                    tilted[i][j] = EMPTY;
                else
                    tilted[i][j] = board[i][j];
            }
        }
    }

    private static void rotate() {
        char[][] rotated = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[m - 1 - j][i] = board[i][j];
            }
        }
        board = rotated;
        swap();
    }

    private static void swap() {
        int temp = n;
        n = m;
        m = temp;
    }
}