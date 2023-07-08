package silver.s2_15663_N과M9;

import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] num;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        num = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
            num[i] = sc.nextInt();
        Arrays.sort(nums);

    }

    public static void comb(int start, int depth) {
        if (depth == m) {

        }
    }
}
