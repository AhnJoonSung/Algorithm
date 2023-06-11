package silver.s3_11727_2xn타일링2;

import java.util.Scanner;

public class Main {
    private static final int MAX = 1_000;
    private static final int DIV = 10_007;

    public static int[] dp = new int[MAX + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % DIV;
        }
        System.out.println(dp[n]);
    }
}
