import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        boolean[][] oddDp = new boolean[n][n/2 + 1]; //[기준점][거리] == 팰린드롬?
        for (int mid = 0; mid < n; mid++)
            oddDp[mid][0] = true;

        for (int mid = 0; mid < n; mid++) {
            for (int dist = 1; dist <= n/2; dist++) {
                if (mid - dist < 0 || mid + dist >= n) break;

                int left = nums[mid - dist];
                int right = nums[mid + dist];

                if (left != right) break;

                oddDp[mid][dist] = oddDp[mid][dist - 1]? true: false;
            }
        }

        boolean[][] evenDp = new boolean[n][n/2 + 1];
        for (int mid = 0; mid < n; mid++) {
            for (int dist = 1; dist <= n/2; dist++) {
                if (mid - dist + 1 < 0 || mid + dist >= n) break;

                int left = nums[mid - dist + 1];
                int right = nums[mid + dist];

                if (left != right) break;

                evenDp[mid][dist] = (dist == 1)? true : evenDp[mid][dist - 1];
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int mid = (start + end) / 2;
            int dist = end - mid;

            if ((end - start + 1) % 2 == 0)
                sb.append(evenDp[mid][dist]? 1: 0).append("\n");
            else
                sb.append(oddDp[mid][dist]? 1: 0).append("\n");
        }

        System.out.print(sb);
    }
}