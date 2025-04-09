import java.util.*;

public class Main {
    static int n, l;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (canPass(map[i]))    
                cnt++;
        }

        for (int j = 0; j < n; j++) {
            int[] col = new int[n];
            for (int i = 0; i < n; i++) {
                col[i] = map[i][j];
            }
            if (canPass(col))
                cnt++;
        }

        System.out.println(cnt);
    }

    static boolean canPass(int[] road) {
        boolean[] visited = new boolean[n];

        for (int i = 1; i < n; i++) {
            if (Math.abs(road[i] - road[i - 1]) > 1)
                return false;
            
            if (road[i - 1] < road[i]) {
                if (i < l)
                    return false;
                for (int bridge = i - l; bridge < i; bridge++) {
                    if (road[bridge] != road[i - l] || visited[bridge])
                        return false;
                    visited[bridge] = true;
                }
            } else if (road[i - 1] > road[i]) {
                if (n - i < l)
                    return false;
                for (int bridge = i; bridge < i + l; bridge++) {
                    if (road[bridge] != road[i] || visited[bridge])
                        return false;
                    visited[bridge] = true;
                }
            }
        }
        return true;
    }
}
