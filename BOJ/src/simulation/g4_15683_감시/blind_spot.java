package simulation.g4_15683_감시;

import java.util.*;

class Main {
    static final int[] CCTV_directions = {-1, 4, 2, 4, 4, 1};

    static int N;
    static int M;
    static int cctv_cnt;

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
        cctv_cnt = cctv_list.size();
        int result = cctv_on(office, cctv_list,0, 64);
        System.out.println(result);
    }

    public static int cctv_on(List<Integer>[] office, List<CCTV> cctv_list, int n, int min) {
        if (n == cctv_cnt) {
            return (get_blind_spot(office));
        }

        List<Integer>[] copy;
        CCTV cctv = cctv_list.get(n);
        int blind_spot_cnt;
        for (int direction = 0; direction < CCTV_directions[cctv.type]; direction++) {
            copy = array_copy(office);
            cctv.monitor(copy, direction);
            blind_spot_cnt = cctv_on(copy, cctv_list, n + 1, min);

            if (blind_spot_cnt < min) {
                min = blind_spot_cnt;
            }
        }
        return (min);
    }

    public static int get_blind_spot(List<Integer>[] office) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i].get(j) == 0)
                    cnt++;
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

                if (CCTV.BLIND_SPOT < type && type < CCTV.WALL) {
                    CCTV cctv = new CCTV(type, new Position(i, j));
                    cctv_list.add(cctv);
                }
            }
        }
        return (cctv_list);
    }
}

class CCTV {
    static final int WALL = 6;
    static final int BLIND_SPOT = 0;
    static final int MONITORING_SPOT = -1;
    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    int type;
    Position pos;
    public CCTV(int type, Position pos) {
        this.type = type;
        this.pos = pos;
    }

    public void monitor(List<Integer>[] office, int direction) {
        switch (type) {
            case 5:
                monitor_cal(office, pos, (direction + 3) % 4);
            case 4:
                monitor_cal(office, pos, (direction + 2) % 4);
            case 3:
                monitor_cal(office, pos, (direction + 1) % 4);
            case 1:
                monitor_cal(office, pos, direction);
                break;
            case 2:
                monitor_cal(office, pos, direction);
                monitor_cal(office, pos, (direction + 2) % 4);
                break;
        }
    }

    private void monitor_cal(List<Integer>[] office, Position pos, int direction) {
        int row = pos.row;
        int col = pos.col;
        int cur_spot;

        while (!isEnd(office, row, col)) {
            cur_spot = office[row].get(col);
            if (cur_spot == BLIND_SPOT)
                office[row].set(col, MONITORING_SPOT);
            row += dy[direction];
            col += dx[direction];
        }
    }

    private boolean isEnd(List<Integer>[] office, int row, int col) {
        if (row == -1 || row == office.length)
            return true;
        if (col == -1 || col == office[row].size())
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
}