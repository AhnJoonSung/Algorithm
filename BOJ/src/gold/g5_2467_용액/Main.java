package gold.g5_2467_용액;

import java.util.Scanner;

public class Main {
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int ans1 = arr[0];
        int ans2 = arr[n - 1];

        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (Math.abs(ans1 + ans2) > Math.abs(arr[left] + arr[right])) {
                ans1 = arr[left];
                ans2 = arr[right];
            }
            if (Math.abs(arr[left + 1] + arr[right]) < Math.abs(arr[left] + arr[right - 1])) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(ans1 + " " + ans2);
    }
}
