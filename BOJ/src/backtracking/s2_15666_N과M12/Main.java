package backtracking.s2_15666_N과M12;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int n, m;
    public static int[] pool;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pool = new int[n];
        for (int i = 0; i < n; i++) {
            pool[i] = sc.nextInt();
        }
        Arrays.sort(pool);
        int[] arr = new int[m];
        recursive(arr, 0, 0);
    }

    public static void recursive(int[] arr, int idx, int st) {
        if (idx == m) {
            print(arr);
            return ;
        }
        for (int i = st; i < n; i++) {
            if (i > 0 && (pool[i - 1] == pool[i]))
                continue;
            arr[idx] = pool[i];
            recursive(arr, idx + 1, i);
        }
    }

    public static void print(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
