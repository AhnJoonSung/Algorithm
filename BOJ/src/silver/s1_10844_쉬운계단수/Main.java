package silver.s1_10844_쉬운계단수;

import java.util.Scanner;

public class Main {
    private static final int MAX = 100;
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[][] dp = new long[MAX + 1][10];

        for (int i = 1; i < 10; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j + 1];
                else if (j == 9)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                dp[i][j] %= MOD;
            }
        }

        int ans = 0;
        for (int j = 0; j < 10; j++) {
            ans += dp[n][j];
            ans %= MOD;
        }

        System.out.println(ans);
    }
}
