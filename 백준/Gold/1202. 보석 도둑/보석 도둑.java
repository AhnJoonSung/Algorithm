import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Dia[] dias = new Dia[N];
        for (int i = 0; i < N; i++) {
            dias[i] = new Dia(sc.nextInt(), sc.nextInt());
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++)
            bags[i] = sc.nextInt();

        Arrays.sort(dias);
        Arrays.sort(bags);
        
        long totalPrice = 0;
        boolean[] isSpend = new boolean[K];
        int[] parents = new int[K];
        for (int i = 0; i < K; i++)
            parents[i] = i;
        
        for (int i = 0; i < N; i++) {
            Dia dia = dias[i];
            int upb = upperbound(bags, dia.weight);
            if (upb == bags.length) continue;
            int idx = find(upb, parents);

            if (isSpend[idx]) continue;

            isSpend[idx] = true;
            if (idx < bags.length - 1)
                union(idx, idx+1, parents);

            totalPrice += dia.price;
        }

        System.out.println(totalPrice);

        sc.close();
    }

    static int find(int x, int[] parents) {
        if (x != parents[x])
            parents[x] = find(parents[x], parents);

        return parents[x];
    }

    static void union(int x, int y, int[] parents) {
        int root_x = find(x, parents);
        int root_y = find(y, parents);

        if (root_x != root_y) {
            parents[root_x] = root_y;
        }
    }

    static int upperbound(int[] bags, int target) {
        int left = 0, right = bags.length - 1;
        int result = bags.length;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (bags[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}

class Dia implements Comparable<Dia> {
    int weight, price;

    public Dia(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Dia other) {
        return other.price - this.price;
    }
}