import java.util.*;

class Main {
    static int[] CCTV_directions = {-1, 4, 2, 4, 4, 1};

    public static void main(String[] args) {
        int N;
        int M;
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

        for (int i = 0; i < office.length; i++) {
            for (int j = 0; j < office[i].size(); j++) {
                if (office[i].get(j) == 0) {
                    cnt++;
                }
            }
        }
        return (cnt);
    }

    public static List<Integer>[] array_copy(List<Integer>[] office) {
        List<Integer>[] copy = new ArrayList[office.length];

        for (int i = 0; i < office.length; i++) {
            copy[i] = new ArrayList<>();
        }
        for (int i = 0; i < office.length; i++) {
            for (int j = 0; j < office[i].size(); j++) {
                copy[i].add(office[i].get(j));
            }
        }
        return (copy);
    }

    public static List<CCTV> get_cctv_list(List<Integer>[] office) {
        List<CCTV> cctv_list = new ArrayList<CCTV>();
        for (int i = 0; i < office.length; i++) {
            for (int j = 0; j < office[i].size(); j++) {
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
    int type, i, j;
    int[] vector_i = {0, 1, 0, -1};
    int[] vector_j = {1, 0, -1, 0};

    public CCTV(int type, int i, int j) {
        this.type = type;
        this.i = i;
        this.j = j;
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

    private void monitor_cal(List<Integer>[] office, int i, int j, int direction) {
        if ((i == -1 || i == office.length) || (j == -1 || j == office[i].size()) || office[i].get(j) == 6) {
            return;
        }
        if (office[i].get(j) == 0) {
            office[i].set(j, -1);
        }
        monitor_cal(office,i + vector_i[direction], j + vector_j[direction], direction);
    }
}