package silver.s2_2630_색종이만들기;

import java.util.Scanner;

public class Main {
    private static final int WHITE = 0;
    private static final int BLUE = 1;
    private static int[][] paper;
    private static int white;
    private static int blue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        slice(0, 0, n);
        System.out.println(white);
        System.out.println(blue);
    }

    public static void slice(int i, int j, int size) {
        if (isComplete(i, j, size)) {
            if (paper[i][j] == WHITE)
                white++;
            else
                blue++;
            return;
        }

        int newSize = size / 2;
        slice(i, j, newSize);
        slice(i, j + newSize, newSize);
        slice(i + newSize, j, newSize);
        slice(i + newSize, j + newSize, newSize);
    }

    public static boolean isComplete(int start_i, int start_j, int size) {
        if (size == 1)
            return true;

        int target = paper[start_i][start_j];
        for (int i = start_i; i < start_i + size; i++) {
            for (int j = start_j; j < start_j + size; j++) {
                if (target != paper[i][j])
                    return false;
            }
        }
        return true;
    }
}
