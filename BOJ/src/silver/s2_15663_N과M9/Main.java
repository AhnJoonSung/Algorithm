package silver.s2_15663_N과M9;

import java.util.Scanner;

public class Main {
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        num = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = sc.nextInt();
    }
}
