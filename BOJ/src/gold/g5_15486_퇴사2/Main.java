package gold.g5_15486_퇴사2;

import java.util.Scanner;

public class Main {
    private static final int MAX = 1_500_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[MAX + 1];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i + t[i] > n)
                dp[i] = dp[i + 1];
            else
                dp[i] = Math.max(p[i] + dp[i + t[i]], dp[i + 1]);
        }

        System.out.println(dp[0]);
    }
}
