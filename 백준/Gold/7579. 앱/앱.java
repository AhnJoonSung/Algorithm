import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        
        App[] apps = new App[n];
        for (int i = 0; i < n; i++) {
            apps[i] = new App();
            apps[i].memory = sc.nextInt();
        }

        for (int i = 0; i < n; i++)
            apps[i].cost = sc.nextInt();

        int max = 100 * n;
        int[] dp = new int[max+1];

        for (int i = 0; i < n; i++) {
            App app = apps[i];
            for (int j = max; j >= app.cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - app.cost] + app.memory);
            }
        }

        System.out.println(getMinCost(dp, m));
        sc.close();
    }

    static int getMinCost(int[] dp, int need) {
        int minCost;
        for (int cost = 0; cost < dp.length; cost++) {
            if (dp[cost] >= need)
                return cost;
        }
        return -1;
    }
}

class App {
    int memory, cost;

    public App(){}

    public App (int memory, int cost) {
        this.memory = memory;
        this.cost = cost;
    }
}