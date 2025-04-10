import java.util.*;

public class Main {
    static final int MOD = 1_000_000_000;
    static final int ALL_DIGITS = (1 << 10) - 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long answer = solve(n);

        System.out.println(answer);
    }

    static long solve(int n) {
        if (n < 10) return 0;

        // n번째 자리 + Last로 끝남 + 이 숫자들을 써서
        long[][][] dp = new long[n + 1][10][(1 << 10)];
        
        for (int i = 1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        for (int len = 1; len < n; len++) {
            for (int last = 0; last < 10; last++) {
                for (int used = 1; used < (1 << 10); used++) {
                    long current = dp[len][last][used];
                    if (current == 0)
                        continue;

                    if (last > 0) {
                        int nextUse = used | (1 << last - 1);
                        dp[len+1][last-1][nextUse] += current % MOD;
                    }
                    if (last < 9) {
                        int nextUse = used | (1 << last + 1);
                        dp[len+1][last+1][nextUse] += current % MOD;
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++)
            answer = (answer + dp[n][i][ALL_DIGITS]) % MOD;

        return answer;
    }
}