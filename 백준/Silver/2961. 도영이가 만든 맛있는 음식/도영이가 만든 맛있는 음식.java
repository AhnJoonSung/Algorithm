import java.util.*;

public class Main {
    static int min;
    static boolean[] visited;
    static List<Food> foods;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        foods = new ArrayList<>();
        for (int i = 0; i < n; i++)
            foods.add(new Food(sc.nextInt(), sc.nextInt()));
            
        min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            backtrack(0, i, new ArrayList<>(), new boolean[n]);
        }

        System.out.println(min);

        sc.close();
    }

    static void backtrack(int depth, int n, List<Food> picked, boolean[] visited) {
        if (depth == n) {
            min = Math.min(min, getScore(picked));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            picked.add(foods.get(i));

            backtrack(depth + 1, n, picked, visited);

            picked.remove(picked.size() - 1);
            visited[i] = false;
        }
    }

    static int getScore(List<Food> picked) {
        int totalSour = 1, totalBitter = 0;

        for (Food food: picked) {
            totalSour *= food.sour;
            totalBitter += food.bitter;
        }

        return Math.abs(totalBitter - totalSour);
    }

}

class Food {
    int sour, bitter;

    public Food(int sour, int bitter) {
        this.sour = sour;
        this.bitter = bitter;
    }
}