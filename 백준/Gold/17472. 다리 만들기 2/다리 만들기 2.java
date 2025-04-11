import java.util.*;

public class Main {
    static final int SEA = 0, GND = 1;
    static final int FIRST_LAND = 2;
    static final int[] di = {0, 0, 1, -1};
    static final int[] dj = {-1, 1, 0, 0};

    static int n, m;
    static int landNum;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        landNum = FIRST_LAND;
        List<Pos> landmarks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == GND) {
                    Pos pos = new Pos(i, j);
                    landmarks.add(pos);
                    setIsland(pos, landNum++);
                }
            }
        }

        List<Bridge> bridges = new ArrayList<>();
        for (Pos landmark: landmarks) {
            makeBridges(bridges, landmark);
        }


        System.out.println(getMinLen(bridges));
        sc.close();
    }

    static int getMinLen(List<Bridge> bridges) {
        List<Bridge> mst = new ArrayList<>();

        Collections.sort(bridges);

        int[] parents = new int[landNum];
        for (int i = FIRST_LAND; i < landNum; i++)
            parents[i] = i;

        for (Bridge bridge: bridges) {
            if (find(bridge.from, parents) != find(bridge.to, parents)) {
                union(bridge.from, bridge.to, parents);
                mst.add(bridge);
                if (mst.size() == (landNum - 1 - FIRST_LAND))
                    return getTotalLen(mst);
            }
        }

        return -1;
    }

    static int getTotalLen(List<Bridge> bridges) {
        int totalLen = 0;
        for (Bridge bridge: bridges)
            totalLen += bridge.len;

        return totalLen;
    }

    static void makeBridges(List<Bridge> bridges, Pos landmark) {
        int from = map[landmark.i][landmark.j];
        
        List<BridgeNode> outline = getOutline(landmark);
        for (BridgeNode pos: outline) {
            for (int dir = 0; dir < 4; dir++) {
                Bridge bridge = makeBridge(pos, from);
                if (bridge != null)
                    bridges.add(bridge);
            }
        }
    }

    static Bridge makeBridge(BridgeNode start, int from) {
        int len = 1;
        int ni = start.i, nj = start.j;
        int dir = start.dir;
        while (true) {
            ni += di[dir];
            nj += dj[dir];

            if (isOut(ni, nj)) break;
            if (map[ni][nj] == from) break;

            if (map[ni][nj] != SEA) {
                if (len == 1) break;

                int to = map[ni][nj];
                return new Bridge(from, to, len);
            }

            len++;
        }

        return null;
    }

    static List<BridgeNode> getOutline(Pos start) {
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        visited[start.i][start.j] = true;

        List<BridgeNode> outline = new ArrayList<>();
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            
            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (isOut(ni, nj)) continue;
                if (visited[ni][nj]) continue;

                visited[ni][nj] = true;

                if (map[ni][nj] == SEA)
                    outline.add(new BridgeNode(ni, nj, dir));
                else
                    q.add(new Pos(ni, nj));
            }
        }

        return outline;
    }

    static void setIsland(Pos start, int num) {
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        visited[start.i][start.j] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            map[cur.i][cur.j] = num;
            
            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (isOut(ni, nj)) continue;
                if (visited[ni][nj]) continue;
                if (map[ni][nj] == SEA) continue;

                visited[ni][nj] = true;
                q.add(new Pos(ni, nj));
            }
        }
    }

    static int find(int x, int[] parents) {
        if (x != parents[x])
            parents[x] = find(parents[x], parents);

        return parents[x];
    }

    static void union(int x, int y, int[] parents) {
        int rootX = find(x, parents);
        int rootY = find(y, parents);

        if (rootX != rootY)
            parents[rootY] = rootX;
    }

    static boolean isOut(int i, int j) {
        if (i < 0 || i >= n)
            return true;
        if (j < 0 || j >= m)
            return true;
        return false;
    }
}

class Bridge implements Comparable<Bridge> {
    int from, to, len;

    public Bridge(int from, int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }

    @Override
    public int compareTo(Bridge other) {
        return this.len - other.len;
    }
}

class BridgeNode {
    int i, j, dir;

    public BridgeNode(int i, int j, int dir) {
        this.i = i;
        this.j = j;
        this.dir = dir;
    }
}

class Pos {
    int i, j;

    public Pos(int i, int j) {
        this.i = i;
        this.j = j;
    }
}