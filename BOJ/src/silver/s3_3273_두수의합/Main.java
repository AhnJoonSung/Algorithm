package silver.s3_3273_두수의합;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        Set<Integer> hashset = new HashSet<>();

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            hashset.add(nums[i]);
        }

        int x = sc.nextInt();
        int ans = 0;
        for (int num : nums) {
            if (hashset.contains(num) && hashset.contains(x - num))
                ans++;
        }
        System.out.println(ans / 2);
    }
}
