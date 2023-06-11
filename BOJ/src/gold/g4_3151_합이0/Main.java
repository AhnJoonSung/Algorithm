package gold.g4_3151_합이0;

import java.util.Scanner;

public class Main {
    private static final int MAX = 40_000;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        int[] counter = new int[MAX + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += counter[20_000 + arr[i]];
            for (int j = 0; j < i; j++) {
                counter[20_000 + -1 * (arr[i] + arr[j])] += 1;
            }
        }
        System.out.println(ans);
    }
}
