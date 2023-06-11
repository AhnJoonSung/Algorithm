package silver.s2_2805_나무자르기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        int[] trees = new int[n];

        for (int i = 0; i < n; i++) {
            trees[i] = sc.nextInt();
        }

        Arrays.sort(trees);
        System.out.println(binarySearch(trees));
    }

    public static int binarySearch(int[] arr) {
        int max = 0;
        int left = 0;
        int right = arr[arr.length - 1];

        while (left <= right) {
            int height = (left + right) / 2;
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if (arr[i] > height) {
                    sum += arr[i] - height;
                }
            }

            if (sum < m) {
                right = height - 1;
            } else {
                left = height + 1;
                max = height;
            }
        }

        return  max;
    }
}
