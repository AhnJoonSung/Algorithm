import java.util.*;

public class Main {
    static final int N = 101;

    static boolean[][] map = new boolean[N][N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int line = sc.nextInt();
        while (line-- > 0) {
            int j = sc.nextInt();
            int i = sc.nextInt();
            int dir = sc.nextInt();
            int gen = sc.nextInt();

            new Dragon(i, j, dir, gen).draw();
        }

        System.out.println(countSquare());
    }

    static int countSquare() {
        int cnt = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i - 1][j - 1] && map[i - 1][j] && map[i][j - 1] && map[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static class Dragon {
        static final int di[] = {0, -1, 0, 1};
        static final int dj[] = {1, 0, -1, 0};

        int i, j, gen;
        List<Integer> route;

        public Dragon(int i, int j, int dir, int gen) {
            this.i = i;
            this.j = j;
            this.gen = gen;

            route = new ArrayList<>();
            route.add(dir);
        }

        private void draw() {
            for (int i = 0; i < gen; i++) {
                generate();
            }
            
            map[i][j] = true;
            int ni = i, nj = j;
            for (int dir: route) {
                ni += di[dir];
                nj += dj[dir];

                map[ni][nj] = true;
            }
        }

        private void generate() {
            int idx = route.size();
            while (idx-- > 0) {
                int target = route.get(idx);
                target = (target + 1) % 4;
                route.add(target);
            }
        }
    }
}