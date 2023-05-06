package dynamic_programming.s1_12852_1로만들기2;

import java.util.Scanner;

public class Main {
    public static final int MAX = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[MAX + 1];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        int[] route = new int[MAX + 1];
        route[1] = 0;
        route[2] = 1;
        route[3] = 1;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            route[i] = i - 1;
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                route[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                route[i] = i / 2;
            }
        }
        System.out.println(dp[n]);
        while (n > 0) {
            System.out.print(n + " ");
            n = route[n];
        }
    }
}
