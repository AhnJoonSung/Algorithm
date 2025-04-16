import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        List<Shark>[][] map = new List[r+1][c+1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] = new ArrayList<Shark>();
            }
        }

        int m = sc.nextInt();

        List<Shark> sharks = new ArrayList<>();
        while (m-- > 0) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            Shark shark = new Shark(i, j, sc.nextInt(), sc.nextInt(), sc.nextInt(), map);
            sharks.add(shark);
            map[i][j].add(shark);
        }

        King king = new King(map, sharks);
        while (king.move()) {
            king.fishing();

            for (Shark shark: sharks)
                shark.move();

            eatOther(map, sharks);
        }

        System.out.println(king.score);
    }

    static void eatOther(List<Shark>[][] map, List<Shark> sharks) {
        int r = map.length - 1;
        int c = map[0].length - 1;

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j].size() >= 2)
                    eat(map[i][j], sharks);
            }
        }
    }

    static void eat(List<Shark> cell, List<Shark> sharks) {
        Shark biggest = cell.get(0);
        for (int i = 1; i < cell.size(); i++) {
            Shark shark = cell.get(i);
            if (shark.size < biggest.size) {
                sharks.remove(shark);
            } else {
                sharks.remove(biggest);
                biggest = shark;
            }
        }

        cell.clear();
        cell.add(biggest);
    }
}

class King {
    int j;
    int score;
    List<Shark>[][] map;
    List<Shark> sharks;

    public King(List<Shark>[][] map, List<Shark> sharks) {
        this.j = 0;
        this.score = 0;
        this.map = map;
        this.sharks = sharks;
    }

    public boolean move() {
        if (this.j < map[0].length - 1) {
            this.j++;
            return true;
        }
        return false;
    }

    public void fishing() {
        for (int i = 1; i < map.length; i++) {
            if (map[i][this.j].isEmpty()) continue;
            
            Shark shark = map[i][this.j].get(0);
            score += shark.size;
            map[i][this.j].clear();
            sharks.remove(shark);
            return;
        }
    }
}

class Shark {
    static final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;

    int i, j, speed, dir, size;
    boolean moved;

    List<Shark>[][] map;

    int r, c;

    public Shark(int i, int j, int speed, int dir, int size, List<Shark>[][] map) {
        this.i = i;
        this.j = j;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
        this.map = map;
        this.r = map.length - 1;
        this.c = map[0].length - 1;
    }

    public void move() {
        int ni = this.i, nj = this.j;

        if (dir == UP || dir == DOWN) {
            int cycle = 2 * (r - 1);
            speed %= cycle;
            
            for (int s = 0; s < speed; s++) {
                if (ni == 1 && dir == UP) dir = DOWN;
                else if (ni == r && dir == DOWN) dir = UP;

                ni += (dir == UP? -1 : 1);
            }
        } else {
            int cycle = 2 * (c - 1);
            speed %= cycle;

            for (int s = 0; s < speed; s++) {
                if (nj == 1 && dir == LEFT) dir = RIGHT;
                else if (nj == c && dir == RIGHT) dir = LEFT;

                nj += (dir == LEFT? -1 : 1);
            }
        }

        map[this.i][this.j].remove(this);
        map[ni][nj].add(this);
        this.i = ni;
        this.j = nj;
    }
}