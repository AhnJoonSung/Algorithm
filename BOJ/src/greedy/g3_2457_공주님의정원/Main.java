package greedy.g3_2457_공주님의정원;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] flowers = new int[n][2];
        for (int i = 0; i < n; i++) {
            flowers[i][0] = sc.nextInt() * 100 + sc.nextInt();
            flowers[i][1] = sc.nextInt() * 100 + sc.nextInt();
        }

        int ans = 0;
        int time = 301;
        while (time < 1201) {
            int next = time;
            for (int i = 0; i < n; i++) {
                if (flowers[i][0] <= time && flowers[i][1] > next)
                    next = flowers[i][1];
            }
            if (next == time) {
                System.out.println(0);
                return;
            }
            ans++;
            time = next;
        }
        System.out.println(ans);
    }
}