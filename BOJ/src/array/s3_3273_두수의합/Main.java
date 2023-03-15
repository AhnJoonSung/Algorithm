package array.s3_3273_두수의합;

import java.util.Scanner;

public class Main {
    static int MAX = 1_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        boolean[] check = new boolean[MAX + 1];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            check[nums[i]] = true;
        }

        int x = sc.nextInt();
        int ans = 0;
        int target;
        for (int i = 0; i < n; i++) {
            target = x - nums[i];
            if (target < 0 || target > MAX)
                continue;
            if (check[nums[i]] && check[target])
                ans++;
        }
        System.out.println(ans / 2);
    }
}
