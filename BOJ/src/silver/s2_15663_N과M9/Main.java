package silver.s2_15663_N과M9;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] nums;
    static int[] ans;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        nums = new int[n];
        ans = new int[m];
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        Arrays.sort(nums);
        comb(0);
        System.out.print(sb);
    }

    public static void comb(int depth) {
        if (depth == m) {
            for (int v : ans)
                sb.append(v).append(" ");
            sb.append("\n");
            return;
        }

        int lastNum = -1;
        for (int i = 0; i < n; i++) {
            if (visited[i] || lastNum == nums[i])
                continue;
            lastNum = nums[i];
            visited[i] = true;
            ans[depth] = nums[i];
            comb(depth + 1);
            visited[i] = false;
        }
    }
}
