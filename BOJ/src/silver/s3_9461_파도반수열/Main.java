package silver.s3_9461_파도반수열;

import java.util.Scanner;

public class Main {
    private static final int MAX = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        long[] dp = new long[MAX + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= MAX; i++)
            dp[i] = dp[i - 2] + dp[i - 3];

        while (tc-- > 0) {
            int n = sc.nextInt();

            System.out.println(dp[n]);
        }
    }
}
