import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        Magician shark = new Magician();
        List<Cloud> clouds = shark.wishRain(map);
        for (int i = 0; i < m; i++) {
            int dir = sc.nextInt();
            int dist = sc.nextInt();

            for (Cloud cloud: clouds)
                cloud.move(dir, dist, map);

            shark.waterCopyBug(map, clouds);
            
            boolean[][] onCloud = getOnCloud(clouds, n);
            clouds = generateClouds(map, onCloud);
        }

        System.out.println(getTotalWater(map));
    }

    static int getTotalWater(int[][] map) {
        int n = map.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }

        return sum;
    }

    static boolean[][] getOnCloud(List<Cloud> clouds, int n) {
        boolean[][] onCloud = new boolean[n][n];

        for (Cloud cloud: clouds) {
            onCloud[cloud.i][cloud.j] = true;
        }

        return onCloud;
    }

    static List<Cloud> generateClouds(int[][] map, boolean[][] onCloud) {
        int n = map.length;

        List<Cloud> clouds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2 && !onCloud[i][j]) {
                    clouds.add(new Cloud(i, j));
                    map[i][j] -= 2;
                }
            }
        }

        return clouds;
    }
}

class Magician {
    public List<Cloud> wishRain(int[][] map) {
        int n = map.length;

        List<Cloud> clouds = new ArrayList<>();
        clouds.add(new Cloud(n-1, 0));
        clouds.add(new Cloud(n-1, 1));
        clouds.add(new Cloud(n-2, 0));
        clouds.add(new Cloud(n-2, 1));

        return clouds;
    }

    public void waterCopyBug(int[][] map, List<Cloud> clouds) {
        for (Cloud cloud: clouds) {
            cloud.waterCopyBug(map);
        }
    }
}

class Cloud {
    static final int[] di = {0,0,-1,-1,-1,0,1,1,1};
    static final int[] dj = {0,-1,-1,0,1,1,1,0,-1};
    static final int[] diagI = {-1,-1,1,1};
    static final int[] diagJ = {-1,1,-1,1};

    int i, j;

    public Cloud (int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void move(int dir, int dist, int[][] map) {
        int n = map.length;
        int ni = (n + (this.i + di[dir] * dist) % n) % n;
        int nj = (n + (this.j + dj[dir] * dist) % n) % n;

        map[ni][nj]++;

        this.i = ni;
        this.j = nj;
    }

    public void waterCopyBug(int[][] map) {
        int n = map.length;

        for (int dir = 0; dir < 4; dir++) {
            int ni = i + diagI[dir];
            int nj = j + diagJ[dir];

            if (ni < 0 || nj < 0) continue;
            if (ni >= n || nj >= n) continue;

            if (map[ni][nj] > 0)
                map[i][j]++;
        }
    }
}