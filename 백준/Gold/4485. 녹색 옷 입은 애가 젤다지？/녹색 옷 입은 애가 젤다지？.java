import java.util.*;

class Main {
    static final int INF = Integer.MAX_VALUE;
    static final int[] di = {0,0,-1,1};
    static final int[] dj = {-1,1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = 1;
        while (true) {
            int n = sc.nextInt();
            if (n == 0)
                break;
            
            int[][] cave = new int[n][n];
    
            for (int i = 0; i < n; i++) {
                for (int j = 0 ; j < n; j++) {
                    cave[i][j] = sc.nextInt();
                }
            }
            
            System.out.println(display(dijkstra(cave), num++));
        }
        sc.close();
    }

    static String display(int answer, int num) {
        return "Problem " + num + ": " + answer;
    }

    static int dijkstra(int[][] cave) {
        int n = cave.length;
        
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
        }

        dist[0][0] = cave[0][0];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.i == n-1 && cur.j == n-1)
                break;

            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (ni < 0 || nj < 0 || ni >= n || nj >= n)
                    continue;

                int newDist = dist[cur.i][cur.j] + cave[ni][nj];
                if (dist[ni][nj] <= newDist)
                    continue;

                dist[ni][nj] = newDist;
                pq.add(new Node(ni, nj, newDist));
            }
        }

        return dist[n-1][n-1];
    }
    
}

class Node implements Comparable<Node> {
    int i, j, dist;

    public Node(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node ohter) {
        return this.dist - ohter.dist;
    }
}