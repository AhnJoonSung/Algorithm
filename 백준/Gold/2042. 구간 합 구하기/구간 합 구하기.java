import java.io.*;
import java.util.*;

public class Main {
    static final int CHANGE = 1, SUM = 2;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        long[] nums = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = sc.nextLong();
        }

        long[] accum = new long[N + 1];
        accum[0] = 0;
        for (int i = 1; i <= N; i++) {
            accum[i] = accum[i-1] + nums[i];
        }

        StringBuilder sb = new StringBuilder();
        List<long[]> mod = new ArrayList<>();
        for (int i = 0; i < M + K; i++) {
            int cmd = sc.nextInt();
            int targetIdx = sc.nextInt();
            long to = sc.nextLong();

            if (cmd == CHANGE) {
                mod.add(new long[]{targetIdx, to - nums[targetIdx]});
                nums[targetIdx] = to;
            }
            else if (cmd == SUM) {
                long sum = sum(accum, targetIdx, (int)to, mod);
                sb.append(sum).append("\n");
            }
        }

        System.out.print(sb);
    }

    static long sum(long[] accum, int start, int end, List<long[]> mod) {
        long result = accum[end] - accum[start - 1];

        for (long[] m: mod) {
            int idx = (int)m[0];
            long amount = m[1];

            if (idx < start || idx > end)
                continue;

            result += amount;
        }

        return result;
    }

}