package dynamic_programming.s1_1932_정수삼각형;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int num = sc.nextInt();
                if (i == 0) {
                    dp[i][j] = num;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = num + dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] = num + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = num + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
        int max = 0;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, dp[n - 1][j]);
        }
        System.out.println(max);
    }
}
