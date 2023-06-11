package silver.s3_11726_2xN타일링;

import java.util.Scanner;

public class Main {
    public static final int MAX = 1000;
    public static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new int[MAX + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= MAX; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        System.out.println(dp[n]);
    }
}
