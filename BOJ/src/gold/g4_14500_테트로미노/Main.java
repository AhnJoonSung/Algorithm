package gold.g4_14500_테트로미노;

import java.util.Scanner;

public class Main {
    private static final int IMPOSSIBLE = -4_000;
    public static int max;
    public static int n, m;
    public static int[][] paper;
    private static final int[] di = {0, 0, 0, // 일자
            0, 1, 1, // 네모
            1, 2, 2, // L
            1, 2, 2, // L 대칭
            1, 1, 2, // 번개
            1, 1, 2, // 번개 대칭
            0, 0, 1, // ㅗ

    };
    private static final int[] dj = {1, 2, 3, // 일자
            1, 0, 1, // 네모
            0, 0, 1, // L
            0, 0, -1, // L 대칭
            0, 1, 1, // 번개
            0, -1, -1, // 번개 대칭
            1, 2, 1 // ㅗ
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        paper = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        for (int cnt = 0; cnt < 4; cnt++) {
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[i].length; j++) {
                    calculate(i, j);
                }
            }
            //printPaper();
            rotate();
        }
        System.out.println(max);
    }

    private static void printPaper() {
        for (int i = 0; i < paper.length; i++) {
            for (int j = 0; j < paper[0].length; j++) {
                System.out.print(paper[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    private static void calculate(int i, int j) {
        int sum = 0;
        for (int dir = 0; dir < di.length; dir++) {
            if (dir % 3 == 0)
                sum = paper[i][j];

            int ni = i + di[dir];
            int nj = j + dj[dir];
            if (isValid(ni, nj))
                sum += paper[ni][nj];
            else
                sum += IMPOSSIBLE;

            if (dir % 3 == 2)
                max = Math.max(max, sum);
        }
    }

    private static boolean isValid(int i, int j) {
        if (i < 0 || j < 0)
            return false;
        if (i >= paper.length || j >= paper[i].length)
            return false;
        return true;
    }

    private static void rotate() {
        int[][] rotated = new int[paper[0].length][paper.length];

        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated[i].length; j++) {
                rotated[i][j] = paper[paper.length - 1 - j][i];
            }
        }
        paper = rotated;
    }
}