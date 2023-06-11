package silver.s3_2193_이친수;

import java.util.Scanner;

public class Main {
    private static final int MAX = 90;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] zero = new long[MAX + 1];
        long[] one = new long[MAX + 1];

        zero[1] = 0;
        one[1] = 1;

        for (int i = 2; i <= n; i++) {
            zero[i] = zero[i - 1] + one[i - 1];
            one[i] = zero[i - 1];
        }

        System.out.println(zero[n] + one[n]);
    }
}
