import java.util.*;

public class Main {
    static final int DOOR = 101, WALL = 102, OPENED = -1;
    static final int[] di = {0,0,-1,1};
    static final int[] dj = {-1,1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();

        n = 2*n - 1;

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 1)
                    arr[i][j] = DOOR;
                else
                    arr[i][j] = WALL;
            }
        }
        for (int i = 0; i < n; i+=2) {
            for (int j = 0; j < n; j+=2) {
                arr[i][j] = sc.nextInt();
            }
        }

        int day = 0;
        while (open(arr, n, l, r)) {
            move(arr, n);
            close(arr, n);

            day++;
        }

        System.out.println(day);
    }

    static void close(int[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == OPENED)
                    arr[i][j] = DOOR;
            }
        }
    }

    static void move(int[][] arr, int n) {
        boolean[][]visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == WALL || arr[i][j] == DOOR) continue;

                int sum = 0;
                int cnt = 0;
                Pos pos = new Pos(i, j);
                List<Pos> friends = new ArrayList<>();
                Queue<Pos> q = new LinkedList<>();
                q.add(pos);
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Pos cur = q.poll();
                    if (arr[cur.i][cur.j] != OPENED) {
                        friends.add(cur);
                        sum += arr[cur.i][cur.j];
                        cnt++;
                    }

                    for (int dir = 0; dir < 4; dir++) {
                        int ni = cur.i + di[dir];
                        int nj = cur.j + dj[dir];

                        if (isOut(ni, nj, n)) continue;
                        if (visited[ni][nj]) continue;
                        if (arr[ni][nj] == WALL || arr[ni][nj] == DOOR) continue;

                        visited[ni][nj] = true;
                        q.add(new Pos(ni, nj));
                    }
                }

                int avg = sum / cnt;
                for (Pos friend: friends) {
                    arr[friend.i][friend.j] = avg;
                }
            }
        }

    }

    static boolean open(int[][] arr, int n, int l, int r) {
        boolean opened = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != DOOR) continue;
                List<Integer> people = new ArrayList<>();
                for (int dir = 0; dir < 4; dir++) {
                    int ni = i + di[dir];
                    int nj = j + dj[dir];

                    if (isOut(ni, nj, n)) continue;
                    if (arr[ni][nj] == WALL) continue;

                    people.add(arr[ni][nj]);
                }

                int dist = Math.abs(people.get(0) - people.get(1));
                if (l <= dist && dist <= r) {
                    arr[i][j] = OPENED;
                    opened = true;
                }
            }
        }

        return opened;
    }

    static boolean isOut(int i, int j, int n) {
        if (i < 0 || i >= n) return true;
        if (j < 0 || j >= n) return true;
        return false;
    }
}

class Pos {
    int i, j;

    public Pos(int i , int j) {
        this.i = i;
        this.j = j;
    }
}

