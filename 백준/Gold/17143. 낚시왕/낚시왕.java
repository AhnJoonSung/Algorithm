import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        Shark [][] map = new Shark[r+1][c+1];

        int m = sc.nextInt();

        while (m-- > 0) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            Shark shark = new Shark(i, j, sc.nextInt(), sc.nextInt(), sc.nextInt());
            map[i][j] = shark;
        }

        King king = new King();
        while (king.move(map)) {
            king.fishing(map);

            Shark[][] newMap = new Shark[r+1][c+1];

            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (map[i][j] != null) {
                        map[i][j].move(newMap);
                    }
                }
            }

            map = newMap;
        }

        System.out.println(king.score);
    }
}

class King {
    int j;
    int score;

    public King() {
        this.j = 0;
        this.score = 0;
    }

    public boolean move(Shark[][] map) {
        if (this.j < map[0].length - 1) {
            this.j++;
            return true;
        }
        return false;
    }

    public void fishing(Shark[][] map) {
        for (int i = 1; i < map.length; i++) {
            if (map[i][this.j] == null) continue;
            
            Shark shark = map[i][this.j];
            score += shark.size;
            map[i][this.j] = null;
            return;
        }
    }
}

class Shark {
    static final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;

    int i, j, speed, dir, size;

    public Shark(int i, int j, int speed, int dir, int size) {
        this.i = i;
        this.j = j;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }

    public void move(Shark[][] map) {
        int r = map.length-1, c = map[0].length-1;
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

        if (map[ni][nj] != null && map[ni][nj].size > this.size)
            return;

        map[ni][nj] = this;
        this.i = ni;
        this.j = nj;
    }
}