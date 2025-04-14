import java.util.*;

public class Main {
    static int n;
    static int minPrice = Integer.MAX_VALUE;

    static int[][] sales;
    static int[] costs;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        costs = new int[n + 1];
        for (int i = 1; i <= n; i++)
            costs[i] = sc.nextInt();

        sales = new int[n + 1][n + 1]; // i를 샀을 때 j가 할인되는 금액
        for (int i = 1; i <= n; i++) {
            int pi = sc.nextInt();

            for (int j = 1; j <= pi; j++) {
                sales[i][sc.nextInt()] = sc.nextInt(); 
            }
        }

        backtrack(0, new ArrayList<>(), new boolean[n + 1]);

        System.out.println(minPrice);
        sc.close();
    }

    static void backtrack(int depth, List<Integer> seq, boolean[] visited) {
        if (depth == n) {
            int price = getPrice(seq, deepcopy(costs));
            minPrice = Math.min(minPrice, price);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            seq.add(i);
            backtrack(depth + 1, seq, visited);
            seq.remove(seq.size() - 1);
            visited[i] = false;
        }
    }

    static int getPrice(List<Integer> seq, int[] costs) {
        int totalPrice = 0;

        for (int i: seq) {
            totalPrice += costs[i];

            for (int j = 1; j <= n; j++) {
                int newCost = costs[j] - sales[i][j];
                costs[j] = Math.max(1, newCost);
            }
        }

        return totalPrice;
    }

    static int[] deepcopy(int[] arr) {
        int[] copy = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }

        return copy;
    }
}