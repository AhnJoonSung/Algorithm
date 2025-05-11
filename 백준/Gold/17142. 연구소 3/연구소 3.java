import java.util.*;

public class Main {
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int IMPOSSIBLE = -1;
    static final int MAX = Integer.MAX_VALUE;

    static int N, M;
    static int[][] lab;
    static List<Virus> virusList = new ArrayList<>();
    static int emptyCount = 0;
    static int result;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        lab = new int[N][N];
        result = MAX;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lab[i][j] = sc.nextInt();
                if (lab[i][j] == VIRUS)
                    virusList.add(new Virus(i, j, 0));
                else if (lab[i][j] == EMPTY)
                    emptyCount++;
            }
        }

        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        comb(new ArrayList<>(), 0);

        if (result == MAX)
            System.out.println(IMPOSSIBLE);
        else
            System.out.println(result);
    }

    static void comb(List<Virus> selected, int st) {
        if (selected.size() == M) {
            int curTime = bfs(selected);
            result = Math.min(result, curTime);
            return;
        }

        for (int i = st; i < virusList.size(); i++) {
            selected.add(virusList.get(i));
            comb(selected, i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    static int bfs(List<Virus> initViruses) {
        int remain = emptyCount;
        Queue<Virus> viruses = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (Virus v: initViruses) {
            viruses.add(v);
            visited[v.i][v.j] = true;
        }

        while (!viruses.isEmpty()) {
            Virus cur = viruses.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if (visited[ni][nj])
                    continue;
                if (lab[ni][nj] == WALL)
                    continue;

                if (lab[ni][nj] == EMPTY)
                    remain--;
                if (remain == 0)
                    return cur.time + 1;

                visited[ni][nj] = true;
                viruses.add(new Virus(ni, nj, cur.time + 1));
            }
        }

        return MAX;
    }
}

class Virus {
    int i, j, time;

    Virus(int i, int j, int time) {
        this.i = i;
        this.j = j;
        this.time = time;
    }
}
