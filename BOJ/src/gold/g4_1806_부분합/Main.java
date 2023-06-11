package gold.g4_1806_부분합;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int s = sc.nextInt();

        int[] seq = new int[n];

        for (int i = 0; i < n; i++)
            seq[i] = sc.nextInt();

        int end = 0;
        int sum = 0;
        int ans = n + 1;
        for (int st = 0; st < n; st++) {
            while (sum < s && end < n) {
                sum += seq[end];
                end++;
            }
            if (sum < s)
                break;
            ans = Math.min(ans, end - st);
            sum -= seq[st];
        }
        if (ans == n + 1)
            ans = 0;
        System.out.println(ans);
    }
}
