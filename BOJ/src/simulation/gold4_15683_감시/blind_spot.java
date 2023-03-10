package simulation.gold4_15683_감시;

import java.util.*;

int N;
int M;
static final int WALL = 6;
static final int BLIND_SPOT = 0;
static final int MONITORING_SPOT = -1;
class Main {
    static int[] CCTV_directions = {-1, 4, 2, 4, 4, 1};
    public static void main(String[] args) {
        int i, j;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        List<Integer>[] office = new ArrayList[N];
        for (i = 0; i < N; i++) {
            office[i] = new ArrayList<>();
        }

        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                office[i].add(sc.nextInt());
            }
            sc.nextLine();
        }

        List<CCTV> cctv_list = get_cctv_list(office);
        int result = recursive(office, cctv_list,0, 64);
        System.out.println(result);
    }

    public static int recursive(List<Integer>[] office, List<CCTV> cctv_list, int n, int min) {
        if (n == cctv_list.size()) {
            return (get_blind_spot(office));
        }

        List<Integer>[] copy;
        CCTV cctv = cctv_list.get(n);
        for (int direction = 0; direction < CCTV_directions[cctv.type]; direction++) {
            copy = array_copy(office);
            cctv.monitor(copy, direction);
            int value = recursive(copy, cctv_list, n + 1, min);
            if (value < min) {
                min = value;
            }
        }
        return (min);
    }

    public static int get_blind_spot(List<Integer>[] office) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i].get(j) == 0) {
                    cnt++;
                }
            }
        }
        return (cnt);
    }

    public static List<Integer>[] array_copy(List<Integer>[] office) {
        List<Integer>[] copy = new ArrayList[office.length];

        for (int i = 0; i < N; i++) {
            copy[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i].add(office[i].get(j));
            }
        }
        return (copy);
    }

    public static List<CCTV> get_cctv_list(List<Integer>[] office) {
        List<CCTV> cctv_list = new ArrayList<CCTV>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int type = office[i].get(j);
                if (type != 0 && type != 6) {
                    CCTV cctv = new CCTV(type, i, j);
                    cctv_list.add(cctv);
                }
            }
        }
        return (cctv_list);
    }
}

class CCTV {
    int type;
    Position pos;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public CCTV(int type, Position pos) {
        this.type = type;
        this.pos = pos;
    }

    public void monitor(List<Integer>[] office, int direction) {
        switch (type) {
            case 5:
                monitor_cal(office, i, j, (direction + 3) % 4);
            case 4:
                monitor_cal(office, i, j, (direction + 2) % 4);
            case 3:
                monitor_cal(office, i, j, (direction + 1) % 4);
            case 1:
                monitor_cal(office, i, j, direction);
                break;
            case 2:
                monitor_cal(office, i, j, direction);
                monitor_cal(office, i, j, (direction + 2) % 4);
                break;
        }
    }

    private void monitor_cal(List<Integer>[] office, Position pos, int direction) {
        int row = pos.row;
        int col = pos.col;

        while (!isEnd(office, pos)) {

        }
    }

    private boolean isEnd(List<Integer>[] office, int row, int col) {
        if (row == (-1 || N) )
            return true;
        if (col == -1 || col == M)
            return true;
        if (office[row].get(col) == WALL)
            return true;
        return false;
    }
}

class Position {
    int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void set_position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}