package silver.s3_15650_N과M2;

import java.util.Scanner;

public class Main {
    public static int n;
    public static int m;
    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];

        permutation(1, 0);
        System.out.print(sb);
    }

    public static void permutation(int start, int depth) {
        if (depth == m) {
            for (int val : arr)
                sb.append(val).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            permutation(i + 1, depth + 1);
        }
    }
}
