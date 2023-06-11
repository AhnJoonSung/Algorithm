package silver.s3_피보나치함수;

import java.util.Scanner;

public class Main {
    private static final int MAX = 41;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[] dp0 = new int[MAX];
        int[] dp1 = new int[MAX];
        dp0[0] = 1;
        dp1[1] = 1;
        for (int i = 2; i < MAX; i++) {
            dp0[i] = dp0[i - 2] + dp0[i - 1];
            dp1[i] = dp1[i - 2] + dp1[i - 1];
        }

        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(dp0[n] + " " + dp1[n]);
        }
    }
}
