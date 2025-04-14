import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        List<Integer> dp = new ArrayList(); // 길이가 len인 부분수열들중 최소 끝 값
        dp.add(nums[0]);

        for (int i = 1; i < n; i++) {
            int idx = binarySearch(dp, nums[i]);
            if (idx == dp.size())
                dp.add(nums[i]);
            else
                dp.set(idx, nums[i]);
        }

        System.out.println(dp.size());
        
        sc.close();
    }

    static int binarySearch(List<Integer> dp, int target) {
        int left = 0, right = dp.size() - 1;
        int result = dp.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            if (dp.get(mid) >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}