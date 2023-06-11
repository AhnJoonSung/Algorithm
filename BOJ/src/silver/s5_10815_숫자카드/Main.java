package silver.s5_10815_숫자카드;

import java.util.Scanner;

public class Main {
    private static final int MAX = 10_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cards = new int[2 * MAX + 1];

        for (int i = 0; i < n; i++) {
            cards[sc.nextInt() + MAX] = 1;
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.print(cards[sc.nextInt() + MAX] + " ");
        }
    }
}
