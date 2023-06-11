package gold.g5_2230_수고르기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int MAX = 2_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }

        Arrays.sort(seq);

        int ans = MAX;
        int end = 0;
        for (int i = 0; i < n; i++) {
            while (end < n && seq[end] - seq[i] < m) {
                end++;
            }
            if (end == n)
                break;
            ans = Math.min(ans, seq[end] - seq[i]);
            if (ans == m) {
                break;
            }
        }

        System.out.println(ans);
    }
}
