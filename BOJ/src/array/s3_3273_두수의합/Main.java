package array.s3_3273_두수의합;

import java.util.Scanner;

public class Main {
    static int MAX = 1_000_000 + 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cnt = new int[MAX];

        int num;
        for (int i = 0; i < n; i++) {
            num = sc.nextInt();
            cnt[num] = 1;
        }

        int x = sc.nextInt();
        int ans = 0;
        for (int i = 1; i < (x + 1) / 2; i++) {
            if ((cnt[i] + cnt[x - i]) == 2)
                ans++;
        }
        System.out.println(ans);
    }
}
