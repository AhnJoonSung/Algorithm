import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        Fish[][] map = new Fish[n][n];
        Shark babyShark = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = sc.nextInt();

                if (size == 0)
                    continue;
                if (size == 9)
                    babyShark = new Shark(i, j);
                else
                    map[i][j] = new Fish(i, j, size);
            }
        }

        while (babyShark.move(map)) {
            babyShark.eat(map);
        }

        System.out.println(babyShark.time);
        sc.close();
    }
}

class Shark extends Fish {
    static final int[] di = {-1,0,0,1};
    static final int[] dj = {0,-1,1,0};

    int cnt;
    int time;

    public Shark(int i, int j) {
        super(i, j, 2);
        this.cnt = 0;
        this.time = 0;
    }

    public boolean move(Fish[][] map) {
        int n = map.length;

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(this.pos);
        boolean[][] visited = new boolean[n][n];
        visited[pos.i][pos.j] = true;

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();

            if (map[cur.i][cur.j] != null && map[cur.i][cur.j].size < this.size) {
                this.time += cur.dist;
                this.pos = new Pos(cur.i, cur.j);
                return true;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ni = cur.i + di[dir];
                int nj = cur.j + dj[dir];

                if (ni < 0 || nj < 0 || ni >= n || nj >= n)
                    continue;
                if (visited[ni][nj])
                    continue;
                if (map[ni][nj] != null && map[ni][nj].size > this.size)
                    continue;

                visited[ni][nj] = true;
                Pos newPos = new Pos(ni, nj, cur.dist + 1);
                pq.add(newPos);
            }

        }

        return false;
    }
    
    public void eat(Fish[][] map) {
        Fish fish = map[this.pos.i][this.pos.j];

        if (fish == null) return;

        if (fish.size < this.size) {
            if (++this.cnt == this.size) {
                this.size++;
                this.cnt = 0;
            }

            map[this.pos.i][this.pos.j] = null;
        }
    }
}

class Fish {
    Pos pos;
    int size;

    public Fish(int i, int j, int size) {
        this.pos = new Pos(i, j);
        this.size = size;
    }
}

class Pos implements Comparable<Pos> {
    int i, j, dist;

    public Pos(int i, int j) {
        this.i = i;
        this.j = j;
        this.dist = 0;
    }

    public Pos(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pos other) {
        if (dist != other.dist)
            return dist - other.dist;
        if (i != other.i)
            return i - other.i;
        return j - other.j;
    }
}