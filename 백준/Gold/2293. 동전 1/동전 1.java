import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 0 ; i < n; i++) {
            int value = sc.nextInt();

            for (int j = value; j <= k; j++) {
                dp[j] = dp[j] + (dp[j - value]);
            }
        }

        System.out.println(dp[k]);
    }
}