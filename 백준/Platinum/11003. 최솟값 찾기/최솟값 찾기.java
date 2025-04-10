import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N + 1];
        for (int i = 1; i <= N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        SegmentTree tree = new SegmentTree(nums);

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int start = Math.max((i - L + 1), 1);
            if (L == 1)
                min = nums[i];
            else {
                q.add(nums[i]);
                if ((start > 1) && (min == q.poll()))  
                    min = tree.queryMin(start, i);
                else
                    min = Math.min(min, nums[i]);
            }
            sb.append(min).append(" ");
        }

        System.out.println(sb);
    }
}

class SegmentTree {
    int[] minTree;
    int[] arr;
    int n;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        n = arr.length - 1;
        minTree = new int[4 * n];
        initMin(1, 1, n);
    }

    private int initMin(int node, int start, int end) {
        if (start == end) {
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        
        return minTree[node] = Math.min(
            initMin(node * 2, start, mid),
            initMin(node * 2 + 1, mid + 1, end)
        );
    }

    public int queryMin(int left, int right) {
        return recursiveQueryMin(1, 1, n, left, right);
    }

    private int recursiveQueryMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left)
            return Integer.MAX_VALUE;
        if (left <= start && end <= right)
            return minTree[node];

        int mid = (start + end) / 2;

        return Math.min(
            recursiveQueryMin(node * 2, start, mid, left, right),
            recursiveQueryMin(node * 2 + 1, mid + 1, end, left, right)
        );
    }
}