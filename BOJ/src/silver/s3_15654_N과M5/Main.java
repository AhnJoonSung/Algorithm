package silver.s3_15654_N과M5;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int n, m;
    public static StringBuilder sb = new StringBuilder();
    public static int[] arr;
    public static int[] num;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];
        num = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++)
            num[i] = sc.nextInt();
        Arrays.sort(num);

        comb(0);
        System.out.println(sb);
    }

    public static void comb(int depth) {
        if (depth == m) {
            for (int val : arr)
                sb.append(val).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            arr[depth] = num[i];
            comb(depth + 1);
            visited[i] = false;
        }
    }
}
