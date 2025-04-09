import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] nums = new int[N+1];
        for (int i = 1; i <= N; i++)
            nums[i] = sc.nextInt();

        SegmentTree st = new SegmentTree(nums);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            int min = st.queryMin(start, end);
            int max = st.queryMax(start, end);
            sb.append(min + " " + max + "\n");
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] minTree;
    int[] maxTree;
    int[] arr;
    int n;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        n = arr.length - 1;

        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        initMin(1, 1, n);
        initMax(1, 1, n);
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

    public int queryMin(int left, int right) {
        return recursiveQueryMin(1, 1, n, left, right);
    }

    public int queryMax(int left, int right) {
        return recursiveQueryMax(1, 1, n, left, right);
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