package dynamic_programming.s3_11659_구간합구하기4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] numbers = new int[n];
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
            dp[i + 1] = numbers[i] + dp[i];
        }

        while (m-- > 0) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            System.out.println(dp[j] - dp[i - 1]);
        }
    }
}
