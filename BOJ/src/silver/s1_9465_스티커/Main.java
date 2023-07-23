package silver.s1_9465_스티커;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        while (tc-- > 0) {
            int n = sc.nextInt();
            int[][] stickers = new int[n][2];
            int[][] dp = new int[n][3];

            for (int i = 0; i < n; i++)
                stickers[i][0] = sc.nextInt();
            for (int i = 0; i < n; i++)
                stickers[i][1] = sc.nextInt();

            dp[0][1] = stickers[0][0];
            dp[0][2] = stickers[0][1];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + stickers[i][0];
                dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + stickers[i][1];
            }

            System.out.println(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n-1][2]));
        }
    }
}
