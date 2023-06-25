package silver.s1_1697_숨바꼭질;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 100_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(n, 0));

        System.out.println(bfs(q, k));
    }

    private static int bfs(Queue<Coordinate> q, int k) {
        boolean[] visited = new boolean[SIZE + 1];

        while (!q.isEmpty()) {
            Coordinate cur = q.poll();
            if (cur.coor == k)
                return cur.time;

            addQ(q, visited, cur.coor - 1, cur.time);
            addQ(q, visited, cur.coor + 1, cur.time);
            addQ(q, visited, cur.coor * 2, cur.time);
        }
        return -1;
    }

    private static void addQ(Queue<Coordinate> q, boolean[] visited, int coor, int time) {
        if (isValid(coor) && !visited[coor]) {
            q.add(new Coordinate(coor, time + 1));
            visited[coor] = true;
        }
    }

    private static boolean isValid(int coor) {
        if (coor < 0)
            return false;
        if (coor > SIZE)
            return false;
        return true;
    }
}

class Coordinate {
    int coor, time;

    public Coordinate(int coor, int time) {
        this.coor = coor;
        this.time = time;
    }
}
