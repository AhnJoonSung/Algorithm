import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        SegmentTree minTree = new SegmentTree(nums, n);
        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            int min = minTree.queryMin(left, right);
            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] arr;
    int[] minTree;
    int n;

    public SegmentTree(int[] arr, int n) {
        this.arr = arr;
        this.n = n;
        minTree = new int[4*n];
        initMin(1, 1, n);
    }

    private int initMin(int node, int start, int end) {
        if (start == end)
            return minTree[node] = arr[start];

        int mid = (start + end) / 2;

        minTree[node] = Math.min(
            initMin(node*2, start, mid),
            initMin(node*2 + 1, mid + 1, end)
        );

        return minTree[node];
    }

    public int queryMin(int left, int right) {
        return recursiveQueryMin(1, 1, n, left, right);
    }

    private int recursiveQueryMin(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];

        int mid = (start + end) / 2;
        return Math.min(
            recursiveQueryMin(node * 2, start, mid, left, right),
            recursiveQueryMin(node * 2 + 1, mid + 1, end, left, right)
        );
    }  
}