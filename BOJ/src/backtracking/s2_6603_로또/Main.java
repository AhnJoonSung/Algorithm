package backtracking.s2_6603_로또;

import java.util.Scanner;

public class Main {
    public static final int LOTTO_LEN = 6;

    public static int k;
    public static int[] s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            k = sc.nextInt();
            if (k == 0)
                break;
            s = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = sc.nextInt();
            }
            int[] arr = new int[LOTTO_LEN];
            recursive(arr, 0, 0);
            System.out.println();
        }
    }

    public static void recursive(int[] arr, int idx, int offset) {
        if (idx == LOTTO_LEN) {
            print(arr);
            return ;
        }
        for (int i = offset; i < k; i++) {
            arr[idx] = s[i];
            recursive(arr, idx + 1, i + 1);
        }
    }

    public static void print(int[] arr) {
        for (int n : arr)
            System.out.print(n + " ");
        System.out.println();
    }
}
