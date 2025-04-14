import java.util.*;

public class Main {
    static final int EMPTY = 0, HOUSE = 1, CHK = 2;

    static int n;
    static int m;

    static int[][] map;
    static List<Pos> houseList = new ArrayList<>();
    static List<Pos> chickenList = new ArrayList<>();

    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == HOUSE)
                    houseList.add(new Pos(i, j));
                else if (map[i][j] == CHK)
                    chickenList.add(new Pos(i, j));
            }
        }

        backtrack(0, new ArrayList<>());

        System.out.println(minDistance);
        sc.close();
    }

    static void backtrack(int start, List<Pos> selected) {
        if (selected.size() == m) {
            minDistance = Math.min(minDistance, getAllDistance(selected));
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selected.add(chickenList.get(i));
            backtrack(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static int getAllDistance(List<Pos> selected) {
        int totalDist = 0;

        for (Pos house: houseList) {
            int dist = Integer.MAX_VALUE;
            for (Pos chicken: selected) {
                dist = Math.min(dist, getDistance(chicken, house));
                if (dist == 1)
                    break;
            }

            totalDist += dist;
        }

        return totalDist;
    }

    static int getDistance(Pos p1, Pos p2) {
        return Math.abs(p1.i - p2.i) + Math.abs(p1.j - p2.j);
    }
}

class Pos {
    int i, j;

    public Pos(int i, int j) {
        this.i = i;
        this.j = j;
    }
}