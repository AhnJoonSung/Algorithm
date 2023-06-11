package silver.s2_16401_과자나눠주기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int m, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();

        int[] snacks = new int[n];
        for (int i = 0; i < n; i++) {
            snacks[i] = sc.nextInt();
        }
        Arrays.sort(snacks);
        System.out.println(binarySearch(snacks));
    }

    public static int binarySearch(int[] arr) {
        int left = 1;
        int right = arr[arr.length - 1];
        int max = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                cnt += arr[i] / mid;
            }
            if (cnt < m) {
                right = mid - 1;
            } else {
                max = Math.max(max, mid);
                left = mid + 1;
            }
        }
        return max;
    }
}
