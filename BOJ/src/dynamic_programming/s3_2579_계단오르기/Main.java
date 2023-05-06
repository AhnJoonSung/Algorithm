package dynamic_programming.s3_2579_계단오르기;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        if (n == 1) {
            System.out.println(arr[0]);
            return ;
        }

        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;
        dp[1][0] = arr[1];
        dp[1][1] = arr[0] + arr[1];
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i];
            dp[i][1] = dp[i - 1][0] + arr[i];
        }
        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }
}
