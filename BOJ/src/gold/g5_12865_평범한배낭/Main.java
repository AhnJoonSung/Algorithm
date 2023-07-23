package gold.g5_12865_평범한배낭;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Thing[] things = new Thing[n + 1];
        for (int i = 1; i <= n; i++) {
            things[i] = new Thing(sc.nextInt(), sc.nextInt());
        }

        int[] dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = k; j - things[i].w >= 0; j--) {
                dp[j] = Math.max(dp[j], things[i].v + dp[j - things[i].w]);
            }
        }
        System.out.println(dp[k]);
    }
}

class Thing {
    int w, v;

    public Thing(int w, int v) {
        this.w = w;
        this.v = v;
    }
}