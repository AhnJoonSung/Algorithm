package gold.g5_14503_로봇청소기;

import java.util.Scanner;

import static gold.g5_14503_로봇청소기.Block.CLEAN;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Block[][] room = new Block[n][m];

        int row = sc.nextInt();
        int col = sc.nextInt();
        int direction = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] = Block.get(sc.nextInt());
            }
        }

        Robot robot = new Robot(row, col, direction, room);
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
    private int row, col;
    private final Block[][] room;
    private int cleaningCnt = 0;

    public Robot(int row, int col, int direction, Block[][] room) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.room = room;
    }

    public void moveForward() {
        int newRow = row + dr[direction];
        int newCol = col + dc[direction];

        move(newRow, newCol);
    }

    public void moveBack() {
        int backDir = (direction + 2) % 4;
        int newRow = row + dr[backDir];
        int newCol = col + dc[backDir];

        move(newRow, newCol);
    }

    private void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean canMoveBack() {
        int backDir = (direction + 2) % 4;
        int newRow = row + dr[backDir];
        int newCol = col + dc[backDir];

        return !blockByWall(newRow, newCol);
    }

    public void cleaning() {
        if (room[row][col].isDirty()) {
            room[row][col] = CLEAN;
            cleaningCnt++;
        }
    }

    public boolean findDirty() {
        int cnt = 4;
        while (cnt-- > 0) {
            direction = (direction + 3) % 4;
            int newRow = row + dr[direction];
            int newCol = col + dc[direction];

            if (blockByWall(newRow, newCol))
                continue;
            if (room[newRow][newCol].isDirty())
                return true;
        }
        return false;
    }

    private boolean blockByWall(int row, int col) {
        return (room[row][col].isWall());
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