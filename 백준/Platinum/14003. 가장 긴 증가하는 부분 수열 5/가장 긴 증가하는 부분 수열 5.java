import java.util.*;

public class Main {
    static int n;
    static int[] seq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }

        // 같은 길이면 마지막 원소가 작은 걸 기록
        List<Integer> L = new ArrayList<>();
        // i번째 원소를 마지막 원소로 가지는 부분 수열의 길이를 기록
        int[] P = new int[n];

        for (int i = 0; i < n; i++) {
            int target = seq[i];
            int position = binarySearch(L, target);

            if (position == L.size())
                L.add(target);
            else
                L.set(position, target);
            P[i] = position;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(L.size()).append("\n");

        int[] lis = getLis(L, P);
        for (int i: lis) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int[] getLis(List<Integer> L, int[] P) {
        int[] lis = new int[L.size()];
        int idx = n - 1;
        int k = L.size();

        for (int i = k - 1; i >= 0; i--) {
            while (0 <= idx && P[idx] != i)
                idx--;
            lis[i] = seq[idx--];
        }

        return lis;
    }

    static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        int result = list.size();
        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}