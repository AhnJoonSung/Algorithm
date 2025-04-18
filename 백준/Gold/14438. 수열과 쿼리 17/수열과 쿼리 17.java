import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] nums = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        SegmentTree tree = new SegmentTree(nums, n);
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int arg1 = Integer.parseInt(st.nextToken());
            int arg2 = Integer.parseInt(st.nextToken());

            if (cmd.startsWith("1")) {
                tree.update(arg1, arg2);
            } else {
                int min = tree.queryMin(arg1, arg2);
                sb.append(min).append("\n");
            }
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] minTree;
    int[] maxTree;
    int[] arr;
    int n;

    public SegmentTree(int[] arr, int n) {
        this.arr = arr;
        this.n = n;

        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        initMin(1, 1, n);
        initMax(1, 1, n);
    }

    public int queryMin(int left, int right) {
        return recursiveQueryMin(1, 1, n, left, right);
    }

    public int queryMax(int left, int right) {
        return recursiveQueryMax(1, 1, n, left, right);
    }

    public void update(int idx, int newVal) {
        update(1, 1, n, idx, newVal);
    }

    private void update(int node, int start, int end, int idx, int newVal) {
        if (idx < start || idx > end)
            return;

        if (start == end) {
            minTree[node] = newVal;
            maxTree[node] = newVal;
        } else {
            int mid = (start + end) /  2;
            update(node*2, start, mid, idx, newVal);
            update(node*2 + 1, mid + 1, end, idx, newVal);

            minTree[node] = Math.min(minTree[node*2], minTree[node*2 + 1]);
            maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2 + 1]);
        }
    }

    private int initMin(int node, int start, int end) {
        if (start == end)
            return minTree[node] = arr[start];

        int mid = (start + end) / 2;
        minTree[node] = Math.min(
            initMin(node * 2, start, mid),
            initMin(node * 2 + 1, mid + 1, end)
        );

        return minTree[node];
    }

    private int initMax(int node, int start, int end) {
        if (start == end)
            return maxTree[node] = arr[start];

        int mid = (start + end) / 2;
        maxTree[node] = Math.max(
            initMax(node * 2, start, mid),
            initMax(node *2 + 1, mid + 1, end)
        );
        
        return maxTree[node];
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

    private int recursiveQueryMax(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[node];

        int mid = (start + end) / 2;
        return Math.max(
            recursiveQueryMax(node * 2, start, mid, left, right),
            recursiveQueryMax(node * 2 + 1, mid + 1, end, left, right)
        );
    }
}