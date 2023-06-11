package silver.s1_1149_RGB거리;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] r = new int[n];
        int[] g = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            r[i] = sc.nextInt();
            g[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        int[][] dp = new int[n][3];
        dp[0][0] = r[0];
        dp[0][1] = g[0];
        dp[0][2] = b[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + r[i];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + g[i];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + b[i];
        }

        int min = 1000 * n;
        for (int i = 0; i < 3; i++) {
            if (dp[n - 1][i] < min)
                min = dp[n - 1][i];
        }
        System.out.println(min);
    }
}
