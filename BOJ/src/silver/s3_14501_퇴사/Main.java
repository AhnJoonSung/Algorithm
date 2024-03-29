package silver.s3_14501_퇴사;

import java.util.Scanner;

public class Main {
    private static final int MAX = 16;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] t = new int[MAX];
        int[] p = new int[MAX];
        int[] dp = new int[MAX];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i + t[i] > n)
                dp[i] = dp[i + 1];
            else
                dp[i] = Math.max(dp[i + 1], p[i] + dp[i + t[i]]);
        }

        System.out.println(dp[0]);
    }
}
