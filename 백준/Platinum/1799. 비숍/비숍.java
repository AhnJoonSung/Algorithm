import java.util.*;

public class Main {
    static final int IMP = 0;

    static int n, max;
    static int[][] board;

    static boolean[] diag1;
    static boolean[] diag2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        board = new int[n][n];

        List<int[]> black = new ArrayList<>();
        List<int[]> white = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();

                if (board[i][j] == IMP) continue;

                if ((i + j) % 2 == 0)
                    white.add(new int[]{i, j});
                else
                    black.add(new int[]{i, j});
            }
        }
        
        diag1 = new boolean[2*n - 1];
        diag2 = new boolean[2*n - 1];

        backtrack(0, black, 0);
        int answer = max;
        max = 0;
        backtrack(0, white, 0);
        answer += max;

        System.out.println(answer);
    }

    static void backtrack(int depth, List<int[]> cells, int cnt) {
        if (depth == cells.size()) {
            max = Math.max(max, cnt);
            return;
        }

        int[] cell = cells.get(depth);
        int i = cell[0], j = cell[1];

        if (!diag1[i + j] && !diag2[i - j + (n - 1)]) {
            diag1[i + j] = true;
            diag2[i -j + (n - 1)] = true;
            backtrack(depth + 1, cells, cnt + 1);
            diag1[i + j] = false;
            diag2[i -j + (n - 1)] = false;
        }

        backtrack(depth + 1, cells, cnt);
    }
}