package silver.s2_11055_가장큰증가하는부분수열;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] seq = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
            dp[i] = seq[i];
        }

        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++)
                if (seq[j] < seq[i])
                    dp[i] = Math.max(dp[i], dp[j] + seq[i]);
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
