import java.util.*;

public class Main {
    static final int IMP = 0, INF = 16_000_001;
    static int n;
    static int[][] w, dp;
    static int allVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int totalCases = 1 << n;
        allVisited = totalCases - 1;
        w = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < n; j++) {
                w[i][j] = sc.nextInt();
            }
        }

        //dp[current][visited]: (current, visited) 상태에서 남은 도시들을 방문하고 돌아오는 최소 비용
        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < totalCases; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int current, int visited) {
        if (visited == allVisited) {
            if (w[current][0] == IMP)
                return INF;
            return w[current][0];
        }

        if (dp[current][visited] != -1)
            return dp[current][visited];

        dp[current][visited] = INF;

        for (int next = 0; next < n; next++) {
            if ((visited & (1 << next)) != 0 || w[current][next] == IMP)
                continue;
            
            int nextVisited = visited | (1 << next);
            
            dp[current][visited] = Math.min(dp[current][visited], tsp(next, nextVisited) + w[current][next]);
        }

        return dp[current][visited];
    }
}