package gold.g5_14503_로봇청소기;

import java.util.Scanner;

import static gold.g5_14503_로봇청소기.Block.CLEAN;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Block[][] room = new Block[n][m];

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] = Block.get(sc.nextInt());
            }
        }

        Robot robot = new Robot(r, c, d, room);
        while (true) {
            robot.cleaning();
            if (robot.findDirty())
                robot.moveForward();
            else if (robot.canMoveBack())
                robot.moveBack();
            else
                break;
        }

        System.out.println(robot.getCleaningCnt());
    }
}

class Robot {
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private int direction;
    private int r, c;
    private final Block[][] room;
    private int cleaningCnt = 0;

    public Robot(int r, int c, int direction, Block[][] room) {
        this.r = r;
        this.c = c;
        this.direction = direction;
        this.room = room;
    }

    public void moveForward() {
        int nr = r + dr[direction];
        int nc = c + dc[direction];

        move(nr, nc);
    }

    public void moveBack() {
        int backDir = (direction + 2) % 4;
        int nr = r + dr[backDir];
        int nc = c + dc[backDir];

        move(nr, nc);
    }

    private void move(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public boolean canMoveBack() {
        int backDir = (direction + 2) % 4;
        int nr = r + dr[backDir];
        int nc = c + dc[backDir];

        return !blockByWall(nr, nc);
    }

    public void cleaning() {
        if (room[r][c].isDirty()) {
            room[r][c] = CLEAN;
            cleaningCnt++;
        }
    }

    public boolean findDirty() {
        int cnt = 4;
        while (cnt-- > 0) {
            direction = (direction + 3) % 4;
            int nr = r + dr[direction];
            int nc = c + dc[direction];

            if (blockByWall(nr, nc))
                continue;
            if (room[nr][nc].isDirty())
                return true;
        }
        return false;
    }

    private boolean blockByWall(int r, int c) {
        return (room[r][c].isWall());
    }

    public int getCleaningCnt() {
        return cleaningCnt;
    }

}

enum Block {
    DIRTY,
    WALL,
    CLEAN;

    static final Block[] blocks = {DIRTY, WALL, CLEAN};

    public boolean isDirty() {
        return this == DIRTY;
    }

    public boolean isWall() {
        return this == WALL;
    }

    public static Block get(int id) {
        return blocks[id];
    }
}