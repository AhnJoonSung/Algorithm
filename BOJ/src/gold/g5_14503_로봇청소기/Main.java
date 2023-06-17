package gold.g5_14503_로봇청소기;

import java.util.Scanner;

import static gold.g5_14503_로봇청소기.CellState.CLEAN;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int row = sc.nextInt();
        int col = sc.nextInt();
        int dir = sc.nextInt();

        CellState[][] room = new CellState[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                room[i][j] = CellState.get(sc.nextInt());
            }
        }

        Robot robot = new Robot(row, col, dir, room);
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
    private int dir;
    private int row, col;
    private final CellState[][] room;
    private int cleaningCnt = 0;

    public Robot(int row, int col, int dir, CellState[][] room) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.room = room;
    }

    public void moveForward() {
        move(dir);
    }

    public void moveBack() {
        int backDir = (dir + 2) % 4;
        move(backDir);
    }

    private void move(int dir) {
        row = row + dr[dir];
        col = col + dc[dir];
    }

    public boolean canMoveBack() {
        int backDir = (dir + 2) % 4;
        int newRow = row + dr[backDir];
        int newCol = col + dc[backDir];

        if (blockByWall(newRow, newCol))
            return false;
        return true;
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
            dir = (dir + 3) % 4;
            int newRow = row + dr[dir];
            int newCol = col + dc[dir];

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

enum CellState {
    DIRTY,
    WALL,
    CLEAN;

    static final CellState[] CELL_STATES = {DIRTY, WALL, CLEAN};

    public boolean isDirty() {
        return this == DIRTY;
    }

    public boolean isWall() {
        return this == WALL;
    }

    public static CellState get(int id) {
        return CELL_STATES[id];
    }
}