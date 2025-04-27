import java.util.*;

public class Main {
    static final int[] di = {-1,1,0,0};
    static final int[] dj = {0,0,-1,1};
    static final int AIR = -1;

    static int r, c;
    static List<Integer> airPos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        int t = sc.nextInt();

        int[][] map = new int[r][c];
        airPos = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == AIR)
                    airPos.add(i);
            }
        }

        Collections.sort(airPos);

        while (t-- > 0) {
            map = spreaded(map);

            map = airCleaning(map);
        }

        System.out.println(countDust(map));
    }

    static int countDust(int[][] map) {
        int count = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == AIR) continue;

                count += map[i][j];
            }
        }

        return count;
    }

    static int[][] airCleaning(int[][] map) {
        int[][] newMap = new int[r][c];

        int top = airPos.get(0);
        int bot = airPos.get(1);

        for (int i = 0; i < r; i++) {
            for (int j = 0 ; j < c; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for (int i = top - 1; i > 0; i--) {
            newMap[i][0] = map[i - 1][0];
        }

        for (int i = bot + 1; i < r - 1; i++) {
            newMap[i][0] = map[i + 1][0];
        }

        for (int j = 0; j < c-1; j++) {
            newMap[0][j] = map[0][j+1];
            newMap[r-1][j] = map[r-1][j+1];
        }

        for (int i = top - 1; i >= 0; i--) {
            newMap[i][c-1] = map[i + 1][c-1];
        }

        for (int i = bot + 1; i <= r - 1; i++) {
            newMap[i][c-1] = map[i - 1][c-1];
        }

        newMap[top][1] = newMap[bot][1] = 0;
        for (int j = 2; j < c; j++) {
            newMap[top][j] = map[top][j-1];
            newMap[bot][j] = map[bot][j-1];
        }

        return newMap;
    }

    static int[][] spreaded(int[][] map) {
        int[][] newMap = new int[r][c];

        newMap[airPos.get(0)][0] = AIR;
        newMap[airPos.get(1)][0] = AIR;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] <= 0)
                    continue;

                int amount = map[i][j] / 5;
                int remain = map[i][j];

                for (int dir = 0; dir < 4; dir++) {
                    int ni = i + di[dir];
                    int nj = j + dj[dir];

                    if (ni < 0 || nj < 0 || ni >= r || nj >= c)
                        continue;

                    if (map[ni][nj] == AIR)
                        continue;

                    newMap[ni][nj] += amount;
                    remain -= amount;
                }

                newMap[i][j] += remain;
            }
        }

        return newMap;
    }
}