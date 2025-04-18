import java.util.*;

class Main {
    static final int EMPTY = 0, WALL = 1;
    static final int[] di = {0,0,-1,1};
    static final int[] dj = {-1,1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] maze = new int[n+1][m+1];

        sc.nextLine();
        for (int i = 1; i<= n; i++) {
            char[] input = sc.nextLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                maze[i][j] = input[j-1] - '0';
            }
        }

        System.out.println(solve(maze, n, m));
        sc.close();
    }

    static int solve(int[][] maze, int n, int m) {
        int[][] dist = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                dist[i][j] = Integer.MAX_VALUE;
        }

        Deque<Pos> deq = new ArrayDeque<>();

        deq.add(new Pos(1,1));
        dist[1][1] = 0;
        while (!deq.isEmpty()) {
            Pos cur = deq.poll();
            if (cur.i == n && cur.j == m)
                return dist[cur.i][cur.j];
            
            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (ni < 1 || nj < 1 || ni > n || nj > m) continue;

                int prvDist = dist[cur.i][cur.j];
                if (dist[ni][nj] <= prvDist + maze[ni][nj])
                    continue;

                dist[ni][nj] = prvDist + maze[ni][nj];

                if (maze[ni][nj] == EMPTY)
                    deq.addFirst(new Pos(ni, nj));
                else
                    deq.addLast(new Pos(ni, nj));
            }
        }

        return -1;
    }
}

class Pos {
    int i, j;

    public Pos(int i, int j) {
        this.i = i;
        this.j = j;
    }
}