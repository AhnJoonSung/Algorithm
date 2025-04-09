import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cards = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int[] parent = new int[M];
        for (int i = 0; i < M; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int enemyCard = Integer.parseInt(st.nextToken());
            
            int upb = upperBound(cards, enemyCard);
            int idx = find(upb, parent);
            int myCard = cards[idx];

            sb.append(myCard).append("\n");

            if (idx != M - 1) {
                union(idx, idx + 1, parent);
            }
        }

        System.out.print(sb);
    }

    static int upperBound(int[] arr, int target) {
        int result = arr.length - 1;
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] > target) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    static int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }

    static void union(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);

        if (x != y) {
            parent[x] = y;
        }
    }
}