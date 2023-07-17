package silver.s1_11660_구간합구하기5;

import java.util.Scanner;

public class Main {
    static int n;
    static int[][] table;
    static int[][] sumTable;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int cnt = sc.nextInt();

        table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i][j] = sc.nextInt();
            }
        }

        setSumTable();

        StringBuilder sb = new StringBuilder();
        while (cnt-- > 0) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            sb.append(getSum(x1, y1, x2, y2)).append("\n");
        }
        System.out.print(sb);
    }

    public static int getSum(int x1, int y1, int x2, int y2) {
        int sum = 0;
        for (int i = x1; i <= x2; i++) {
            sum += (sumTable[i][y2] - sumTable[i][y1 - 1]);
        }
        return sum;
    }

    public static void setSumTable() {
        sumTable = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sumTable[i][j] = sumTable[i][j - 1] + table[i][j];
            }
        }
    }
}
