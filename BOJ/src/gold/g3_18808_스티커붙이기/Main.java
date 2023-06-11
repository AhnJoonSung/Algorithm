package gold.g3_18808_스티커붙이기;

import java.util.*;

public class Main {
    static final int FILLED = 1;
    static final int EMPTY = 0;
    static final int ROTATE_CNT = 4;

    static int N, M, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        sc.nextLine();

        List<List<Integer>> notebook = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                temp.add(0);
            }
            notebook.add(temp);
        }

        List<Sticker> stickers = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            sc.nextLine();

            List<List<Integer>> graph_paper = new ArrayList<>();
            for (int j = 0; j < R; j++) {
                List<Integer> temp = new ArrayList<>();
                for (int k = 0; k < C; k++) {
                    temp.add(sc.nextInt());
                }
                graph_paper.add(temp);
                sc.nextLine();
            }
            stickers.add(new Sticker(R, C, graph_paper));
        }

        Position position;
        for (Sticker sticker : stickers) {
            for (int n = 0; n < ROTATE_CNT; n++) {
                position = find_stick_position(notebook, sticker);
                if (position != null) {
                    stick(notebook, sticker, position);
                    break;
                }
                sticker.rotate();
            }
        }
        int result = get_sticker_cnt(notebook);
        System.out.println(result);
    }

    public static Position find_stick_position(List<List<Integer>> notebook, Sticker sticker) {
        for (int i = 0; i <= (N - sticker.row); i++) {
            for (int j = 0; j <= (M - sticker.col); j++) {
                if (can_stick(notebook, sticker, i, j)) {
                    return (new Position(i, j));
                }
            }
        }
        return (null);
    }

    private static boolean can_stick(List<List<Integer>> notebook, Sticker sticker, int not_i, int not_j) {
        for (int i = 0; i < sticker.row; i++) {
            for (int j = 0; j < sticker.col; j++) {
                int sticker_block = sticker.graph_paper.get(i).get(j);
                int not_block = notebook.get(not_i + i).get(not_j + j);

                if (sticker_block == FILLED && not_block == FILLED) {
                    return (false);
                }
            }
        }
        return (true);
    }

    public static void stick(List<List<Integer>> notebook, Sticker sticker, Position position) {
        int not_i = position.i;
        int not_j = position.j;

        for (int i = 0; i < sticker.row; i++) {
            for (int j = 0; j < sticker.col; j++) {
                int sticker_block = sticker.graph_paper.get(i).get(j);

                if (sticker_block == FILLED) {
                    notebook.get(not_i + i).set(not_j + j, FILLED);
                }
            }
        }
    }

    public static int get_sticker_cnt(List<List<Integer>> notebook) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook.get(i).get(j) == FILLED) {
                    cnt++;
                }
            }
        }
        return (cnt);
    }
}

class Sticker {
    int row, col;
    List<List<Integer>> graph_paper;

    public Sticker(int row, int col, List<List<Integer>> graph_paper) {
        this.row = row;
        this.col = col;
        this.graph_paper = graph_paper;
    }

    public void rotate() {
        List<List<Integer>> rotated_paper = new ArrayList<>();

        for (int i = 0; i < col; i++) {
            rotated_paper.add(new ArrayList<>());
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                int original = graph_paper.get((row - 1)- j).get(i);

                rotated_paper.get(i).add(original);
            }
        }

        int temp = this.row;
        this.row = col;
        this.col = temp;
        this.graph_paper = rotated_paper;
    }
}

class Position {
    int i, j;

    public Position (int i, int j) {
        this.i = i;
        this.j = j;
    }
}
